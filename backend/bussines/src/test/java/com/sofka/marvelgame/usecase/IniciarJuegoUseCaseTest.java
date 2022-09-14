package com.sofka.marvelgame.usecase;

import co.com.sofka.domain.generic.DomainEvent;
import com.sofka.marvelgame.commands.IniciarJuegoCommand;
import com.sofka.marvelgame.events.JuegoCreado;
import com.sofka.marvelgame.events.JugadorAgregado;
import com.sofka.marvelgame.events.RondaCreada;
import com.sofka.marvelgame.events.TableroCreado;
import com.sofka.marvelgame.gateway.JuegoDomainEventRepository;
import com.sofka.marvelgame.values.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Set;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class IniciarJuegoUseCaseTest {

    @Mock
    private JuegoDomainEventRepository repository;

    @InjectMocks
    private IniciarJuegoUseCase useCase;

    @Test
    void iniciarJuego() {

        //arrange
        var command = new IniciarJuegoCommand();
        command.setJuegoId("Juego1");

        when(repository.obtenerEventosPor("Juego1")).thenReturn(history());

        StepVerifier.create(useCase.apply(Mono.just(command)))
                .expectNextMatches(domainEvent -> {
                    var event = (TableroCreado) domainEvent;
                    return "Juego1".equals(event.aggregateRootId());

                }).expectNextMatches(domainEvent -> {
                    var event = (RondaCreada) domainEvent;
                    return event.getRonda().value().numero()==1;
                })
                .expectComplete()
                .verify();


    }

    private Flux<DomainEvent> history() {
        var jugadorId = JugadorID.of("ggg");
        var jugador2Id = JugadorID.of("hhhhhh");
        var cartaId = CartaMaestraID.of("1");
        var cartas = Set.of(new Carta(cartaId, 20, true, estaOculta));


        return Flux.just(
                new JuegoCreado(jugadorId),
                new JugadorAgregado(jugadorId, Alias.of("user1"),new Mazo(cartas)),
                new JugadorAgregado(jugador2Id,Alias.of("user2"),new Mazo(cartas))

        );
    }

}