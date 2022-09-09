package org.example.cardgame.application.adapters.services;

import com.sofka.marvelgame.gateway.ListaCartasServices;
import com.sofka.marvelgame.gateway.ModelCartas;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
public class DataConsultarCartaMaestraService implements ListaCartasServices {

    private final ReactiveMongoTemplate template;

    public DataConsultarCartaMaestraService(ReactiveMongoTemplate template) {
        this.template = template;
    }

    @Override
    public Flux<ModelCartas> obtenerCartasDeMarvel() {
        return template.findAll(ModelCartas.class, "cards");
    }

}
