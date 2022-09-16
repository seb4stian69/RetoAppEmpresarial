package com.sofka.marvelgame.usecase;

import co.com.sofka.domain.generic.DomainEvent;

import com.sofka.marvelgame.Juego;
import com.sofka.marvelgame.commands.FinalizarRondaCommand;
import com.sofka.marvelgame.gateway.JuegoDomainEventRepository;
import com.sofka.marvelgame.values.Carta;
import com.sofka.marvelgame.values.JuegoID;
import com.sofka.marvelgame.values.JugadorID;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class FinalizarRondaUseCase extends UseCaseForCommand<FinalizarRondaCommand> {

    private final JuegoDomainEventRepository repository;

    public FinalizarRondaUseCase(JuegoDomainEventRepository repository){
        this.repository = repository;
    }

    @Override
    public Flux<DomainEvent> apply(Mono<FinalizarRondaCommand> finalizarRondaCommand) {
        return finalizarRondaCommand.flatMapMany((command) -> repository
                .obtenerEventosPor(command.getJuegoId())
                .collectList()
                .flatMapIterable(events -> {

                    var juego = Juego.from(JuegoID.of(command.getJuegoId()), events);
                    TreeMap<Integer, String> partidaOrdenada = new TreeMap<>((t1, t2) -> t2 - t1);
                    Set<Carta> cartasEnTablero = new HashSet<>();
                    juego.tablero().partida().forEach((jugadorId, cartas) -> {
                        cartas.stream()
                                .map(c -> c.value().poder())
                                .reduce(Integer::sum)
                                .ifPresent(puntos -> {
                                    partidaOrdenada.put(puntos, jugadorId.value());
                                    cartasEnTablero.add(cartas.iterator().next()); // El cambio mas mrk de todos
                                });

                    });

                    var competidores = partidaOrdenada.values()
                            .stream()
                            .map(JugadorID::of)
                            .collect(Collectors.toSet());
                    var partida =  partidaOrdenada.firstEntry();
                    var ganadorId = partida.getValue();
                    var puntos = partida.getKey();

                    juego.asignarCartasAGanador(JugadorID.of(ganadorId), puntos, cartasEnTablero);
                    juego.terminarRonda(juego.tablero().identity(), competidores);

                    return juego.getUncommittedChanges();
                }));
    }


}