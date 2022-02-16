package com.kronostudios.the_game.model;

import java.util.List;

public class Player {
    private User user;
    private Build build;
    private List<Card> hand;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public List<Card> draw(){
        if(hand.size() < 3){
            while (hand.size()<3){
                if(build.getDeck().getCards().size() == 0){
                    build.getDeck().refillDeck();
                }
                build.getDeck().shuffle();
                Card draw = build.getDeck().getCards().remove(0);
                hand.add(draw);
            }
        }
        return hand;
    }

    public void useCard(Card c){
        hand.remove(c);
        build.getDeck().getCardsDrawn().add(c);
    }
}
