package com.sofka.marvelgame.usecase;

import co.com.sofka.domain.generic.DomainEvent;
import com.sofka.marvelgame.Juego;
import com.sofka.marvelgame.commands.CrearRondaCommand;
import com.sofka.marvelgame.gateway.JuegoDomainEventRepository;
import com.sofka.marvelgame.values.JuegoID;
import com.sofka.marvelgame.values.JugadorID;
import com.sofka.marvelgame.values.Ronda;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.util.Optional;
import java.util.stream.Collectors;


public class CrearRondaUseCase extends UseCaseForCommand<CrearRondaCommand> {

    private final JuegoDomainEventRepository repository;

    public CrearRondaUseCase(JuegoDomainEventRepository repository) {
        this.repository = repository;
    }

    @Override
    public Flux<DomainEvent> apply(Mono<CrearRondaCommand> iniciarJuegoCommand) {
        return iniciarJuegoCommand.flatMapMany((command) -> repository
                .obtenerEventosPor(command.getJuegoId())
                .collectList()
                .flatMapIterable(events -> {
                    var juego = Juego.from(JuegoID.of(command.getJuegoId()), events);
                    var jugadores = command.getJugadores().stream()
                            .map(JugadorID::of)
                            .collect(Collectors.toSet());

                    Optional.ofNullable(juego.ronda())
                            .ifPresentOrElse(
                                    ronda -> juego.crearRonda(
                                            ronda.incrementarRonda(jugadores), command.getTiempo()), () -> juego.crearRonda(
                                            new Ronda(1, jugadores), command.getTiempo())
                            );
                    return juego.getUncommittedChanges();
                }));
    }
}
