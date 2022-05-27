package com.kronostudios.the_game.cards;

import com.kronostudios.the_game.core.Effect;
import com.kronostudios.the_game.models.Card;
import com.kronostudios.the_game.models.Stats;

import java.io.Serializable;

public class Torpedo extends Card implements Serializable {

    private static final long id = 5;
    private static final String name = "Torpedo";
    private static final String description = "Shoots all enemies 4 dmg + Str";
    private static final Stats statsRequired = new Stats(0,0,0);
    private static int baseDamage = 4;

    public Torpedo() {
        super(Torpedo.id, Torpedo.name, Torpedo.description, Torpedo.statsRequired);
    }

    public Torpedo(long id, String name, String description) {
        super(id, name, description, Torpedo.statsRequired);
    }

    @Override
    public Effect effect(Stats stats) {
        int amount = baseDamage + getDamage(stats.getStr());
        return new Effect(amount,Effect.AOEDMGEFFECT);
    }


    @Override
    public Effect altEffect(Stats stats) {
        //no alt effects on this card
        return null;
    }
}
