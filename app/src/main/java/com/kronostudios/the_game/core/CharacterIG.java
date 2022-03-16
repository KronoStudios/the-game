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

    /**
     * Funció per centralitzar el damage dealt a un Character.
     * Té en compte el shield actual.
     *
     * @param amount
     */
    public void recieveDamage(int amount) {
        int shield = getShield();
        if(shield > 0) {
            if(shield >= amount) {
                //shield més gran. P.e: Shield de 5, incoming damage 2, diff = 3
                int diff = shield - amount;
                setShield(diff);
                amount = 0;
            }else {
                //shield més petit. P.e. Shield de 5, incoming damage 10, diff = 5
                int diff = amount - shield;
                setShield(0);
                amount = diff;
            }

        }
        int finalAmount = getHealth() - amount;
        if(finalAmount < 0 ) finalAmount = 0;
        setHealth(finalAmount);
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
