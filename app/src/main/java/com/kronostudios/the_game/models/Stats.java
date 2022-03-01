package com.kronostudios.the_game.models;

public class Stats {
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
