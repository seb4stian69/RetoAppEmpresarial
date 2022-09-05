package com.sofka.marvelgame.usecase;

import co.com.sofka.domain.generic.DomainEvent;
import com.sofka.marvelgame.Juego;
import com.sofka.marvelgame.commands.CrearJuegoCommand;
import com.sofka.marvelgame.entities.JugadorFactory;
import com.sofka.marvelgame.gateway.ListaCartasServices;
import com.sofka.marvelgame.gateway.ModelCartas;
import com.sofka.marvelgame.values.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.util.*;
import java.util.Collections;
import java.util.HashSet;
import java.util.stream.Collectors;

public class CrearJuegoUseCase extends UseCaseForCommand<CrearJuegoCommand>{

    private final ListaCartasServices listaDeCartaService;

    public CrearJuegoUseCase(ListaCartasServices listaDeCartaService) {
        this.listaDeCartaService = listaDeCartaService;    }

    @Override
    public Flux<DomainEvent> apply(Mono<CrearJuegoCommand> input) {

        return listaDeCartaService.obtenerCartasDeMarvel().collectList()
                .flatMapMany(cartas -> input.flatMapIterable(command -> {
                    var factory = new JugadorFactory();
                    command.getJugadores()
                            .forEach((id, alias) ->
                                    factory.agregarJugador(JugadorID.of(id), Alias.of(alias), generarMazo(cartas))
                            );
                    var juego = new Juego(
                            JuegoID.of(command.getJuegoId()),
                            JugadorID.of(command.getJugadorPrincipalId()),
                            factory
                    );
                    return juego.getUncommittedChanges();
                }));

    }
    private Mazo generarMazo(List<ModelCartas> cartas) {
        Collections.shuffle(cartas);
        var lista = cartas.stream().limit(5)
                .map(carta -> new Carta(CartaMaestraID.of(carta.getId()), carta.getPoder(), false))
                .collect(Collectors.toList());
        cartas.removeIf(cartaMaestra -> lista.stream().anyMatch(carta -> {
            var id = carta.value().cartaMaeestraID().value();
            return cartaMaestra.getId().equals(id);
        }));
        return new Mazo(new HashSet<>(lista));
    }

}
