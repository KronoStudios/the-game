package com.kronostudios.the_game.core;

import com.kronostudios.the_game.models.Character;

public class CharacterIG extends Character {
    private int health;
    private int maxHealth;
    private int shield;

    //mana
    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public int getShield() {
        return shield;
    }

    public void setShield(int shield) {
        this.shield = shield;
    }
}
