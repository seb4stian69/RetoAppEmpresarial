package com.sofka.marvelgame.values;

import co.com.sofka.domain.generic.Identity;

public class JugadorID extends Identity {

    private JugadorID (String id){
        super(id);
    }

    public static JugadorID of(String id){
        return new JugadorID(id);
    }

}
