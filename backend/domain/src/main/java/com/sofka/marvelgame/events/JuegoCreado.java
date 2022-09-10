package com.sofka.marvelgame.events;

import co.com.sofka.domain.generic.DomainEvent;
import com.sofka.marvelgame.values.JugadorID;

public class JuegoCreado extends DomainEvent {
    private final JugadorID jugadorPrincipal;
    public JuegoCreado(JugadorID jugadorPrincipal) {
        super("marvelgame.JuegoCreado");
        this.jugadorPrincipal = jugadorPrincipal;
    }

    public JugadorID getJugadorPrincipal() {
        return jugadorPrincipal;
    }
}
