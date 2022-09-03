package com.sofka.marvelgame.values;

import co.com.sofka.domain.generic.Identity;

public class CartaMaestraID extends Identity {

    private CartaMaestraID(String id) {
        super(id);
    }

    public static CartaMaestraID of(String id) {
        return new CartaMaestraID(id);
    }

}
