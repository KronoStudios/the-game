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

public class DeckBuilderActivity extends AppCompatActivity {

    Deck deck;
    Activity act;
    int currentDeckGridNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deck_builder);
        act = this;
        currentDeckGridNumber = 0;

        obtainDeck();


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
                    i.putExtra("deckGridNumber", currentDeckGridNumber);
                    startActivity(i);
                }
            });
            grid.addView(cardLayout);
            currentDeckGridNumber++;
        }




    }

    /**
     * Si ya hay deck en SharedPreferences, la coje. Sinó coje una de prueba.
     */
    private void obtainDeck() {

        //new Gson().toJson(connections)

        String string_deck = PreferencesProvider.providePreferences().getString("deck","");
        //if(string_deck != null || !string_deck.equals("")){
            //unserialize TO-DO

        //}else{
            deck = Deck.getFakeDeck();

            Log.d("JSON DECK", new Gson().toJson(deck));
            Gson gson = new Gson();
            PreferencesProvider.providePreferences().edit().putString("deck",gson.toJson(deck)).commit();
        //}

    }

    public void onSavePressed(View view) {
    }
}