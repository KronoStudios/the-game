package com.kronostudios.the_game.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.kronostudios.the_game.R;
import com.kronostudios.the_game.core.AppController;

public class BuildSelection extends AppCompatActivity {

    TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_build_selection);

        title = findViewById(R.id.textTitle);
        int id = getResources().getIdentifier(getIntent().getExtras().getString("dest"), "string", getPackageName());
        title.setText(getString(id));
    }

    @Override
    public void onBackPressed() {
        AppController.changeActivity(this, GamemodeSelection.class);
    }
}