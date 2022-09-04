package com.sofka.marvelgame.events;

import co.com.sofka.domain.generic.DomainEvent;
import com.sofka.marvelgame.values.TableroID;

public class TiempoCambiadoDelTablero extends DomainEvent {
    private final TableroID tableroId;
    private final Integer tiempo;

    public TiempoCambiadoDelTablero(TableroID tableroId, Integer tiempo) {
        super("com.sofka.marvelgame.TiempoCambiadoDelTablero");
        this.tableroId = tableroId;
        this.tiempo = tiempo;
    }

    public Integer getTiempo() {
        return tiempo;
    }

    public TableroID getTableroId() {
        return tableroId;
    }
}
