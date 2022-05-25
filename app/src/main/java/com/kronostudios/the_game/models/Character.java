package com.kronostudios.the_game.models;

import android.app.Activity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.kronostudios.the_game.R;
import com.kronostudios.the_game.core.CharacterIG;

import java.util.Locale;

/**
 * @author: gcaballe
 *
 * The Character class describes one of the three Characters in a Build.
 * This Character has some defined stats.
 */
public class Character {
    private String id;
    private String name;
    private Stats stats;
    private CharacterIG characterIG;

    public Character(String id, String name, Stats stats) {
        this.id = id;
        this.name = name;
        this.stats = stats;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Stats getStats() {
        return stats;
    }

    public void setStats(Stats stats) {
        this.stats = stats;
    }

    public CharacterIG getCharacterIG()
    {
        if(characterIG == null){
            characterIG = new CharacterIG(this.id, this.name, this.stats);
        }
        return  characterIG;
    }

    public static Character getFakeCharacter(){
        Character c = new Character("001", "Pepe", new Stats(5,5,50));
        return c;
    }

    public static void populateCharacterView(LinearLayout characterLayout, Character c, Activity a) {

        ((TextView)characterLayout.findViewById(R.id.character_name)).setText(c.getName());

        int image_resource = a.getResources().getIdentifier("com.kronostudios.the_game:drawable/" + c.getImageName(), null, null);
        ((ImageView)characterLayout.findViewById(R.id.character_image)).setImageResource(image_resource);

        ((TextView)characterLayout.findViewById(R.id.character_strength)).setText("Str: " + c.getStats().getStr());
        ((TextView)characterLayout.findViewById(R.id.character_intellect)).setText("Int: " + c.getStats().getIntel());

        int d = c.getCharacterIG().getProgress();
        ((ProgressBar)characterLayout.findViewById(R.id.healthBar)).setProgress(d);

        //shield??

    }

    public String getImageName() {
        return getName().toLowerCase(Locale.ROOT).replace(" ", "_");
    }

}
