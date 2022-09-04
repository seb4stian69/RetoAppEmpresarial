package com.sofka.marvelgame.events;

import co.com.sofka.domain.generic.DomainEvent;
import com.sofka.marvelgame.values.Carta;
import com.sofka.marvelgame.values.JugadorID;
import com.sofka.marvelgame.values.TableroID;


public class CartaPuestaEnTablero extends DomainEvent {
    private final TableroID tableroId;
    private final JugadorID jugadorId;
    private final Carta carta;

    public CartaPuestaEnTablero(TableroID tableroId, JugadorID jugadorId, Carta carta) {
        super("com.sofka.marvelgame.CartaPuestaEnTablero");
        this.tableroId = tableroId;
        this.jugadorId = jugadorId;
        this.carta = carta;
    }

    public TableroID getTableroId() {
        return tableroId;
    }

    public JugadorID getJugadorId() {
        return jugadorId;
    }

    public Carta getCarta() {
        return carta;
    }
}
