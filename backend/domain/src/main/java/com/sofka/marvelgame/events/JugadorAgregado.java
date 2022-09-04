package com.sofka.marvelgame.events;

import co.com.sofka.domain.generic.DomainEvent;
import com.sofka.marvelgame.values.*;

public class JugadorAgregado extends DomainEvent {
    private final JugadorID identity;
    private final Alias alias;

    private final Email email;
    private final Puntos puntosIniciales = Puntos.of(0);
    private final Mazo mazo;

    public JugadorAgregado(JugadorID identity, Email email, Alias alias, Mazo mazo) {
        super("com.sofka.marvelgame.JugadorAgregado");
        this.identity = identity;
        this.alias = alias;
        this.email = email;
        this.mazo = mazo;
    }

    public JugadorID getJuegoId() {
        return identity;
    }

    public Alias getAlias() {
        return alias;
    }

    public Puntos getPuntos(){return puntosIniciales;}

    public Email getEmail(){return email;}

    public Mazo getMazo() {
        return mazo;
    }
}
