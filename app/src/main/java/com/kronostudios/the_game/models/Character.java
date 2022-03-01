package com.kronostudios.the_game.models;

public class Character {
    private String id;
    private String name;
    private Stats stats;


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

    public Stats getStats() {
        return stats;
    }

    public void setStats(Stats stats) {
        this.stats = stats;
    }

    public static Character getFakeCharacter(){
        Character c = new Character();
        c.id = "001";
        c.name="Pepe";
        c.stats = new Stats(30,30,50);
        return c;
    }

}
