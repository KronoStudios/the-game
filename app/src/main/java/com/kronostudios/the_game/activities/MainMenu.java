package com.kronostudios.the_game.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.kronostudios.the_game.R;
import com.kronostudios.the_game.cards.Fireball;
import com.kronostudios.the_game.core.APIController;
import com.kronostudios.the_game.core.AppController;
import com.kronostudios.the_game.loginUtils.PreferencesProvider;
import com.kronostudios.the_game.models.Card;

/**
 * @author: gperez
 *
 * The MainMenu lets the player choose between Playing, editing his Profile or
 * moving onto the CollectionMenu.
 */
public class MainMenu extends AppCompatActivity {

    // Legal popup
    PopupWindow legalPopup;
    Boolean legalPopupOpened;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        APIController.Cards_Get(getApplicationContext());
        APIController.MatchResults_Get(getApplicationContext(), 11);

        legalPopupOpened = false;
    }

    /**
     * Starts gamemode selection activity
     * @return void
     * @param v
     */
    public void onPlayButtonPressed(View v) {
        AppController.changeActivity(MainMenu.this, GamemodeSelection.class);
    }

    /**
     * Starts collection activity
     * @return void
     * @param v
     */
    public void onCollectionButtonPressed(View v) {
        // TODO Collection button method
    }

    /**
     * Starts profile activity
     * @return void
     * @param v
     */
    public void onProfileButtonPressed(View v) {
        AppController.changeActivity(MainMenu.this, MatchHistoryActivity.class);
    }

    /**
     * Stops the application
     * @return void
     * @param v
     */
    public void onLogoutButtonPressed(View v) {
        PreferencesProvider.providePreferences().edit().remove("token").commit();
        startActivity(new Intent(this, LoginMainActivity.class));
    }

    /**
     * Opens the legal popup
     * @return void
     * @param v
     */
    public void onOpenLegalButtonPressed(View v) {
        if(!legalPopupOpened){
            legalPopupOpened = true;

            LayoutInflater layoutInflater = (LayoutInflater) MainMenu.this.getSystemService(getApplicationContext().LAYOUT_INFLATER_SERVICE);
            View customView = layoutInflater.inflate(R.layout.legal_popup, null);

            //instantiate popup window
            legalPopup = new PopupWindow(customView, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

            //display the popup window
            legalPopup.showAtLocation(findViewById(R.id.mainMenuLayout), Gravity.CENTER, 0, 0);
        }
    }

    /**
     * Closes the legal popup
     * @return void
     * @param v
     */
    public void onCloseLegalButtonPressed(View v) {
        legalPopupOpened = false;
        legalPopup.dismiss();
    }

    /**
     * Starts legal information activity
     * @return void
     * @param v
     */
    public void onLegalButtonPressed(View v) {
        AppController.changeActivity(MainMenu.this, TermsAndCond.class);
    }

    /**
     * Starts about us activity
     * @return void
     * @param v
     */
    public void onAboutButtonPressed(View v) {
        AppController.changeActivity(MainMenu.this, About.class);
    }

    /**
     * Closes legal popup or the application depending on the last action
     * @return void
     */
    @Override
    public void onBackPressed() {
       if (legalPopupOpened) {
            legalPopup.dismiss();
            legalPopupOpened = false;
       } else {
            finish();
       }
    }

};