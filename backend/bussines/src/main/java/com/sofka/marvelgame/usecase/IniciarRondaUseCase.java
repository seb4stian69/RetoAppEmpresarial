package com.sofka.marvelgame.usecase;

import co.com.sofka.domain.generic.DomainEvent;
import com.sofka.marvelgame.Juego;
import com.sofka.marvelgame.commands.IniciarRondaCommand;
import com.sofka.marvelgame.gateway.JuegoDomainEventRepository;
import com.sofka.marvelgame.values.JuegoID;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class IniciarRondaUseCase extends UseCaseForCommand<IniciarRondaCommand> {
    private final JuegoDomainEventRepository repository;

    public IniciarRondaUseCase(JuegoDomainEventRepository repository) {
        this.repository = repository;
    }

    @Override
    public Flux<DomainEvent> apply(Mono<IniciarRondaCommand> iniciarRondaCommandMono) {
        return iniciarRondaCommandMono.flatMapMany((command)->repository
                .obtenerEventosPor(command.getJuegoId())
                .collectList()
                .flatMapIterable(event ->{
                    var juego = Juego.from(JuegoID.of(command.getJuegoId()),event);
                    juego.iniciarRonda();
                    return juego.getUncommittedChanges();
                }));
    }
}