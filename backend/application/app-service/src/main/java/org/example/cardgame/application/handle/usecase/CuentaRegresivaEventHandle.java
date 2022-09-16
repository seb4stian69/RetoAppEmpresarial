package org.example.cardgame.application.handle.usecase;

import org.example.cardgame.application.handle.IntegrationHandle;
import com.sofka.marvelgame.events.RondaIniciada;
import com.sofka.marvelgame.usecase.IniciarCuentaRegresivaUseCase;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import reactor.core.publisher.Mono;

@Configuration
public class CuentaRegresivaEventHandle {

    private final IniciarCuentaRegresivaUseCase usecase;

    private final IntegrationHandle handle;

    public CuentaRegresivaEventHandle(IniciarCuentaRegresivaUseCase usecase, IntegrationHandle handle) {
        this.usecase = usecase;
        this.handle = handle;
    }

    @EventListener
    public void handleIniciarCuentaRegresiva(RondaIniciada event) {
        handle.apply(usecase.apply(Mono.just(event))).block();
        // usecase.andThen(handle).apply(Mono.just(event)).block();
    }

}
