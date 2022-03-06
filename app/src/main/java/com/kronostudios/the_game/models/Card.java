package com.kronostudios.the_game.models;

import com.kronostudios.the_game.core.Effect;

/**
 * @author: gperez
 *
 * The Fireball class is a basic effect Card.
 * Deals baseDamage amount of health to the target,
 * amplified by the Stat intel of the executor.
 *
 */
public abstract class Card {
    /**
     *
     * Damage formula is 0,3 * stat + damage
     * **/
    private static String id;
    private static String name;
    private static String description;
    private static Stats statsRequired;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Stats getStatsRequired() {
        return statsRequired;
    }

    public int getDamage(int value){
        Double dmg = value * 0.3;
        return dmg.intValue();
    }

    public void setStatsRequired(Stats statsRequired) {
        this.statsRequired = statsRequired;
    }

    public abstract Effect effect(Stats stats);
    public abstract Effect altEffect(Stats stats);
}
