package com.sofka.marvelgame.commands;

import co.com.sofka.domain.generic.Command;

import java.util.Set;

public class CrearRondaCommand extends Command {
    private String juegoId;
    private Integer tiempo;
    private Set<String> jugadores;


    public Set<String> getJugadores() {
        return jugadores;
    }

    public void setJugadores(Set<String> jugadores) {
        this.jugadores = jugadores;
    }

    public String getJuegoId() {
        return juegoId;
    }

    public void setJuegoId(String juegoId) {
        this.juegoId = juegoId;
    }

    public void setTiempo(Integer tiempo) {
        this.tiempo = tiempo;
    }

    public Integer getTiempo() {
        return tiempo;
    }
}
