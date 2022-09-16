package org.example.cardgame.application.handle;

import com.sofka.marvelgame.commands.CrearJuegoCommand;
import com.sofka.marvelgame.commands.IniciarJuegoCommand;
import com.sofka.marvelgame.commands.IniciarRondaCommand;
import com.sofka.marvelgame.commands.PonerCartaEnTablero;
import com.sofka.marvelgame.commands.CrearRondaCommand;
import com.sofka.marvelgame.usecase.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class CommandHandle {

    private final IntegrationHandle integrationHandle;
    private final ErrorHandler errorHandler;

    public CommandHandle(IntegrationHandle integrationHandle, ErrorHandler errorHandler) {
        this.integrationHandle = integrationHandle;
        this.errorHandler = errorHandler;
    }

    @Bean RouterFunction<ServerResponse> crear(CrearJuegoUseCase usecase) {
        return route(
                POST("/juego/crear").and(accept(MediaType.APPLICATION_JSON)),
                request -> usecase.andThen(integrationHandle)
                        .apply(request.bodyToMono(CrearJuegoCommand.class))
                        .then(ServerResponse.ok().build())
                        .onErrorResume(errorHandler::badRequest)

        );
    }

    @Bean RouterFunction<ServerResponse> iniciar(IniciarJuegoUseCase usecase) {
        return route(
                POST("/juego/iniciar").and(accept(MediaType.APPLICATION_JSON)),
                request -> usecase.andThen(integrationHandle)
                        .apply(request.bodyToMono(IniciarJuegoCommand.class))
                        .then(ServerResponse.ok().build())
                        .onErrorResume(errorHandler::badRequest)

        );
    }

    @Bean RouterFunction<ServerResponse> iniciarRonda(IniciarRondaUseCase usecase) {
        return route(
                POST("/juego/ronda/iniciar").and(accept(MediaType.APPLICATION_JSON)),
                request -> usecase.andThen(integrationHandle)
                        .apply(request.bodyToMono(IniciarRondaCommand.class))
                        .then(ServerResponse.ok().build())
                        .onErrorResume(errorHandler::badRequest)

        );
    }

    @Bean RouterFunction<ServerResponse> poner(PonerCartaEnTableroUseCase usecase) {
        return route(
                POST("/juego/poner").and(accept(MediaType.APPLICATION_JSON)),
                request -> usecase.andThen(integrationHandle)
                        .apply(request.bodyToMono(PonerCartaEnTablero.class))
                        .then(ServerResponse.ok().build())
                        .onErrorResume(errorHandler::badRequest)

        );
    }

    @Bean RouterFunction<ServerResponse> crearRonda(CrearRondaUseCase usecase) {
        return route(
                POST("/juego/crear/ronda").and(accept(MediaType.APPLICATION_JSON)),
                request -> usecase.andThen(integrationHandle)
                        .apply(request.bodyToMono(CrearRondaCommand.class))
                        .then(ServerResponse.ok().build())
                        .onErrorResume(errorHandler::badRequest)

        );
    }

}
