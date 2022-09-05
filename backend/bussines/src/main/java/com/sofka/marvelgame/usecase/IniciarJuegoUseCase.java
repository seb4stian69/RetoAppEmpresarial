package com.sofka.marvelgame.usecase;

import co.com.sofka.domain.generic.DomainEvent;
import com.sofka.marvelgame.Juego;
import com.sofka.marvelgame.commands.IniciarJuegoCommand;
import com.sofka.marvelgame.gateway.JuegoDomainEventRepository;
import com.sofka.marvelgame.values.JuegoID;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class IniciarJuegoUseCase extends UseCaseForCommand<IniciarJuegoCommand>{

    private final JuegoDomainEventRepository repository;

    public IniciarJuegoUseCase(JuegoDomainEventRepository repository){
        this.repository = repository;
    }

    @Override
    public Flux<DomainEvent> apply(Mono<IniciarJuegoCommand> iniciarJuegoCommand) {
        return iniciarJuegoCommand.flatMapMany((command) -> repository
                .obtenerEventosPor(command.getJuegoId())
                .collectList()
                .flatMapIterable(events -> {
                    var juego = Juego.from(JuegoID.of(command.getJuegoId()), events);
                    juego.crearTablero(command.getJuegoId());
                    return juego.getUncommittedChanges();
                }));
    }

}
