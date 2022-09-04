package com.sofka.marvelgame.entities;

import com.sofka.marvelgame.values.*;

import java.util.HashSet;
import java.util.Set;

public class JugadorFactory {
    private final Set<Jugador> jugadores;
    private final Puntos puntosIniciales = Puntos.of(0);

    public JugadorFactory(){
        jugadores = new HashSet<>();
    }
    public void agregarJugador(JugadorID jugadorId, Alias alias, Email email, Mazo mazo){
        jugadores.add(new Jugador(jugadorId, alias,email,this.puntosIniciales,mazo));
    }

    public Set<Jugador> getJugadores() {
        return jugadores;
    }
}
