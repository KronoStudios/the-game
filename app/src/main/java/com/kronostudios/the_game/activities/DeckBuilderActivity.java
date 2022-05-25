package com.kronostudios.the_game.activities;

import static android.widget.GridLayout.*;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kronostudios.the_game.R;
import com.kronostudios.the_game.loginUtils.PreferencesProvider;
import com.kronostudios.the_game.models.Card;
import com.kronostudios.the_game.models.Deck;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Constructor;
import java.util.Stack;

public class DeckBuilderActivity extends AppCompatActivity {

    Deck deck;
    Activity act;
    int currentDeckGridNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deck_builder);
        act = this;

        try {
            obtainDeck();
        } catch (Exception e) {
            e.printStackTrace();
        }


        GridLayout grid = (GridLayout)findViewById(R.id.gridLayout);
        for (Object o : deck.getCards().toArray()){
            Card c = (Card) o;

            LinearLayout cardLayout = (LinearLayout) getLayoutInflater().inflate(R.layout.card_layout, null);
            ((TextView)cardLayout.findViewById(R.id.hand_name)).setText(c.getName());
            ((TextView)cardLayout.findViewById(R.id.hand_desc)).setText(c.getDescription());
            int image_resource = getResources().getIdentifier("com.kronostudios.the_game:drawable/" + c.getImageName(), null, null);
            ((ImageView)cardLayout.findViewById(R.id.hand_image)).setImageResource(image_resource);

            GridLayout.LayoutParams glp=new GridLayout.LayoutParams();
            glp.setMargins(30,30,30,30);
            cardLayout.setLayoutParams(glp);
            cardLayout.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    Intent i = new Intent(act, SelectCardActivity.class);
                    i.putExtra("originalCard", c);
                    GridLayout parent = (GridLayout) view.getParent();
                    int x = parent.indexOfChild(view);
                    i.putExtra("deckGridNumber", x);
                    startActivity(i);
                }
            });
            grid.addView(cardLayout);
        }

    }

    /**
     * Si ya hay deck en SharedPreferences, la coje. Sinó coje una de prueba.
     */
    private void obtainDeck() throws Exception {


        String string_deck = PreferencesProvider.providePreferences().getString("deck","");
        if(string_deck != null && !string_deck.equals("")){
            //unserialize TO-DO
            JSONObject obj = new JSONObject(string_deck);
            JSONArray jsonArray = obj.getJSONArray("cards");
            Stack<Card> cards = new Stack<>();
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject cardObject = jsonArray.getJSONObject(i);
                String name = cardObject.getString("name");
                String nameReplaced = name.replaceAll(" ","");
                String className = "com.kronostudios.the_game.cards." +nameReplaced;
                Constructor cons = Class.forName(className).getConstructor();
                Card c = (Card) cons.newInstance();
                cards.add(c);
            }
            deck = new Deck("1", cards);
        }else{
            deck = Deck.getFakeDeck();

            Log.d("JSON DECK", new Gson().toJson(deck));
            Gson gson = new Gson();
            PreferencesProvider.providePreferences().edit().putString("deck",gson.toJson(deck)).commit();
        }

    }

    public void onSavePressed(View view) {
        Intent i = new Intent(this, MainMenu.class);
        startActivity(i);
        finish();
    }
}