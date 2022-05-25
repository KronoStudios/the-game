package com.kronostudios.the_game.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kronostudios.the_game.R;
import com.kronostudios.the_game.cards.Fireball;
import com.kronostudios.the_game.loginUtils.PreferencesProvider;
import com.kronostudios.the_game.models.Card;
import com.kronostudios.the_game.models.Deck;
import com.kronostudios.the_game.models.Stats;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Stack;

public class SelectCardActivity extends AppCompatActivity {

    Activity act;
    LinearLayout originalCardLayout;
    Card selectedCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_card);
        act = this;

        //carregar la carta original (foto de dalt)
        Card originalCard = (Card)this.getIntent().getSerializableExtra("originalCard");
        selectedCard = originalCard;
        LinearLayout original_image = findViewById(R.id.original_image);

        originalCardLayout = (LinearLayout) getLayoutInflater().inflate(R.layout.card_layout, null);
        Card.populateCardView(originalCardLayout, originalCard, act);

        original_image.addView(originalCardLayout);


        //carregar la collecció de cartes

        GridLayout grid = (GridLayout)findViewById(R.id.gridLayout);
        for (Card c : Card.getCompleteCollection()){

            LinearLayout cardLayout = (LinearLayout) getLayoutInflater().inflate(R.layout.card_layout, null);
            Card.populateCardView(cardLayout, c, act);

            GridLayout.LayoutParams glp=new GridLayout.LayoutParams();
            glp.setMargins(30,30,30,30);
            cardLayout.setLayoutParams(glp);
            cardLayout.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {

                    Card.populateCardView(originalCardLayout, c, act);
                    selectedCard = c;
                }
            });
            grid.addView(cardLayout);

        }
    }

    public void onSavePressed(View view) throws JSONException, ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {

        String str = PreferencesProvider.providePreferences().getString("deck","");

        JSONObject jObject = new JSONObject(str);
        JSONArray jarr = jObject.getJSONArray("cards");

        ArrayList<Card> arr_cards = new ArrayList<>();
        for(int i = 0; i < jarr.length(); i++){
            String id = jarr.getJSONObject(i).getString("id");
            String name = jarr.getJSONObject(i).getString("name");
            String description =jarr.getJSONObject(i).getString("description");
            String replacedName = name.replace(" ","");
            String className = "com.kronostudios.the_game.cards." + replacedName;
            System.out.println(className);
            //long id, String name, String description, Stats statsRequired)
            Constructor cons = Class.forName(className).getConstructor();
            Card c = (Card) cons.newInstance();
            arr_cards.add(c);
        }

        int deckGridNumber = getIntent().getIntExtra("deckGridNumber",0);

        arr_cards.remove(deckGridNumber);
        arr_cards.add(deckGridNumber, selectedCard);

        Stack<Card> stack_cards = new Stack<>();
        for(Card c1 : arr_cards) {
            stack_cards.push(c1);
        }

        Deck newDeck = new Deck("1", stack_cards);

        PreferencesProvider.providePreferences().edit().putString("deck",new Gson().toJson(newDeck)).commit();

        Intent i = new Intent(this, DeckBuilderActivity.class);

        startActivity(i);

    }


}