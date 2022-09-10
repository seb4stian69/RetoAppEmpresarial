package com.sofka.marvelgame.events;

import co.com.sofka.domain.generic.DomainEvent;
import com.sofka.marvelgame.values.JugadorID;
import com.sofka.marvelgame.values.TableroID;

import java.util.Set;

public class TableroCreado extends DomainEvent {
    private final TableroID tableroId;
    private final Set<JugadorID> jugadorIds;



    public TableroCreado(TableroID tableroId, Set<JugadorID> jugadorIds) {
        super("marvelgame.TableroCreado");
        this.tableroId = tableroId;
        this.jugadorIds = jugadorIds;
    }

    public Set<JugadorID> getJugadorIds() {
        return jugadorIds;
    }

    public TableroID getTableroId() {
        return tableroId;
    }
}
