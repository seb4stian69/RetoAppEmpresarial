package com.sofka.marvelgame.values;

import co.com.sofka.domain.generic.ValueObject;

public class Puntos implements ValueObject<Integer> {

    private final Integer cantidad;

    private Puntos(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public static Puntos of(Integer cantidad) {return new Puntos(cantidad);}

    @Override
    public Integer value() {
        return cantidad;
    }

}
