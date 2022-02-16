package com.kronostudios.the_game.model;

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

    public Character getExecuter() {
        return executor;
    }

    public void setExecuter(Character executer) {
        this.executor = executer;
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
