package com.kronostudios.the_game.models;

import com.kronostudios.the_game.cards.Fireball;
import com.kronostudios.the_game.core.CharacterIG;
import com.kronostudios.the_game.core.DeckIG;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * @author: gcaballe
 *
 * The Deck class i just a stack of Cards.
 */
public class Deck {
    private String id;
    private Stack<Card> cards;
    public String getId() {
        return id;
    }
    public DeckIG deckIG;

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

    /**
     * @author: gcaballe
     *
     * The FakeDeck is just a deck of nine Cards (all fireballs at the moment)
     * This function will only be used during the development process
     */
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
        if(deckIG == null){
            deckIG = new DeckIG(this.id, this.cards);
        }
        return  deckIG;
    }

}
