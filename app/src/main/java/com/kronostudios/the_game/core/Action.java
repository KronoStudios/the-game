package com.kronostudios.the_game.core;

import com.kronostudios.the_game.models.Card;
import com.kronostudios.the_game.models.Character;

public class Action {
    private Card card;
    private CharacterIG executor;
    private CharacterIG target;

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public CharacterIG getExecutor() {
        return executor;
    }

    public void setExecutor(CharacterIG executor) {
        this.executor = executor;
    }

    public CharacterIG getTarget() {
        return target;
    }

    public void setTarget(CharacterIG target) {
        this.target = target;
    }


    public void doAction(){
        Effect e = card.effect(getExecutor().getStats());
        e.executeEffect(executor, target);
    }
}
