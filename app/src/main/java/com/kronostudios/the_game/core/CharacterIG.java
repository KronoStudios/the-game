package com.kronostudios.the_game.core;

import androidx.databinding.Bindable;

import com.kronostudios.the_game.models.Character;
import com.kronostudios.the_game.models.Stats;

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

    @Bindable
    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
        notifyPropertyChanged(this.health);
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
