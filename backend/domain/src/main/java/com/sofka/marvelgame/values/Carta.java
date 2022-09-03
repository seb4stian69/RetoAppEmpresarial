package com.sofka.marvelgame.values;

import co.com.sofka.domain.generic.ValueObject;

public class Carta implements ValueObject<Carta.Props>, Comparable<Carta>{

    private final CartaMaestraID cartaMaeestraID;
    private final Integer poder;
    private final Boolean estaHabilitada;

    public Carta(CartaMaestraID cartaMaeestraID, Integer poder, Boolean estaHabilitada) {
        this.cartaMaeestraID = cartaMaeestraID;
        this.poder = poder;
        this.estaHabilitada = estaHabilitada;
    }

    @Override
    public Props value() {
        return new Props(){
            @Override
            public CartaMaestraID cartaMaeestraID() {
                return cartaMaeestraID;
            }
            @Override
            public Integer poder() {
                return poder;
            }
            @Override
            public Boolean estaHabilitada() {
                return estaHabilitada;
            }
        };
    }

    @Override
    public int compareTo(Carta carta) {
        return (this.poder>carta.poder) ? 1 : 0; // 1 gana la actual - 0 gana la que se pasa por parametro
    }

    public interface Props {
        CartaMaestraID cartaMaeestraID();
        Integer poder();
        Boolean estaHabilitada();
    }

}
