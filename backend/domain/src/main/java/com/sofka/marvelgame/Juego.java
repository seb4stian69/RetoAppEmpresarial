package com.sofka.marvelgame;

import co.com.sofka.domain.generic.AggregateEvent;
import co.com.sofka.domain.generic.DomainEvent;
import com.sofka.marvelgame.entities.Jugador;
import com.sofka.marvelgame.entities.JugadorFactory;
import com.sofka.marvelgame.entities.Tablero;
import com.sofka.marvelgame.events.*;
import com.sofka.marvelgame.values.*;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Juego extends AggregateEvent<JuegoID> {

    protected Map<JugadorID, Jugador> jugadores;
    protected Tablero tablero;
    protected Jugador ganador;
    protected Ronda ronda;
    protected JugadorID jugadorPrincipal;

    public Juego(JuegoID id, JugadorID uid, JugadorFactory jugadorFactory) {
        super(id);
        appendChange(new JuegoCreado(uid)).apply();
        jugadorFactory.getJugadores()
                .forEach(jugador ->
                        appendChange(new JugadorAgregado(jugador.identity(), jugador.getAlias() ,jugador.getMazo()))
                );
        subscribe(new JuegoChanged(this));
    }

    private Juego(JuegoID id){
        super(id);
        subscribe(new JuegoChanged(this));
    }

    public static Juego from(JuegoID juegoId, List<DomainEvent> events){
        var juego = new Juego(juegoId);
        events.forEach(juego::applyEvent);
        return juego;
    }

    public void crearTablero(String idReq){
        var id = TableroID.of(idReq);
        appendChange(new TableroCreado(id, jugadores.keySet())).apply();
    }

    public void crearTablero(){
        var id = new TableroID();
        appendChange(new TableroCreado(id, jugadores.keySet())).apply();
    }


    public void crearRonda(Ronda ronda, Integer tiempo){
        appendChange(new RondaCreada(ronda, tiempo)).apply();
    }

    public void cambiarTiempoDelTablero(TableroID tableroId, Integer tiempo){
        appendChange(new TiempoCambiadoDelTablero(tableroId, tiempo)).apply();
    }

    public void ponerCartaEnTablero(TableroID tableroId, JugadorID jugadorId, Carta carta){
        appendChange(new CartaPuestaEnTablero(tableroId, jugadorId, carta)).apply();
        appendChange(new CartaQuitadaDelMazo(jugadorId, carta)).apply();
    }

    public void quitarCartaEnTablero(TableroID tableroId, JugadorID jugadorId, Carta carta){
        appendChange(new CartaQuitadaDelTablero(tableroId, jugadorId, carta)).apply();
    }

    public void iniciarRonda() {
        appendChange(new RondaIniciada()).apply();
    }

    public void asignarCartasAGanador(JugadorID ganadorId, Integer puntos, Set<Carta> cartasApuesta) {
        appendChange(new CartasAsignadasAJugador(ganadorId,puntos, cartasApuesta)).apply();
    }
    public void terminarRonda(TableroID tableroId, Set<JugadorID> jugadorIds){
        appendChange(new RondaTerminada(tableroId, jugadorIds)).apply();
    }
    public void finalizarJuego(JugadorID jugadorId, String alias) {
        appendChange(new JuegoFinalizado(jugadorId, alias)).apply();
    }

    public Ronda ronda() {
        return ronda;
    }

    public Tablero tablero() {
        return tablero;
    }

    public Map<JugadorID, Jugador> jugadores() {
        return jugadores;
    }

}
