package com.sofka.marvelgame.usecase;

import co.com.sofka.domain.generic.DomainEvent;
import com.sofka.marvelgame.Juego;
import com.sofka.marvelgame.entities.Jugador;
import com.sofka.marvelgame.events.RondaTerminada;
import com.sofka.marvelgame.gateway.JuegoDomainEventRepository;
import com.sofka.marvelgame.values.JuegoID;
import com.sofka.marvelgame.values.JugadorID;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class DeterminarGanadorUseCase  extends UseCaseForEvent<RondaTerminada> {
    private final JuegoDomainEventRepository repository;

    public DeterminarGanadorUseCase(JuegoDomainEventRepository repository){
        this.repository = repository;
    }


    @Override
    public Flux<DomainEvent> apply(Mono<RondaTerminada> rondaTerminadaMono) {
        return rondaTerminadaMono.flatMapMany((event) -> repository
                .obtenerEventosPor(event.aggregateRootId())
                .collectList()
                .flatMapIterable(events -> {
                    var juego = Juego.from(JuegoID.of(event.aggregateRootId()), events);
                    var jugadoresVivos = juego.jugadores().values().stream()
                            .filter(jugador -> jugador.mazo().value().cantidad() > 0)
                            .collect(Collectors.toList());
                    if(jugadoresVivos.size()  == 1){
                        var jugador = jugadoresVivos.get(0);
                        juego.finalizarJuego(jugador.identity(), jugador.alias());
                    } else if(event.getJugadorIds().size() == 1){
                        event.getJugadorIds().stream().findFirst()
                                .flatMap(jugadorId -> jugadoresVivos.stream()
                                        .filter(jugador -> jugador.identity().value().equals(jugadorId.value()))
                                        .findFirst()).ifPresent(jugador -> juego.finalizarJuego(jugador.identity(), jugador.alias()));
                    }
                    return juego.getUncommittedChanges();
                }));
    }
}
