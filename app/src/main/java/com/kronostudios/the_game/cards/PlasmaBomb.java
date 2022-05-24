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
public class PlasmaBomb extends Card implements Serializable {

    private static final long id = 1;
    private static final String name = "Plasma Bomb";
    private static final String description = "Throws a plasma bomb that affect all enemy targets doing 3 dmg + intellect";
    private static final Stats statsRequired = new Stats(0,0,0);
    private static int baseDamage = 3;

    public PlasmaBomb() {
        super(PlasmaBomb.id, PlasmaBomb.name, PlasmaBomb.description, PlasmaBomb.statsRequired);
    }

    public PlasmaBomb(long id, String name, String description) {
        super(id, name, description, PlasmaBomb.statsRequired);
    }

    @Override
    public Effect effect(Stats stats) {
        int amount = baseDamage + getDamage(stats.getIntel());
        return new Effect(amount,Effect.AOEDMGEFFECT);
    }


    @Override
    public Effect altEffect(Stats stats) {
        //no alt effects on this card
        return null;
    }
}
