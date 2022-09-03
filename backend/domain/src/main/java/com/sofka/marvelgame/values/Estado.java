package com.sofka.marvelgame.values;

import co.com.sofka.domain.generic.ValueObject;

public class Estado implements ValueObject<Estado.estado> {

    private final estado estado;

    private Estado(Estado.estado estado) {
        this.estado = estado;
    }

    public static Estado of(Estado.estado estado){
        return new Estado(estado);
    }

    @Override
    public estado value() {
        return estado;
    }

    public enum estado {CREADO, INICIADO, TERMINADO}

}
