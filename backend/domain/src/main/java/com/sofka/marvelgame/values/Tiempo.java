package com.sofka.marvelgame.values;

import co.com.sofka.domain.generic.ValueObject;
import java.sql.Time;

public class Tiempo implements ValueObject<Time> {

    private final Time tiempo;

    private Tiempo(Time tiempo) {
        this.tiempo = tiempo;
    }

    public static Tiempo of(Time tiempo) {return new Tiempo(tiempo);}

    @Override
    public Time value() {
        return tiempo;
    }

}
