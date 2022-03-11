package com.kronostudios.the_game.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.kronostudios.the_game.R;
import com.kronostudios.the_game.core.AppController;

/**
 * @author: gperez
 *
 * The GamemodeSelection prompts the player to choose between arena (PvP) or adventure (PvE).
 */
public class GamemodeSelection extends AppCompatActivity {

    // Menu buttons
    Button btnAdventure;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gamemode_selection);

        btnAdventure = (Button) findViewById(R.id.btnAdventure);
        btnAdventure.setText(btnAdventure.getText() + " (SOON)");
    }

    /**
     * Goes back to the main menu
     * @return void
     * @param v
     */
    public void onBackButtonPressed(View v) {
        AppController.changeActivity(GamemodeSelection.this, MainMenu.class);
    }

    /**
     * Starts build selection activity with a parameter
     * @return void
     * @param v
     */
    public void onAdventureButtonPressed(View v) {
        // TODO uncomment adventure button code
        /*
        Bundle params = new Bundle();
        params.putString("dest", "adventure");
        AppController.changeActivityWithParams(GamemodeSelection.this, BuildSelection.class, params);
         */
    }

    /**
     * Starts build selection activity with a parameter
     * @return void
     * @param v
     */
    public void onArenaButtonPressed(View v) {
        Bundle params = new Bundle();
        params.putString("dest", "arena");
        AppController.changeActivityWithParams(GamemodeSelection.this, BuildSelection.class, params);
    }

    /**
     * Goes back to the main menu
     * @return void
     */
    @Override
    public void onBackPressed() {
        AppController.changeActivity(this, MainMenu.class);
    }
}