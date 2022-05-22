package com.kronostudios.the_game.core;

import com.kronostudios.the_game.activities.GameBoard;
import com.kronostudios.the_game.models.Card;
import com.kronostudios.the_game.models.Character;

/**
 * @author: mfernandez
 *
 * The Action class executes the effect of a card,
 * used by a Character executor applies to another
 * Character target.
 *
 */
public class Action {
    private Card card;



    private CharacterIG executor;
    private CharacterIG target;
    private GameBoard gameBoard;
    public Action() {}
    public Action(Card card, CharacterIG executor, CharacterIG target, GameBoard gameBoard) {
        this.card = card;
        this.executor = executor;
        this.target = target;
        this.gameBoard = gameBoard;
    }

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

    public void doAction() {
        Effect e = card.effect(getExecutor().getStats());
        e.executeEffect(executor, target, gameBoard);
    }
}
