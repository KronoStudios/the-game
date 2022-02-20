package com.kronostudios.the_game.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private String id;
    private List<Card> cards;
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

}