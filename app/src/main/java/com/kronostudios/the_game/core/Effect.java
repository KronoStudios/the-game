package com.kronostudios.the_game.core;

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
                target.setHealth(target.getHealth() - amount);
            case HEALEFFECT:

            case SHIELDEFFECT:

        }
    }
}
