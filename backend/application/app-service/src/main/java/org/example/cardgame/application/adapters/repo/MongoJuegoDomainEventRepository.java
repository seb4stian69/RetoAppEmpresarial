package org.example.cardgame.application.adapters.repo;

import co.com.sofka.domain.generic.DomainEvent;
import com.sofka.marvelgame.gateway.JuegoDomainEventRepository;
import org.example.cardgame.application.generic.EventStoreRepository;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
public class MongoJuegoDomainEventRepository implements JuegoDomainEventRepository {

    private final EventStoreRepository repository;

    public MongoJuegoDomainEventRepository(EventStoreRepository repository) {
        this.repository = repository;
    }

    @Override
    public Flux<DomainEvent> obtenerEventosPor(String id) {
        return repository.getEventsBy("game", id);
    }

}
