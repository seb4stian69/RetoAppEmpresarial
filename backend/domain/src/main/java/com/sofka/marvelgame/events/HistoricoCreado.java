package com.sofka.marvelgame.events;

import co.com.sofka.domain.generic.DomainEvent;
import com.sofka.marvelgame.values.JuegoID;
import com.sofka.marvelgame.values.JugadorID;
import lombok.Getter;

import java.util.Set;

@Getter
public class HistoricoCreado extends DomainEvent {

    private final Set<JugadorID> jugadorId;
    private final JuegoID juegoId;

    public HistoricoCreado(Set<JugadorID> jugadorId, JuegoID juegoId){
        super("marvelgame.HistoricoCreado");
        this.jugadorId = jugadorId;
        this.juegoId = juegoId;
    }

}
