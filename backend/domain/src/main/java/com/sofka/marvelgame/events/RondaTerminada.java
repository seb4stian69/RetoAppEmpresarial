package com.sofka.marvelgame.events;

import co.com.sofka.domain.generic.DomainEvent;
import com.sofka.marvelgame.values.JugadorID;
import com.sofka.marvelgame.values.TableroID;

import java.util.Set;

public class RondaTerminada extends DomainEvent {
    private final TableroID tableroId;
    private final Set<JugadorID> jugadorIds;

    public RondaTerminada(TableroID tableroId, Set<JugadorID> jugadorIds) {
        super("com.sofka.marvelgame.RondaTerminada");
        this.tableroId = tableroId;
        this.jugadorIds = jugadorIds;
    }

    public TableroID getTableroId() {
        return tableroId;
    }

    public Set<JugadorID> getJugadorIds() {
        return jugadorIds;
    }
}
