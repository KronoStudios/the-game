package com.kronostudios.the_game.core;

import com.kronostudios.the_game.models.Character;
import com.kronostudios.the_game.models.Stats;

/**
 * @author mfernandez
 *
 * CharacterIG class represents a Character in a game.
 * Has the attributes maxHealth, health and shield
 */
public class CharacterIG extends Character {
    private int health;
    private int maxHealth;
    private int shield;

    public CharacterIG(String id, String name, Stats stats) {
        super(id, name, stats);
        this.health = this.getStats().getSta();
        this.maxHealth = this.getStats().getSta();
        this.shield = 0;
    }

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
