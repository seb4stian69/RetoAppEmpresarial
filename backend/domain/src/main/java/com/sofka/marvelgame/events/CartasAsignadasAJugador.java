package com.sofka.marvelgame.events;

import co.com.sofka.domain.generic.DomainEvent;
import com.sofka.marvelgame.values.Carta;
import com.sofka.marvelgame.values.JugadorID;
import java.util.Set;

public class CartasAsignadasAJugador extends DomainEvent {
    private final JugadorID ganadorId;
    private final Integer puntos;
    private final Set<Carta> cartasApuesta;

    public CartasAsignadasAJugador(JugadorID ganadorId, Integer puntos, Set<Carta> cartasApuesta) {
        super("marvelgame.CartasAsignadasAJugador");
        this.ganadorId = ganadorId;
        this.puntos = puntos;
        this.cartasApuesta = cartasApuesta;
    }

    public Integer getPuntos() {
        return puntos;
    }

    public JugadorID getGanadorId() {
        return ganadorId;
    }

    public Set<Carta> getCartasApuesta() {
        return cartasApuesta;
    }
}
