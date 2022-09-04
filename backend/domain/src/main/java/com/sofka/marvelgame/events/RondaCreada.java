package com.sofka.marvelgame.events;

import co.com.sofka.domain.generic.DomainEvent;
import com.sofka.marvelgame.values.Ronda;

public class RondaCreada extends DomainEvent {
    private final Ronda ronda;
    private final Integer tiempo;

    public RondaCreada(Ronda ronda, Integer tiempo) {
        super("com.sofka.marvelgame.RondaCreada");
        this.ronda = ronda;
        this.tiempo = tiempo;
    }

    public Ronda getRonda() {
        return ronda;
    }

    public Integer getTiempo() {
        return tiempo;
    }
}
