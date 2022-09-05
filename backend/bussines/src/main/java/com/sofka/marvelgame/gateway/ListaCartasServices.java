package com.sofka.marvelgame.gateway;

import reactor.core.publisher.Flux;

public interface ListaCartasServices {
    Flux<ModelCartas> obtenerCartasDeMarvel();
}
