package com.sofka.marvelgame.events;

import co.com.sofka.domain.generic.DomainEvent;
import com.sofka.marvelgame.values.Carta;
import com.sofka.marvelgame.values.JugadorID;

public class CartaQuitadaDelMazo extends DomainEvent {
    private final JugadorID jugadorId;
    private final Carta carta;

    public CartaQuitadaDelMazo(JugadorID jugadorId, Carta carta) {
        super("com.sofka.marvelgame.CartaQuitadaDelMazo");
        this.jugadorId = jugadorId;
        this.carta = carta;
    }

    public JugadorID getJugadorId() {
        return jugadorId;
    }

    public Carta getCarta() {
        return carta;
    }
}
