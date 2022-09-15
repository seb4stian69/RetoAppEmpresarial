package org.example.cardgame.application.handle.materialize;

import com.sofka.marvelgame.events.JuegoFinalizado;
import com.sofka.marvelgame.usecase.HistoricoCreado;
import org.springframework.context.event.EventListener;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import java.util.HashMap;

public class HistoricoMaterializeHandle {

    private static final String COLLECTION_VIEW = "historicoView";
    private final ReactiveMongoTemplate template;

    public HistoricoMaterializeHandle(ReactiveMongoTemplate template) {
        this.template = template;
    }

    @EventListener
    public void historicoCreado(JuegoFinalizado event){

        var data = new HashMap<>();
        var jugadores = event.getJugadorId();


    }

}
