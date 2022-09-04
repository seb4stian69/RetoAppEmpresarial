package com.sofka.marvelgame.events;

import co.com.sofka.domain.generic.DomainEvent;

public class RondaIniciada extends DomainEvent {
    public RondaIniciada() {
        super("com.sofka.marvelgame.RondaIniciada");
    }
}
