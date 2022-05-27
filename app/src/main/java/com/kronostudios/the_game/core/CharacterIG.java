package com.kronostudios.the_game.core;

import android.os.Handler;
import android.view.View;
import android.widget.LinearLayout;

import com.kronostudios.the_game.R;
import com.kronostudios.the_game.activities.GameBoard;
import com.kronostudios.the_game.models.Character;
import com.kronostudios.the_game.models.Stats;

import pl.droidsonroids.gif.GifImageView;

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
    private LinearLayout characterLayout;

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

    public void recieveHeal(int amount) {

        //if dead, don't heal
        if(getHealth() == 0) return;

        int health = getHealth();
        int min = Math.min(health + amount, getMaxHealth());
        setHealth(min);
    }

    public void recieveShield(int amount) {
        setShield(getShield()+amount);
    }

    //mana
    public int getHealth() {
        return health;
    }

    public int getProgress(){
        double d = (double)health / maxHealth;
        int i = (int) (d*100);
        return i;
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


    public void playExplosionAnimation() {
        int charId = Integer.parseInt(this.getId());

        GifImageView giv = characterLayout.getChildAt(0).findViewById(R.id.character_explosion);
        giv.setVisibility(View.VISIBLE);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                giv.setVisibility(View.INVISIBLE);
            }
        }, 1000);
    }

    public void setCharacterLayout(LinearLayout characterLayout) {
        this.characterLayout = characterLayout;
    }

    public LinearLayout getCharacterLayout() {
        return characterLayout;
    }

    /**
     * Agafo la ID del characer. Si es 1, 2 o 3, es que es el player.
     * Sinó, és la maquina
     * @return
     */
    public boolean isPlayer() {
        return (Integer.parseInt(getId()) < 3);
    }


}
