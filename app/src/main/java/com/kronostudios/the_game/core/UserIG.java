package com.kronostudios.the_game.core;

import com.kronostudios.the_game.models.Build;
import com.kronostudios.the_game.models.Card;
import com.kronostudios.the_game.models.User;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author mfernandez
 *
 * The UserIG class represents the player in a game.
 * Consists of his chosen build, a list of 3 Cards on his hand,
 * and his deck, with his remaining cards.
 */
public class UserIG extends User{
    private Build build;
    private List<Card> hand;
    private DeckIG deck;

    public UserIG(String id, String name, int rating, Build build) {
        super(id, name, rating);
        this.build = build;
        this.hand = new ArrayList<Card>();
        this.deck = build.getDeck().getDeckIG();
    }

    public Build getBuild() {
        return build;
    }

    public void setBuild(Build build) {
        this.build = build;
    }

    public List<Card> getHand() {
        return hand;
    }

    public void setHand(List<Card> hand) {
        this.hand = hand;
    }

    public DeckIG getDeck() {
        return deck;
    }


    /**
     * Draws cards from the deck and puts in into the players' hand until he has three.
     * Reshuffles the deck every time before drawing from the top of the stack.
     * If the deck is empty, refillsDeck() first.
     *
     * @return List<Card> hand;
     */
    public List<Card> draw() {
        if(hand.size() < 3){
            while (hand.size() < 3){
                if(deck.getCards().size() == 0){
                    deck.refillDeck();
                }
                deck.shuffle();
                Card draw = deck.topDeck();
                hand.add(draw);
            }
        }
        return hand;
    }

    /**
     * Called when executing an action. Removes the card from the current player's hand
     *
     * @param c Card
     */
    public void useCard(Card c){
        hand.remove(c);
        hand.removeAll(Collections.singleton(null));
        deck.getCardsDrawn().add(c);
    }
}
