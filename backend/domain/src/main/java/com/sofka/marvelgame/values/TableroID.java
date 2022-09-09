package com.sofka.marvelgame.values;

import co.com.sofka.domain.generic.Identity;

public class TableroID extends Identity {

    private TableroID(String id) {super(id);}
    public TableroID(){/*Void Constructor*/}
    public static TableroID of(String id){return new TableroID(id);}

}
