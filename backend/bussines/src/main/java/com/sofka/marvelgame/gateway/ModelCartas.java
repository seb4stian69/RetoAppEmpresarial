package com.sofka.marvelgame.gateway;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data @AllArgsConstructor/**/ @Getter @Setter
public class ModelCartas {

    private String id;
    private String nombre;
    private String uri;
    private Integer poder;

}
