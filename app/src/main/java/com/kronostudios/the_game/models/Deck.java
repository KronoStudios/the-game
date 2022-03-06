package com.kronostudios.the_game.models;

import com.kronostudios.the_game.cards.Fireball;
import com.kronostudios.the_game.core.DeckIG;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class Deck {
    private String id;
    private Stack<Card> cards;
    public String getId() {
        return id;
    }

    public Deck(String id, Stack<Card> cards) {
        this.id = id;
        this.cards = cards;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Stack<Card> getCards() {
        return cards;
    }

    public void setCards(Stack<Card> cards) {
        this.cards = cards;
    }

    public static Deck getFakeDeck(){
        Stack<Card> cards = new Stack<>();
        cards.push(new Fireball());
        cards.push(new Fireball());
        cards.push(new Fireball());
        cards.push(new Fireball());
        cards.push(new Fireball());
        cards.push(new Fireball());
        cards.push(new Fireball());
        cards.push(new Fireball());
        cards.push(new Fireball());

        return new Deck("1", cards);
    }

    public DeckIG getDeckIG() {
        return new DeckIG(this.id, this.cards);
    }

}
