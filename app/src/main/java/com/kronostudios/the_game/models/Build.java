package com.kronostudios.the_game.models;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author: gcaballe
 *
 * A build is a set of Deck and three characters, that a User will create to use in a game.
 */
public class Build {
    private String id;
    private String name;
    private User user;
    private Deck deck;
    private List<Character> characters;



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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Deck getDeck() {
        return deck;
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    public List<Character> getCharacters() {
        return characters;
    }

    public void setCharacters(List<Character> characters) {
        this.characters = characters;
    }

    public static Build getFakeBuild() {
        Build b = new Build();
        b.characters = Arrays.asList(
            //Character.getFakeCharacter(),
            //Character.getFakeCharacter(),
            //Character.getFakeCharacter()
            new Character("001", "Engineer", new Stats(15,15,50)),
            new Character("002", "Scientist", new Stats(0,30,50)),
            new Character("003", "Lieutnant", new Stats(10,0,50))
        );

        b.deck = Deck.getFakeDeck();
        b.id = "1";
        b.name = "Fake Build";
        return b;
    }

    public static Build getFakeBuild2() {
        Build b = new Build();
        b.characters = Arrays.asList(
                //Character.getFakeCharacter(),
                //Character.getFakeCharacter(),
                //Character.getFakeCharacter()
                new Character("001", "Engineer", new Stats(15,15,50)),
                new Character("002", "Scientist", new Stats(0,30,50)),
                new Character("003", "Lieutnant", new Stats(10,0,50))
        );

        b.deck = Deck.getFakeDeck2();
        b.id = "1";
        b.name = "Fake Build";
        return b;
    }

    @Override
    public String toString() {
        return name;
    }
}
