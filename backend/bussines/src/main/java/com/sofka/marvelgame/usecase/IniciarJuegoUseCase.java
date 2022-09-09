package com.sofka.marvelgame.usecase;

import co.com.sofka.domain.generic.DomainEvent;
import com.sofka.marvelgame.Juego;
import com.sofka.marvelgame.commands.IniciarJuegoCommand;
import com.sofka.marvelgame.gateway.JuegoDomainEventRepository;
import com.sofka.marvelgame.values.JuegoID;
import com.sofka.marvelgame.values.Ronda;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class IniciarJuegoUseCase extends UseCaseForCommand<IniciarJuegoCommand>{

    private final JuegoDomainEventRepository repository;


    public IniciarJuegoUseCase(JuegoDomainEventRepository repository) {
        this.repository = repository;

    }

    @Override
    public Flux<DomainEvent> apply(Mono<IniciarJuegoCommand> iniciarJuegoCommand) {
        return iniciarJuegoCommand.flatMapMany((command) -> repository
                .obtenerEventosPor(command.getJuegoId())
                .collectList()
                .flatMapIterable(events -> {
                    var juego = Juego.from(JuegoID.of(command.getJuegoId()), events);
                    var jugadoresId = juego.jugadores().keySet();
                    var ronda = new Ronda(1, jugadoresId);

                    juego.crearTablero();
                    juego.crearRonda(ronda, 80);

                    return juego.getUncommittedChanges();
                }));
    }

}
