package com.sofka.marvelgame.usecase;

import co.com.sofka.domain.generic.DomainEvent;
import com.sofka.marvelgame.commands.CrearRondaCommand;
import com.sofka.marvelgame.events.JuegoCreado;
import com.sofka.marvelgame.events.RondaCreada;
import com.sofka.marvelgame.events.TableroCreado;
import com.sofka.marvelgame.gateway.JuegoDomainEventRepository;
import com.sofka.marvelgame.values.JugadorID;
import com.sofka.marvelgame.values.TableroID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith({MockitoExtension.class})
class CrearRondaUseCaseTest {

    @Mock
    private JuegoDomainEventRepository repository;

    @InjectMocks
    private CrearRondaUseCase useCase;

    @Test
    void CrearRonda(){

        var command = new CrearRondaCommand();
        command.setJuegoId("1234");
        command.setJugadores(new HashSet<>());
        command.getJugadores().add("lu");
        command.getJugadores().add("Lulo");
        command.setTiempo(30);

        when(repository.obtenerEventosPor("1234")).thenReturn(history());

        StepVerifier.
                create(useCase.apply(Mono.just(command)))
                .expectNextMatches(domainEvent -> {
                    var event = (RondaCreada) domainEvent;
                    Assertions.assertEquals(30, event.getTiempo().intValue());
                    return 2 == (event.getRonda().value().jugadores().size());
                })
                .expectComplete()
                .verify();
    }

    private Flux<DomainEvent> history() {
        var jugadorId = JugadorID.of("yyyyy");
        var jugador2Id = JugadorID.of("hhhhhh");

        return Flux.just(

                new JuegoCreado(jugadorId),
                new TableroCreado(TableroID.of("tableroID"), Set.of(jugadorId, jugador2Id))
        );
    }

}