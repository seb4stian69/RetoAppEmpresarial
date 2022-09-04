package com.sofka.marvelgame.commands;

import co.com.sofka.domain.generic.Command;
import com.sofka.marvelgame.values.JuegoID;
import com.sofka.marvelgame.values.TableroID;


public class CambiarTiempoCommand extends Command {
    private JuegoID juegoId;
    private TableroID tableroId;
    private Integer tiempo;

    public Integer getTiempo() {
        return tiempo;
    }

    public void setJuegoId(JuegoID juegoId) {
        this.juegoId = juegoId;
    }

    public JuegoID getJuegoId() {
        return juegoId;
    }

    public void setTiempo(Integer tiempo) {
        this.tiempo = tiempo;
    }

    public TableroID getTableroId() {
        return tableroId;
    }

    public void setTableroId(TableroID tableroId) {
        this.tableroId = tableroId;
    }
}
