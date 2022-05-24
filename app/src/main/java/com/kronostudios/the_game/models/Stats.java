package com.kronostudios.the_game.models;

import java.io.Serializable;

/**
 * @author: gcaballe
 *
 * The Stats of a character are strength, intellect and stamina.
 * Stamina is the Health of the char, while strenght and intellect empower his abilities.
 */
public class Stats implements Serializable {
    private int str;

    public Stats(int str, int intel, int sta) {
        this.str = str;
        this.intel = intel;
        this.sta = sta;
    }

    private int intel;
    private int sta;

    public int getStr() {
        return str;
    }

    public void setStr(int str) {
        this.str = str;
    }

    public int getIntel() {
        return intel;
    }

    public void setIntel(int intel) {
        this.intel = intel;
    }

    public int getSta() {
        return sta;
    }

    public void setSta(int sta) {
        this.sta = sta;
    }
}
