package com.sofka.marvelgame.values;

import co.com.sofka.domain.generic.ValueObject;

public class Alias implements ValueObject<String> {

    private final String alias;

    private Alias(String alias) {
        this.alias = alias;
    }

    public static Alias of(String alias) {
        return new Alias(alias);
    }

    @Override
    public String value() {
        return alias;
    }

    @Override
    public String toString() {
        return this.alias;
    }
}
