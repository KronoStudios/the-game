package com.kronostudios.the_game.core;

import com.kronostudios.the_game.activities.GameBoard;

/**
 * @author mfernandez
 *
 * The Effect class represents the effect that a Card executes on the game board.
 * This effect could be damaging a Character, shielding or healing a Character, ...
 */
public class Effect {

    public static final int DMGEFFECT = 1;
    public static final int HEALEFFECT = 2;
    public static final int SHIELDEFFECT = 3;
    public static final int AOEDMGEFFECT = 4;
    public static final int AOESHIELDEFFECT = 5;
    public static final int AOEHEALEFFECT = 6;
    private int amount;
    private int effect;

    public Effect(int amount, int effect) {
        this.amount = amount;
        this.effect = effect;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getEffect() {
        return effect;
    }

    public void setEffect(int effect) {
        this.effect = effect;
    }

    public void executeEffect(CharacterIG source, CharacterIG target, GameBoard gameBoard){
        switch (effect){
            case DMGEFFECT:
                /*int finalHealth = target.getHealth() - amount;
                finalHealth = finalHealth<0?finalHealth=0:finalHealth;
                target.setHealth(finalHealth);*/
                target.recieveDamage(amount);
                break;
            case HEALEFFECT:

                break;
            case SHIELDEFFECT:

                break;
            case AOEDMGEFFECT:
                for(CharacterIG ch : gameBoard.listEnemyChars){
                    ch.recieveDamage(amount);
                }

                break;
            case AOESHIELDEFFECT:
                for(CharacterIG ch : gameBoard.listPlayerChars){
                    ch.setShield(ch.getShield() + amount);
                }

                break;
            case AOEHEALEFFECT:
                for(CharacterIG ch : gameBoard.listPlayerChars){
                    ch.recieveHeal(amount);
                }
                break;
        }
    }
}
