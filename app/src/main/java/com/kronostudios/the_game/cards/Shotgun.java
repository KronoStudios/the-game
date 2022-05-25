package com.kronostudios.the_game.cards;

import com.kronostudios.the_game.core.Effect;
import com.kronostudios.the_game.models.Card;
import com.kronostudios.the_game.models.Stats;

import java.io.Serializable;

/**
 * @author: gperez
 *
 * The Fireball class is a basic effect Card.
 * Deals baseDamage amount of health to the target,
 * amplified by the Stat intel of the executor.
 *
 */
public class Shotgun extends Card implements Serializable {

    private static final long id = 6;
    private static final String name = "Shotgun";
    private static final String description = "Shoots a target dealing 5 dmg + Strength";
    private static final Stats statsRequired = new Stats(0,0,0);
    private static int baseDamage = 5;

    public Shotgun() {
        super(Shotgun.id, Shotgun.name, Shotgun.description, Shotgun.statsRequired);
    }

    public Shotgun(long id, String name, String description) {
        super(id, name, description, Shotgun.statsRequired);
    }

    @Override
    public Effect effect(Stats stats) {
        int amount = baseDamage + getDamage(stats.getStr());
        return new Effect(amount,Effect.DMGEFFECT);
    }


    @Override
    public Effect altEffect(Stats stats) {
        //no alt effects on this card
        return null;
    }
}
