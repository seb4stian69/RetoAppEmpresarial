package org.example.cardgame.application.handle;

import org.example.cardgame.application.handle.model.JuegoListViewModel;
import org.example.cardgame.application.handle.model.MazoViewModel;
import org.example.cardgame.application.handle.model.TableroViewModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;

@Configuration
public class QueryHandle {

    private final ReactiveMongoTemplate template;

    public QueryHandle(ReactiveMongoTemplate template) {
        this.template = template;
    }

    @Bean
    public RouterFunction<ServerResponse> listarJuego() {
        return RouterFunctions.route(
                GET("/juego/listar/{id}"),
                request -> template.find(filterByUId(request.pathVariable("id")), JuegoListViewModel.class, "gameview")
                        .collectList()
                        .flatMap(list -> ServerResponse.ok()
                                .contentType(MediaType.APPLICATION_JSON)
                                .body(BodyInserters.fromPublisher(Flux.fromIterable(list), JuegoListViewModel.class)))
        );
    }


    @Bean
    public RouterFunction<ServerResponse> mazoPorJugador() {
        return RouterFunctions.route(
                GET("/jugador/mazo/{juegoId}/{uid}"),
                request -> template.findOne(filterByUIdJuego(request.pathVariable("juegoId"),request.pathVariable("uid")),
                                MazoViewModel.class, "mazoview")

                        .flatMap(list -> ServerResponse.ok()
                                .contentType(MediaType.APPLICATION_JSON)
                                .body(BodyInserters.fromPublisher(Mono.just(list), MazoViewModel.class)))
        );
    }

    private Query filterByUIdJuego( String juegoId,String uid) {
        return new Query(
                Criteria.where("juegoId").is(juegoId).and("uid").is(uid)
        );
    }


    @Bean
    public RouterFunction<ServerResponse> tableroPorJuego(){
        return RouterFunctions.route(
                GET("/juego/tablero/{juegoId}"),
                request ->template.findOne(filterById(request.pathVariable("juegoId")),
                                TableroViewModel.class,"boardview")
                        .flatMap(list -> ServerResponse.ok()
                                .contentType(MediaType.APPLICATION_JSON)
                                .body(BodyInserters.fromPublisher(Mono.just(list),TableroViewModel.class)))
        );
    }


    private Query filterById(String juegoId) {
        return new Query(
                Criteria.where("_id").is(juegoId)
        );
    }

    private Query filterByUId(String uid) {
        return new Query(
                Criteria.where("uid").is(uid)
        );
    }


}
