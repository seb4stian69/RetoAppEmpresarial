package com.sofka.marvelgame.entities;

import co.com.sofka.domain.generic.Entity;
import com.sofka.marvelgame.values.*;
import lombok.Getter;
import lombok.Setter;

@Getter/* */@Setter
public class Jugador extends Entity<JugadorID> {

    private final Alias alias;
    private final Email email;
    private final Puntos puntos;
    private Mazo mazo;

    public Jugador(JugadorID entityId, Alias alias, Email email, Puntos puntos, Mazo mazo) {
        super(entityId);
        this.alias = alias;
        this.email = email;
        this.puntos = puntos;
        this.mazo = mazo;
    }

    public void agregarCartaAMazo(Carta carta){
        mazo = mazo.agregarCarta(carta);
    }

    public void quitarCartaDeMazo(Carta carta){
        mazo = mazo.retirarCarta(carta);
    }

}
