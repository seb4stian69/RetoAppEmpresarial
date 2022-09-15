package com.sofka.marvelgame.commands;

import co.com.sofka.domain.generic.Command;
import com.sofka.marvelgame.values.JuegoID;
import com.sofka.marvelgame.values.JugadorID;
import lombok.*;
import java.util.Set;

@Getter@Setter /**/ @AllArgsConstructor@NoArgsConstructor
public class CrearHistorico extends Command {

    private Set<JugadorID> jugadorId;
    private JuegoID juegoId;

}
