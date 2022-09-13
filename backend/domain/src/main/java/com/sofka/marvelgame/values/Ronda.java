package com.sofka.marvelgame.values;

import co.com.sofka.domain.generic.ValueObject;

import java.util.Set;
import java.util.logging.Logger;

public class Ronda implements ValueObject<Ronda.Props> {

    private final Logger log = Logger.getLogger("Logger");
    private final Set<JugadorID> jugadores;
    private Boolean estaIniciada;
    private final Integer numero;

    public Ronda(Integer numero, Set<JugadorID> jugadores) {
        this.jugadores = jugadores;
        this.numero = numero;
        this.estaIniciada = false;
    }

    private Ronda(Set<JugadorID> jugadores, Integer numero, Boolean estaIniciada) {
        this.jugadores = jugadores;
        this.numero = numero;
        this.estaIniciada = estaIniciada;
    }

    public Ronda iniciarRonda(){
        return new Ronda(jugadores, numero, true);
    }

    public Ronda terminarRonda(){
        return new Ronda(jugadores, numero, false);
    }

    public Ronda incrementarRonda(Set<JugadorID> jugadores){
        log.info("Nueva ronda creada en el tablero");
        return new Ronda(jugadores,this.numero + 1, false);
    }

    @Override
    public Props value() {
        return new Props(){
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
