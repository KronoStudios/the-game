package com.kronostudios.the_game.models;

import android.util.Log;

import com.google.gson.Gson;
import com.kronostudios.the_game.cards.*;
import com.kronostudios.the_game.core.DeckIG;
import com.kronostudios.the_game.loginUtils.PreferencesProvider;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Stack;

/**
 * @author: gcaballe
 *
 * The Deck class i just a stack of Cards.
 */
public class Deck implements Serializable {
    private String id;
    private Stack<Card> cards;



    public String getId() {
        return id;
    }
    public DeckIG deckIG;

    public Deck(String id, Stack<Card> cards) {
        this.id = id;
        this.cards = cards;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Stack<Card> getCards() {
        return cards;
    }

    public void setCards(Stack<Card> cards) {
        this.cards = cards;
    }

    /**
     * @author: gcaballe
     *
     * The FakeDeck is just a deck of nine Cards (all fireballs at the moment)
     * This function will only be used during the development process
     */
    public static Deck getFakeDeck(){
        Stack<Card> cards = new Stack<>();
        cards.push(new LaserGun());
        cards.push(new LaserGun());
        cards.push(new LaserGun());
        cards.push(new LaserGun());
        cards.push(new LaserGun());
        cards.push(new LaserGun());
        cards.push(new LaserGun());
        cards.push(new LaserGun());
        cards.push(new LaserGun());
        //cards.push(new PlasmaBom());
        //cards.push(new SonicShield());
        //cards.push(new MotherCellSpray());
        //cards.push(new PlasmaBomb());

        return new Deck("1", cards);
    }

    public static Deck getIADeck() {
        Stack<Card> cards = new Stack<>();
        cards.push(new LaserGun());
        cards.push(new LaserGun());
        cards.push(new LaserGun());
        cards.push(new LaserGun());
        cards.push(new LaserGun());
        cards.push(new LaserGun());
        cards.push(new LaserGun());
        cards.push(new LaserGun());
        cards.push(new LaserGun());

        return new Deck("2", cards);
    }

    public DeckIG getDeckIG() {
        if(deckIG == null){
            deckIG = new DeckIG(this.id, this.cards);
        }
        return  deckIG;
    }

    /**
     * Si ya hay deck en SharedPreferences, la coje. Sinó coje una de prueba.
     */
    public static Deck obtainDeck() {

        Deck deck = null;

        try {
            String string_deck = PreferencesProvider.providePreferences().getString("deck", "");
            //PreferencesProvider.providePreferences().edit().remove("deck").commit();
            if (string_deck != null && !string_deck.equals("")) {
                //unserialize TO-DO
                JSONObject obj = new JSONObject(string_deck);
                JSONArray jsonArray = obj.getJSONArray("cards");
                Stack<Card> cards = new Stack<>();
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject cardObject = jsonArray.getJSONObject(i);
                    String name = cardObject.getString("name");
                    String nameReplaced = name.replaceAll(" ", "");
                    String className = "com.kronostudios.the_game.cards." + nameReplaced;
                    Constructor cons = Class.forName(className).getConstructor();
                    Card c = (Card) cons.newInstance();
                    cards.add(c);
                }
                deck = new Deck("1", cards);
            } else {
                deck = Deck.getFakeDeck();

                Log.d("JSON DECK", new Gson().toJson(deck));
                Gson gson = new Gson();
                PreferencesProvider.providePreferences().edit().putString("deck", gson.toJson(deck)).commit();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return deck;
    }
}
