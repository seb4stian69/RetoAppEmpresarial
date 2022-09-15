package com.sofka.marvelgame;

import co.com.sofka.domain.generic.EventChange;
import com.sofka.marvelgame.entities.Jugador;
import com.sofka.marvelgame.entities.Tablero;
import com.sofka.marvelgame.events.*;
import com.sofka.marvelgame.values.JugadorID;
import java.util.HashMap;
import java.util.Objects;
import java.util.Set;

public class JuegoChanged extends EventChange {
    public JuegoChanged(Juego juego) {

        apply((JuegoCreado event) -> {
            juego.jugadores = new HashMap<>();
            juego.jugadorPrincipal = event.getJugadorPrincipal();
        });

        apply((JugadorAgregado event) -> {
            juego.jugadores.put(event.getJuegoId(),
                    new Jugador(event.getJuegoId(), event.getAlias(), event.getPuntos(), event.getMazo())
            );
        });

        apply((RondaCreada event) -> {
            if(Objects.isNull(juego.tablero)){
                throw new IllegalArgumentException("Debe existir el tablero primero");
            }
            juego.ronda = event.getRonda();
            juego.tablero.ajustarTiempo(event.getTiempo());
        });

        apply((TableroCreado event) -> {
            juego.tablero = new Tablero(event.getTableroId(), event.getJugadorIds());
        });

        apply((TiempoCambiadoDelTablero event) -> {
            juego.tablero.ajustarTiempo(event.getTiempo());
        });

        apply((CartaPuestaEnTablero event) -> {
            juego.tablero.adicionarPartida(event.getJugadorId(), event.getCarta());
        });

        apply((CartaQuitadaDelTablero event) -> {
            juego.tablero.quitarCarta(event.getJugadorId(), event.getCarta());
        });

        apply((CartaQuitadaDelMazo event) -> {
            juego.jugadores.get(event.getJugadorId()).quitarCartaDeMazo(event.getCarta());
        });

        apply((RondaIniciada event) -> {

            if(Objects.isNull(juego.ronda)){
                throw new IllegalArgumentException("Debe existir una runda creada");
            }

            juego.ronda = juego.ronda.iniciarRonda();
            juego.tablero.habilitarApuesta();

        });

        apply((RondaTerminada event) -> {
            juego.ronda = juego.ronda.terminarRonda();
            juego.tablero.inhabilitarApuesta();
        });

        apply((CartasAsignadasAJugador event) -> {
            var jugador =  juego.jugadores().get(event.getGanadorId());
            event.getCartasApuesta().forEach(jugador::agregarCartaAMazo);
        });

        apply((JuegoFinalizado event) -> {

            juego.ganador = juego.jugadores.get(event.getJugadorId());


            Set<JugadorID> jugadores = null;

            juego.jugadores.values().stream().forEach(Jugador ->{

                if(Objects.isNull(Jugador)){
                    throw new IllegalArgumentException("No se envio ningun jugador");
                }

                jugadores.add(Jugador.identity());

            });

            new HistoricoCreado(jugadores, juego.identity());

        });

    }

}
