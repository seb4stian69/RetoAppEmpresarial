package com.sofka.marvelgame.usecase;

import co.com.sofka.domain.generic.DomainEvent;
import com.sofka.marvelgame.commands.LanzarUnaCartaCommand;
import com.sofka.marvelgame.events.*;
import com.sofka.marvelgame.gateway.JuegoDomainEventRepository;
import com.sofka.marvelgame.values.*;
import org.junit.jupiter.api.Assertions;
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
class PonerCartaEnTableroUseCaseTest {

    @Mock
    private JuegoDomainEventRepository repository;

    @InjectMocks
    private PonerCartaEnTableroUseCase useCase;

    @Test
    void lanzarCarta() {
        //Arranga

        var command = new LanzarUnaCartaCommand();
        command.setCartaId("user");
        command.setJuegoId("game");
        command.setJugadorId("1234");

        when(repository.obtenerEventosPor("game")).thenReturn(history());

        StepVerifier.create(useCase.apply(Mono.just(command)))//act
                .expectNextMatches(domainEvent -> {
                    var event = (CartaLanzada) domainEvent;
                    Assertions.assertEquals("1234", event.getJugadorId().value());
                    return "user".equals(event.getCarta().value().cartaMaeestraID().value());
                })
                .expectNextMatches(domainEvent -> {
                    var event = (CartaQuitadaDelMazo) domainEvent;
                    Assertions.assertEquals("1234", event.getJugadorId().value());
                    return "user".equals(event.getCarta().value().cartaMaeestraID().value());
                })
                .expectComplete()
                .verify();
    }

    private Flux<DomainEvent> history() {

        var idJugador1 = JugadorID.of("1234");
        var idJugador2 = JugadorID.of("4568");
        var cartaId = CartaMaestraID.of("user");
        var cartas = Set.of(new Carta(cartaId, 20, true));
        var rondaDelJuego = new Ronda(1, Set.of(idJugador1, idJugador2));

        return Flux.just(
                new JuegoCreado(idJugador1),
                new JugadorAgregado(idJugador1, Alias.of("user"), new Mazo(cartas)),
                new TableroCreado(new TableroID(), Set.of(idJugador1, idJugador2)),
                new RondaCreada(rondaDelJuego, 30),
                new RondaIniciada()

        );

    }

}