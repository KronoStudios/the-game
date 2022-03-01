package com.kronostudios.the_game.core;

public class Effect {

    public static final int DMGEFFECT = 1;
    public static final int HEALEFFECT = 2;
    public static final int SHIELDEFFECT = 3;
    private int ammount;
    private int effect;

    public Effect(int amount, int effect) {
        this.ammount = amount;
        this.effect = effect;
    }

    public int getAmmount() {
        return ammount;
    }

    public void setAmmount(int ammount) {
        this.ammount = ammount;
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

            case HEALEFFECT:

            case SHIELDEFFECT:

        }
    }
}
