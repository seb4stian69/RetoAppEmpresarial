package com.sofka.marvelgame.gateway;

import co.com.sofka.domain.generic.DomainEvent;
import reactor.core.publisher.Flux;

public interface JuegoDomainEventRepository {
    Flux<DomainEvent> obtenerEventosPor(String id);
}