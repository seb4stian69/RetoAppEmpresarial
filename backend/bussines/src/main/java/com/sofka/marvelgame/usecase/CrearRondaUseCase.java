package com.sofka.marvelgame.usecase;

import co.com.sofka.domain.generic.DomainEvent;
import com.sofka.marvelgame.Juego;
import com.sofka.marvelgame.commands.CrearRondaCommand;
import com.sofka.marvelgame.events.RondaTerminada;
import com.sofka.marvelgame.gateway.JuegoDomainEventRepository;
import com.sofka.marvelgame.values.JuegoID;
import com.sofka.marvelgame.values.JugadorID;
import com.sofka.marvelgame.values.Ronda;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;


public class CrearRondaUseCase extends UseCaseForEvent<RondaTerminada> {

    private final JuegoDomainEventRepository repository;

    public CrearRondaUseCase(JuegoDomainEventRepository repository) {
        this.repository = repository;
    }

    @Override
    public Flux<DomainEvent> apply(Mono<RondaTerminada> rondaTerminada) {
        return rondaTerminada.flatMapMany((event) -> repository
                .obtenerEventosPor(event.aggregateRootId())
                .collectList()
                .flatMapIterable(events -> {
                    var juego = Juego.from(JuegoID.of(event.aggregateRootId()), events);
                    var jugadores = new HashSet<>(event.getJugadorIds());
                    var ronda = juego.ronda();
                    if(Objects.isNull(ronda)){
                        throw new IllegalArgumentException("Debe existir la primera ronda");
                    }
                    juego.crearRonda(ronda.incrementarRonda(jugadores), 60);
                    return juego.getUncommittedChanges();
                }));
    }
}
