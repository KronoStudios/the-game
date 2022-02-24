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

public class GamemodeSelection extends AppCompatActivity {

    // Menu buttons
    Button btnAdventure;
    Button btnArena;
    Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gamemode_selection);

        btnArena = (Button) findViewById(R.id.btnArena);
        btnArena.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle params = new Bundle();
                params.putString("dest", "arena");
                AppController.changeActivityWithParams(GamemodeSelection.this, BuildSelection.class, params);
            }
        });

        btnAdventure = (Button) findViewById(R.id.btnAdventure);
        btnAdventure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle params = new Bundle();
                params.putString("dest", "adventure");
                AppController.changeActivityWithParams(GamemodeSelection.this, BuildSelection.class, params);
            }
        });

        btnBack = (Button) findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppController.changeActivity(GamemodeSelection.this, MainMenu.class);
            }
        });
    }

    @Override
    public void onBackPressed() {
        AppController.changeActivity(this, MainMenu.class);
    }
}