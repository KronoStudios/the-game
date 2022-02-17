package com.kronostudios.the_game.core;

import com.kronostudios.the_game.models.Card;
import com.kronostudios.the_game.models.Deck;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DeckIG extends Deck {

    private List<Card> cardsDrawn;
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

    public List<Card> refillDeck(){
        super.setCards(cardsDrawn);
        cardsDrawn = new ArrayList<Card>();
        return super.getCards();
    }
}
