package com.sofka.marvelgame.values;

import co.com.sofka.domain.generic.ValueObject;

import java.util.Set;
import java.util.logging.Logger;

public class Ronda implements ValueObject<Ronda.Props> {

    private final Set<JugadorID> jugadores;
    private final Integer numero;
    private final Boolean estaIniciada;

    public Ronda(Integer numero, Set<JugadorID> jugadores) {
        this.jugadores = jugadores;
        this.numero = numero;
        this.estaIniciada = false;
    }

    private Ronda(Integer numero, Set<JugadorID> jugadores, Boolean estaIniciada) {
        this.jugadores = jugadores;
        this.numero = numero;
        this.estaIniciada = estaIniciada;
    }

    public Ronda iniciarRonda(){
        return new Ronda(this.numero, this.jugadores, true);
    }

    public Ronda terminarRonda(){
        return new Ronda(this.numero, this.jugadores, false);
    }

    //public Ronda
    public Ronda incrementarRonda(Set<JugadorID> jugadores){
        return new Ronda(this.numero + 1, jugadores, false);
    }

    @Override
    public Props value() {
        return new Props() {
            @Override
            public Set<JugadorID> jugadores() {
                return jugadores;
            }

            @Override
            public Integer numero() {
                return numero;
            }

            @Override
            public Boolean estaIniciada() {
                return estaIniciada;
            }
        };
    }

    public interface Props {
        Set<JugadorID> jugadores();
        Integer numero();
        Boolean estaIniciada();
    }

}
