package com.kronostudios.the_game.cards;

import com.kronostudios.the_game.core.Effect;
import com.kronostudios.the_game.models.Card;
import com.kronostudios.the_game.models.Stats;

/**
 * @author: gcaballe
 *
 * The MotherCellSpray class is a aoe effect Card.
 * Heals amount of health to all allied characters,
 * amplified by the Stat intel of the executor.
 *
 */
public class MotherCellSpray extends Card {

    private static final long id = 1;
    private static final String name = "Mother Cell Spray";
    private static final String description = "Heals every allied character with 2 health points.";
    private static final Stats statsRequired = new Stats(0,0,0);

    public MotherCellSpray() {
        super(MotherCellSpray.id, MotherCellSpray.name, MotherCellSpray.description, MotherCellSpray.statsRequired);
    }

    public MotherCellSpray(long id, String name, String description) {
        super(id, name, description, MotherCellSpray.statsRequired);
    }

    @Override
    public Effect effect(Stats stats) {
        int amount = 2;
        return new Effect(amount,Effect.AOESHIELDEFFECT);
    }


    @Override
    public Effect altEffect(Stats stats) {
        //no alt effects on this card
        return null;
    }
}
