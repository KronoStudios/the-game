package com.kronostudios.the_game.models;

import com.kronostudios.the_game.core.CharacterIG;

public class Character {
    private String id;
    private String name;
    private Stats stats;

    public Character(String id, String name, Stats stats) {
        this.id = id;
        this.name = name;
        this.stats = stats;
    }

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

    public CharacterIG getCharacterIG()
    {
        return new CharacterIG(this.id, this.name, this.stats);
    }

    public static Character getFakeCharacter(){
        Character c = new Character("001", "Pepe", new Stats(30,30,50));
        return c;
    }

}
