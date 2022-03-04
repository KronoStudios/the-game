package com.kronostudios.the_game.cards;

import com.kronostudios.the_game.core.Action;
import com.kronostudios.the_game.core.Effect;
import com.kronostudios.the_game.core.Game;
import com.kronostudios.the_game.models.Card;
import com.kronostudios.the_game.models.Stats;

/**
 * @author: gperez
 *
 * The Fireball class is a basic effect Card.
 * Deals baseDamage amount of health to the target,
 * amplified by the Stat intel of the executor.
 *
 */
public class Fireball extends Card {

    private static final String id = "000001";
    private static final String name = "Fireball";
    private static final String description = "Throws a fireball doing 6 dmg + intelect";
    private static final Stats statsRequired = new Stats(0,0,0);
    private static int baseDamage = 6;

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
