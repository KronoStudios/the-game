package com.kronostudios.the_game.models;

import android.app.Activity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kronostudios.the_game.R;
import com.kronostudios.the_game.cards.*;
import com.kronostudios.the_game.core.Effect;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * @author: gperez
 *
 * The Fireball class is a basic effect Card.
 * Deals baseDamage amount of health to the target,
 * amplified by the Stat intel of the executor.
 *
 */
public abstract class Card implements Serializable {

    private long id;
    private String name;
    private String description;
    private Stats statsRequired;

    private static List<Card> cards;

    public Card(long id, String name, String description, Stats statsRequired) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.statsRequired = statsRequired;
    }

    public static void populateCardView(LinearLayout originalCardLayout, Card c, Activity a) {

        ((TextView)originalCardLayout.findViewById(R.id.hand_name)).setText(c.getName());
        ((TextView)originalCardLayout.findViewById(R.id.hand_desc)).setText(c.getDescription());
        int image_resource = a.getResources().getIdentifier("com.kronostudios.the_game:drawable/" + c.getImageName(), null, null);
        ((ImageView)originalCardLayout.findViewById(R.id.hand_image)).setImageResource(image_resource);

    }

    public static ArrayList<Card> getCompleteCollection() {
        ArrayList arr = new ArrayList();

        arr.add(new LaserGun());
        arr.add(new PlasmaBomb());
        arr.add(new SonicShield());
        arr.add(new MotherCellSpray());
        arr.add(new Shotgun());
        arr.add(new Torpedo());

        return arr;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Stats getStatsRequired() {
        return statsRequired;
    }

    public int getDamage(int value){
        Double dmg = value * 0.3;
        return dmg.intValue();
    }

    public void setStatsRequired(Stats statsRequired) {
        this.statsRequired = statsRequired;
    }

    public abstract Effect effect(Stats stats);
    public abstract Effect altEffect(Stats stats);

    public static List<Card> getCards() {
        return cards;
    }

    public static void populateCards(JSONObject cardsJSON) {
        try {
            JSONArray jsonArray = cardsJSON.getJSONArray("cards");

            if(cards == null) cards = new ArrayList<Card>() ;
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject o = jsonArray.getJSONObject(i);
                Class<?> classToInstantiate = Class.forName(o.getString("java_class"));
                Constructor<?> constructor = classToInstantiate.getConstructor(long.class, String.class, String.class);
                Object instance = constructor.newInstance(o.getLong("id"), o.getString("name"), o.getString("description"));

                cards.add((Card) instance);

            }
        } catch (JSONException | ClassNotFoundException | InvocationTargetException | NoSuchMethodException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
    }

    public String getImageName() {
        return getName().toLowerCase(Locale.ROOT).replace(" ", "_");
    }

}
