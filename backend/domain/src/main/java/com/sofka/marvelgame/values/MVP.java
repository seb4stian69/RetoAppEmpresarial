package com.sofka.marvelgame.values;

import co.com.sofka.domain.generic.ValueObject;

public class MVP implements ValueObject<MVP.Props> {

    private final JugadorID id;
    private final Puntos puntos;

    private MVP(JugadorID id, Puntos puntos) {
        this.id = id;
        this.puntos = puntos;
    }

    public static MVP of(JugadorID id,Puntos puntos){
        return new MVP(id,puntos);
    }

    @Override
    public Props value() {
        return new Props(){
            @Override
            public JugadorID id() {
                return id;
            }
            @Override
            public Puntos puntos() {
                return puntos;
            }
        };
    }

    public interface Props {
        JugadorID id();
        Puntos puntos();
    }
}
