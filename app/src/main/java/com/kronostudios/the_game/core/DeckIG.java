package com.kronostudios.the_game.core;

import com.kronostudios.the_game.models.Card;
import com.kronostudios.the_game.models.Deck;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class DeckIG extends Deck {

    private List<Card> cardsDrawn;

    public DeckIG(String id, Stack<Card> cards) {
        super(id, cards);
        cardsDrawn = new ArrayList<>();
    }

    public List<Card> getCardsDrawn() {
        return cardsDrawn;
    }

    public void setCardsDrawn(List<Card> cardsDrawn) {
        this.cardsDrawn = cardsDrawn;
    }


    public void shuffle(){
        Collections.shuffle(super.getCards());
    }

    public Card topDeck(){
        return getCards().remove(0);
    }

    public Stack<Card> refillDeck(){
        Stack<Card> cards = new Stack<Card>();
        cards.addAll(cardsDrawn);
        super.setCards(cards);
        cardsDrawn = new ArrayList<Card>();
        return super.getCards();
    }
}
