package org.example.cardgame.application.handle.materialize;

import com.sofka.marvelgame.events.JuegoFinalizado;
import org.springframework.context.annotation.Configuration;
import co.com.sofka.domain.generic.DomainEvent;
import com.sofka.marvelgame.events.JuegoCreado;
import com.sofka.marvelgame.events.JugadorAgregado;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.time.Instant;
import java.util.HashMap;

@Configuration
public class GameMaterializeHandle {

    private static final String COLLECTION_VIEW = "gameview";

    private final ReactiveMongoTemplate template;

    public GameMaterializeHandle(ReactiveMongoTemplate template) {
        this.template = template;
    }

    @EventListener
    public void handleJuegoCreado(JuegoCreado event) {
        var data = new HashMap<>();
        data.put("_id", event.aggregateRootId());
        data.put("fecha", Instant.now());
        data.put("uid", event.getJugadorPrincipal().value());
        data.put("iniciado", false);
        data.put("finalizado", false);
        data.put("cantidadJugadores", 0);
        data.put("jugadores", new HashMap<>());
        template.save(data, COLLECTION_VIEW).block();
    }

    @EventListener
    public void handleJugadorAgregado(JugadorAgregado event) {
        var data = new Update();
        data.set("fecha", Instant.now());
        data.set("jugadores."+event.getJuegoId().value()+".alias", event.getAlias());
        data.set("jugadores."+event.getJuegoId().value()+".jugadorId", event.getJuegoId().value());
        data.inc("cantidadJugadores");
        template.updateFirst(getFilterByAggregateId(event), data, COLLECTION_VIEW).block();
    }

    @EventListener
    public void handleJuegoIniciado(Juego event)

    @EventListener
    public void handleJuegoFinalizado(JuegoFinalizado event){
        var data = new Update();
        data.set("finalizado", true);
        //data.set("ganador", "jugadores."+event.getJugadorId().value()+".alias", event.getAlias());
    }

    private Query getFilterByAggregateId(DomainEvent event) {
        return new Query(
                Criteria.where("_id").is(event.aggregateRootId())
        );
    }

}