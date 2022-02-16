package com.kronostudios.the_game.model;

import java.util.List;

public class Card {
    private String id;
    private String name;
    private String statsRequired;
    //efect
    //alt efect

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatsRequired() {
        return statsRequired;
    }

    public void setStatsRequired(String statsRequired) {
        this.statsRequired = statsRequired;
    }

}
