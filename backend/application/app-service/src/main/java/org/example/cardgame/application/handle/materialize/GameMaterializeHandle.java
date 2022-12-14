package org.example.cardgame.application.handle.materialize;

import co.com.sofka.domain.generic.Identity;
import com.sofka.marvelgame.events.JuegoFinalizado;
import com.sofka.marvelgame.events.TableroCreado;
import org.springframework.context.annotation.Configuration;
import co.com.sofka.domain.generic.DomainEvent;
import com.sofka.marvelgame.events.JuegoCreado;
import com.sofka.marvelgame.events.JugadorAgregado;
import org.springframework.context.event.EventListener;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.bson.Document;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import java.time.Instant;
import java.util.HashMap;
import java.util.stream.Collectors;

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
    public void tableroCreado(TableroCreado event){

        var data = new Update();
        var gameView = new Update();

        gameView.set("iniciado", true);

        var doucment = new Document();
        var jugadores = event.getJugadorIds()
                .stream().map(Identity::value)
                .collect(Collectors.toList());

        doucment.put("tableroId", event.getTableroId().value());
        doucment.put("jugadores", jugadores);
        doucment.put("habilitado", true);
        doucment.put("cartas", new HashMap<>());
        doucment.put("cantidadJugadores", event.getJugadorIds().size());

        data.set("tablero", doucment);

        template.updateFirst(getByAggregateId(event), gameView, "gameview").block();
        template.updateFirst(getByAggregateId(event), data, COLLECTION_VIEW).block();


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
    public void handleJuegoFinalizado(JuegoFinalizado event){
        var data = new Update();
        data.set("finalizado", true);
        data.set("ganador."+event.getJugadorId().value()+".alias", event.getAlias());
        template.updateFirst(getFilterByAggregateId(event), data, COLLECTION_VIEW).block();
    }

    private Query getFilterByAggregateId(DomainEvent event) {
        return new Query(
                Criteria.where("_id").is(event.aggregateRootId())
        );
    }

    private Query getByAggregateId(DomainEvent event) {
        return new Query(
                Criteria.where("_id").is(event.aggregateRootId())
        );
    }

}

