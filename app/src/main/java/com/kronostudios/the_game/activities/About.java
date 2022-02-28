package com.kronostudios.the_game.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.kronostudios.the_game.R;
import com.kronostudios.the_game.core.AppController;

public class About extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        TextView tAuth = findViewById(R.id.textAuthors);
        tAuth.setText(tAuth.getText()+"\nMarc Fernandez Parra\nGuillem Caballe Tomas\nGerman Perez Bordera");
        TextView tVersion = findViewById(R.id.textVersion);
        tVersion.setText(tVersion.getText()+"\n1.0");
    }

    @Override
    public void onBackPressed() {
        AppController.changeActivity(this, MainMenu.class);
    }
}