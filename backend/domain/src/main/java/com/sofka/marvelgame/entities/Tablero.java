package com.sofka.marvelgame.entities;

import co.com.sofka.domain.generic.Entity;
import com.sofka.marvelgame.values.*;
import lombok.Getter;
import lombok.Setter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Getter/* */@Setter
public class Tablero extends Entity<TableroID> {

    private Integer tiempoEnSegundos;
    private Boolean estaHabilitado;
    private final Map<JugadorID, Set<Carta>> partida;

    public Tablero(TableroID entityId, Set<JugadorID> jugadorIds) {
        super(entityId);
        this.partida = new HashMap<>();
        this.estaHabilitado = false;
        jugadorIds.forEach(jugadorId -> partida.put(jugadorId, new HashSet<>()));
    }

    public void ajustarTiempo(Integer tiempo){
        this.tiempoEnSegundos = tiempo;
    }


    public Integer tiempo() {
        return tiempoEnSegundos;
    }

    public void adicionarPartida(JugadorID jugadorId, Carta carta){
        partida.getOrDefault(jugadorId, new HashSet<>()).add(carta);
    }

    public void quitarCarta(JugadorID jugadorId, Carta carta){
        partida.getOrDefault(jugadorId, new HashSet<>()).remove(carta);
    }

    public void habilitarApuesta(){
        this.estaHabilitado = true;
    }

    public void inhabilitarApuesta(){
        this.estaHabilitado = false;
    }

    public void reiniciarPartida(){
        partida.clear();
    }

    public Boolean estaHabilitado() {
        return estaHabilitado;
    }

    public Map<JugadorID, Set<Carta>> partida() {
        return partida;
    }

}
