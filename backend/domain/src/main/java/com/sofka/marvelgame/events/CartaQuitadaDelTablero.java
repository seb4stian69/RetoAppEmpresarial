package com.sofka.marvelgame.events;

import co.com.sofka.domain.generic.DomainEvent;
import com.sofka.marvelgame.values.Carta;
import com.sofka.marvelgame.values.JugadorID;
import com.sofka.marvelgame.values.TableroID;


public class CartaQuitadaDelTablero extends DomainEvent {
    private final TableroID tableroId;
    private final JugadorID jugadorId;
    private final Carta carta;

    public CartaQuitadaDelTablero(TableroID tableroId, JugadorID jugadorId, Carta carta) {
        super("marvelgame.CartaQuitadaDelTablero");
        this.tableroId = tableroId;
        this.jugadorId = jugadorId;
        this.carta = carta;
    }

    public Carta getCarta() {
        return carta;
    }

    public JugadorID getJugadorId() {
        return jugadorId;
    }

    public TableroID getTableroId() {
        return tableroId;
    }
}
