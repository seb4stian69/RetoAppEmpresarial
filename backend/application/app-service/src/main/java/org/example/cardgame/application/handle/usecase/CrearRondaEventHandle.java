package org.example.cardgame.application.handle.usecase;

import co.com.sofka.domain.generic.Identity;
import com.sofka.marvelgame.commands.CrearRondaCommand;
import org.example.cardgame.application.handle.IntegrationHandle;
import com.sofka.marvelgame.events.RondaTerminada;
import com.sofka.marvelgame.usecase.CrearRondaUseCase;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import reactor.core.publisher.Mono;
import java.util.stream.Collectors;

@Configuration
public class CrearRondaEventHandle {
    private final CrearRondaUseCase usecase;

    private final IntegrationHandle handle;

    public CrearRondaEventHandle(CrearRondaUseCase usecase, IntegrationHandle handle) {
        this.usecase = usecase;
        this.handle = handle;
    }


    @Async
    @EventListener
    public void handleCrearRonda(RondaTerminada event) {
        var command = new CrearRondaCommand();
        var jugadores = event.getJugadorIds()
                .stream()
                .map(Identity::value)
                .collect(Collectors.toSet());
        command.setJuegoId(event.aggregateRootId());
        command.setTiempo(10);
        command.setJugadores(jugadores);
        handle.apply(usecase.apply(Mono.just(command))).block();
    }

}
