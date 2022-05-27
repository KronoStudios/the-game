package com.kronostudios.the_game.core;

import android.view.View;

import com.kronostudios.the_game.R;
import com.kronostudios.the_game.activities.GameBoard;

import java.util.List;

import pl.droidsonroids.gif.GifImageView;

/**
 * @author mfernandez
 *
 * The Effect class represents the effect that a Card executes on the game board.
 * This effect could be damaging a Character, shielding or healing a Character, ...
 */
public class Effect {

    public static final int DMGEFFECT = 1;
    public static final int HEALEFFECT = 2;
    public static final int SHIELDEFFECT = 3;
    public static final int AOEDMGEFFECT = 4;
    public static final int AOESHIELDEFFECT = 5;
    public static final int AOEHEALEFFECT = 6;
    private int amount;
    private int effect;

    public Effect(int amount, int effect) {
        this.amount = amount;
        this.effect = effect;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getEffect() {
        return effect;
    }

    public void setEffect(int effect) {
        this.effect = effect;
    }

    public void executeEffect(CharacterIG source, CharacterIG target, GameBoard gameBoard){

        //gameBoard.findViewById(R.id.);
        switch (effect){
            case DMGEFFECT:
                target.recieveDamage(amount);
                target.playExplosionAnimation();
                break;

            case HEALEFFECT:
                target.recieveHeal(amount);
                //target.playHealAnimation();
                break;

            case SHIELDEFFECT:
                target.recieveShield(amount);
                //target.playShieldAnimation();
                break;

            case AOEDMGEFFECT:
                for (CharacterIG ch : (source.isPlayer())?gameBoard.listEnemyChars : gameBoard.listPlayerChars) {
                    ch.recieveDamage(amount);
                    ch.playExplosionAnimation();
                }
                break;

            case AOESHIELDEFFECT:
                for (CharacterIG ch : (source.isPlayer())?gameBoard.listPlayerChars : gameBoard.listEnemyChars) {
                    ch.recieveShield(amount);
                    //ch.playShieldAnimation();
                }
                break;

            case AOEHEALEFFECT:
                for (CharacterIG ch : (source.isPlayer())?gameBoard.listPlayerChars : gameBoard.listEnemyChars) {
                    ch.recieveHeal(amount);
                    //ch.playHealAnimation();
                }
                break;
        }
    }
}
