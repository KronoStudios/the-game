package com.kronostudios.the_game.core;

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

    public void executeEffect(CharacterIG source, CharacterIG target){
        switch (effect){
            case DMGEFFECT:
                int finalHealth = target.getHealth() - amount;
                finalHealth = finalHealth<0?finalHealth=0:finalHealth;
                target.setHealth(finalHealth);
            case HEALEFFECT:

            case SHIELDEFFECT:

        }
    }
}
