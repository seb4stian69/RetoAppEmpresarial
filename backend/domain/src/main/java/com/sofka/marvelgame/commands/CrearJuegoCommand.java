package com.sofka.marvelgame.commands;

import co.com.sofka.domain.generic.Command;

import java.util.Map;

public class CrearJuegoCommand extends Command {
    private String juegoId;
    private Map<String, String> jugadores;
    private String jugadorPrincipalId;

    public void setJugadores(Map<String, String> jugadores) {
        this.jugadores = jugadores;
    }

    public Map<String, String> getJugadores() {
        return jugadores;
    }

    public void setJugadorPrincipalId(String jugadorPrincipalId) {
        this.jugadorPrincipalId = jugadorPrincipalId;
    }

    public String getJugadorPrincipalId() {
        return jugadorPrincipalId;
    }

    public String getJuegoId() {
        return juegoId;
    }

    public void setJuegoId(String juegoId) {
        this.juegoId = juegoId;
    }
}
