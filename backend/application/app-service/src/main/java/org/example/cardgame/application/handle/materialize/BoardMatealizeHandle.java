package org.example.cardgame.application.handle.materialize;

import co.com.sofka.domain.generic.DomainEvent;
import co.com.sofka.domain.generic.Identity;
import org.bson.Document;
import com.sofka.marvelgame.events.RondaCreada;
import com.sofka.marvelgame.events.TableroCreado;
import com.sofka.marvelgame.events.TiempoCambiadoDelTablero;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import java.time.Instant;
import java.util.HashMap;
import java.util.stream.Collectors;

@Configuration
public class BoardMatealizeHandle {

    private static final String COLLECTION_VIEW = "boardview";

    private final ReactiveMongoTemplate template;

    public BoardMatealizeHandle(ReactiveMongoTemplate template) {
        this.template = template;
    }

    @EventListener
    public void handleTableroCreado(TableroCreado event) {

        var data = new HashMap<>();

        var tablero = new Document();

        var jugadores = event.getJugadorIds()
                .stream().map(Identity::value)
                .collect(Collectors.toList());

        data.put("_id", event.aggregateRootId());
        tablero.put("tableroId", event.getTableroId().value());
        tablero.put("jugadores", jugadores);
        tablero.put ("habilitado", true);
        tablero.put("cartas", new HashMap<>());
        data.put("tablero",tablero);

        template.save(data, COLLECTION_VIEW).block();

    }

    @EventListener
    public void handleRondaCreda(RondaCreada event) {

        var data = new Update();

        var ronda = event.getRonda().value();
        var rondaDoc = new Document();

        var jugadores = ronda.jugadores()
                .stream().map(Identity::value)
                .collect(Collectors.toList());

        rondaDoc.put("jugadores", jugadores);
        rondaDoc.put("numero", ronda.numero());
        rondaDoc.put("estaIniciada",event.getRonda().value().estaIniciada());
        rondaDoc.put("tiempo",event.getTiempo());

        data.set("ronda", rondaDoc);

        template.updateFirst(getByAggregateId(event),data, COLLECTION_VIEW).block();

    }

//    @EventListener
//    public void handleTiempoCambiadoDelTablero(TiempoCambiadoDelTablero event){
//        var data = new Update();
//        data.set("fecha", Instant.now());
//        data.set("tiempo", event.getTiempo());
//        data.set("ronda.estaIniciada", true);
//
//        template.updateFirst(getByAggregateId(event),data, COLLECTION_VIEW).block();
//    }

    private Query getByAggregateId(DomainEvent event) {
        return new Query(
                Criteria.where("_id").is(event.aggregateRootId())
        );
    }

}