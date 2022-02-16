package com.kronostudios.the_game.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Deck {
    private String id;
    private List<Card> cards;
    private List<Card> cardsDrawn;

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

    public List<Card> getCardsDrawn() {
        return cardsDrawn;
    }

    public void setCardsDrawn(List<Card> cardsDrawn) {
        this.cardsDrawn = cardsDrawn;
    }
    public void shuffle(){
        Collections.shuffle(cards);
    }

    public List<Card> refillDeck(){
        cards = cardsDrawn;
        cardsDrawn = new ArrayList<Card>();
        return cards;
    }
}
