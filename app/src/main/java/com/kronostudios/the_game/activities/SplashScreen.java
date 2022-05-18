package com.kronostudios.the_game.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.kronostudios.the_game.R;
import com.kronostudios.the_game.core.AppController;

/**
 * @author: gperez
 *
 * The SplashScreen is an auxiliar menu to redirect players to the AboutUs or TermsAndConditions activities.
 */
public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                AppController.changeActivity(SplashScreen.this, MainMenu.class);
            }
        }, 2000);
    }
}