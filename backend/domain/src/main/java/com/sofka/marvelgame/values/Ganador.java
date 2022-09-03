package com.sofka.marvelgame.values;

import co.com.sofka.domain.generic.ValueObject;

public class Ganador implements ValueObject<JugadorID> {

    private final JugadorID id;

    private Ganador(JugadorID id) {
        this.id = id;
    }

    public static Ganador of(JugadorID id) {
        return new Ganador(id);
    }

    @Override
    public JugadorID value() {
        return id;
    }

}
