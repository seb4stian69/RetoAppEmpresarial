package com.sofka.marvelgame.gateway;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data /**/ @Getter @Setter
public class ModelCartas {

    private String id;
    private String nombre;
    private String uri;
    private Integer poder;

}
