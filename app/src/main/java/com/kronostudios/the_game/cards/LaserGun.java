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
public class LaserGun extends Card implements Serializable {

    private static final long id = 1;
    private static final String name = "Laser Gun";
    private static final String description = "Deal 6 dmg + int";
    private static final Stats statsRequired = new Stats(0,0,0);
    private static int baseDamage = 6;

    public LaserGun() {
        super(com.kronostudios.the_game.cards.LaserGun.id, com.kronostudios.the_game.cards.LaserGun.name, com.kronostudios.the_game.cards.LaserGun.description, com.kronostudios.the_game.cards.LaserGun.statsRequired);
    }

    public LaserGun(long id, String name, String description) {
        super(id, name, description, com.kronostudios.the_game.cards.LaserGun.statsRequired);
    }

    @Override
    public Effect effect(Stats stats) {
        int amount = baseDamage + getDamage(stats.getIntel());
        return new Effect(amount,Effect.DMGEFFECT);
    }


    @Override
    public Effect altEffect(Stats stats) {
        //no alt effects on this card
        return null;
    }
}
