package com.sofka.marvelgame.values;

import co.com.sofka.domain.generic.ValueObject;

public class Email implements ValueObject<String> {

    private final String email;

    private Email(String email) {
        this.email = email;
    }

    public static Email of(String email) {
        return new Email(email);
    }

    @Override
    public String value() {
        return email;
    }

}
