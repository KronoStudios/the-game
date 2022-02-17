package com.kronostudios.the_game.models;

public abstract class Card {
    private static String id;
    private static String name;
    private static String description;
    private static Stats statsRequired;


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

    public Stats getStatsRequired() {
        return statsRequired;
    }

    public void setStatsRequired(Stats statsRequired) {
        this.statsRequired = statsRequired;
    }

    public abstract void effect();
    public abstract void altEffect();
}
