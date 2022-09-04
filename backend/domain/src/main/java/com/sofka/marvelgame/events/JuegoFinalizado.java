package com.sofka.marvelgame.events;

import co.com.sofka.domain.generic.DomainEvent;
import com.sofka.marvelgame.values.JugadorID;

public class JuegoFinalizado extends DomainEvent {
    private final JugadorID jugadorId;
    private final String alias;

    public JuegoFinalizado(JugadorID jugadorId, String alias) {
        super("com.sofka.marvelgame.JuegoFinalizado");
        this.jugadorId = jugadorId;
        this.alias = alias;
    }

    public JugadorID getJugadorId() {
        return jugadorId;
    }

    public String getAlias() {
        return alias;
    }
}
