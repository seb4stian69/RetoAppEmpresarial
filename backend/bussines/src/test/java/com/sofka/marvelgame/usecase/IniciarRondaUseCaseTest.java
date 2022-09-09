package com.sofka.marvelgame.usecase;

import co.com.sofka.domain.generic.DomainEvent;
import com.sofka.marvelgame.commands.IniciarRondaCommand;
import com.sofka.marvelgame.events.JuegoCreado;
import com.sofka.marvelgame.events.RondaCreada;
import com.sofka.marvelgame.events.RondaIniciada;
import com.sofka.marvelgame.events.TableroCreado;
import com.sofka.marvelgame.gateway.JuegoDomainEventRepository;
import com.sofka.marvelgame.values.JugadorID;
import com.sofka.marvelgame.values.Ronda;
import com.sofka.marvelgame.values.TableroID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class IniciarRondaUseCaseTest {

    @Mock
    private JuegoDomainEventRepository repository;

    @InjectMocks
    private IniciarRondaUseCase useCase;

    @Test
    void iniciarRonda(){

        // arrange
        var command = new IniciarRondaCommand();
        command.setJuegoId("1234");



        when(repository.obtenerEventosPor("1234")).thenReturn(history());

        StepVerifier.create(useCase.apply(Mono.just(command)))
                .expectNextMatches(domainEvent -> {
                    var event = (RondaIniciada) domainEvent;
                    return "1234".equals(event.aggregateRootId());

                })
                .expectComplete()
                .verify();
    }

    private Flux<DomainEvent> history() {
        var jugadorId = JugadorID.of("yyyyy");
        var jugador2Id = JugadorID.of("hhhhhh");
        var ronda = new Ronda(1, Set.of(jugadorId,jugador2Id));


        return Flux.just(
                new JuegoCreado(jugadorId),
                new TableroCreado(new TableroID(), Set.of(jugadorId, jugador2Id)),
                new RondaCreada(ronda,20)
        );
    }

}