package com.sofka.marvelgame.values;

import co.com.sofka.domain.generic.Identity;

public class JuegoID extends Identity {

    private JuegoID(String id) {
        super(id);
    }

    public static JuegoID of(String id) {
        return new JuegoID(id);
    }

}
