package com.kronostudios.the_game.cards;

import com.kronostudios.the_game.core.Effect;
import com.kronostudios.the_game.models.Card;
import com.kronostudios.the_game.models.Stats;

/**
 * @author: gcaballe
 *
 * The SonicShield class is a aoe effect Card.
 * Shields amount of health to the target,
 * amplified by the Stat intel of the executor.
 *
 */
public class SonicShield extends Card {

    private static final long id = 1;
    private static final String name = "Sonic Shield Grenade";
    private static final String description = "Throws a sonic grenade to an allied target, shielding all allied characters for 4 dmg + intellect.";
    private static final Stats statsRequired = new Stats(0,0,0);

    public SonicShield() {
        super(SonicShield.id, SonicShield.name, SonicShield.description, SonicShield.statsRequired);
    }

    public SonicShield(long id, String name, String description) {
        super(id, name, description, SonicShield.statsRequired);
    }

    @Override
    public Effect effect(Stats stats) {
        int amount = 4 + stats.getIntel();
        return new Effect(amount,Effect.AOESHIELDEFFECT);
    }


    @Override
    public Effect altEffect(Stats stats) {
        //no alt effects on this card
        return null;
    }
}
