package com.kronostudios.the_game.core;

import com.kronostudios.the_game.models.Card;
import com.kronostudios.the_game.models.Deck;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * @author mfernandez
 *
 * The DeckIG class represents the deck of a player in a game.
 * Consists of a list of Cards.
 *
 */
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

    /**
     * shuffle() sorts randomly the Stack<Cards> deck
     */
    public void shuffle(){
        Collections.shuffle(super.getCards());
    }

    /**
     * Returns the top card of a Deck
     *
     * @return Stack<Card> deck
     */
    public Card topDeck(){
        Stack<Card> aux = getCards();
        Card cardDraw = aux.remove(0);
        setCards(aux);
        return cardDraw;
    }

    /**
     * Refills the DeckIG of the current game  with all the cards in the original Deck.
     *
     * @return Stack<Card> deck
     */
    public Stack<Card> refillDeck(){
        Stack<Card> cards = new Stack<Card>();
        cards.addAll(cardsDrawn);
        super.setCards(cards);
        cardsDrawn = new ArrayList<Card>();
        return super.getCards();
    }
}
