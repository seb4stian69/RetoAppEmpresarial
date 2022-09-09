package com.sofka.marvelgame.usecase;

import co.com.sofka.domain.generic.DomainEvent;
import com.sofka.marvelgame.commands.CrearJuegoCommand;
import com.sofka.marvelgame.events.JuegoCreado;
import com.sofka.marvelgame.events.JugadorAgregado;
import com.sofka.marvelgame.gateway.ListaCartasServices;
import com.sofka.marvelgame.gateway.ModelCartas;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.HashMap;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CrearJuegoUseCaseTest {

    @Mock
    private ListaCartasServices listaDeCartasService;

    @InjectMocks
    private CrearJuegoUseCase useaCase;


    @Test
    void crearJuego(){
        //arrange

        var command = new CrearJuegoCommand();
        command.setJuegoId("XXX");
        command.setJugadores(new HashMap<>());
        command.getJugadores().put("123","j1prueba");
        command.getJugadores().put("456","j2prueba");
        command.setJugadorPrincipalId("123");

        when(listaDeCartasService.obtenerCartasDeMarvel()).thenReturn(history());

        StepVerifier.create(useaCase.apply(Mono.just(command)))
                .expectNextMatches(new Predicate<DomainEvent>() {
                    @Override
                    public boolean test(DomainEvent domainEvent) {
                        var event = (JuegoCreado) domainEvent;
                        return "XXX".equals(event.aggregateRootId()) && "123".equals(event.getJugadorPrincipal().value());
                    }
                }).expectNextMatches(new Predicate<DomainEvent>() {
                    @Override
                    public boolean test(DomainEvent domainEvent) {
                        var event = (JugadorAgregado) domainEvent;
                        return "j1prueba".equals(event.getAlias());
                    }
                }).expectNextMatches(new Predicate<DomainEvent>() {
                    @Override
                    public boolean test(DomainEvent domainEvent) {
                        var event = ( JugadorAgregado ) domainEvent;
                        return "j2prueba".equals(event.getAlias());
                    }
                }).expectComplete().verify();



    }

    private Flux<ModelCartas> history() {
        return  Flux.just(
                new ModelCartas("2","tarjeta2","uri",2),
                new ModelCartas("1","tarjeta1","uri",2),
                new ModelCartas("3","tarjeta2","uri",2),
                new ModelCartas("4","tarjeta2","uri",2),
                new ModelCartas("5","tarjeta2","uri",2),
                new ModelCartas("6","tarjeta2","uri",2),
                new ModelCartas("7","tarjeta2","uri",2),
                new ModelCartas("8","tarjeta2","uri",2),
                new ModelCartas("9","tarjeta2","uri",2),
                new ModelCartas("10","tarjeta2","uri",2)
        );
    }

}