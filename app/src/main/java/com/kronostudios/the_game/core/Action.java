package com.kronostudios.the_game.core;

import com.kronostudios.the_game.models.Card;
import com.kronostudios.the_game.models.Character;

public class Action {
    private Card card;
    private Character executor;
    private Character target;

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public Character getExecutor() {
        return executor;
    }

    public void setExecutor(Character executor) {
        this.executor = executor;
    }

    public Character getTarget() {
        return target;
    }

    public void setTarget(Character target) {
        this.target = target;
    }


    public void doAction(){
        // TODO: execute single action
    }
}
