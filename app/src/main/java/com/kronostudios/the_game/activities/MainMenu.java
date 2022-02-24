package com.kronostudios.the_game.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.kronostudios.the_game.R;

public class MainMenu extends AppCompatActivity {

    // Menu buttons
    Button btnPlay;
    Button btnCollection;
    Button btnProfile;
    Button btnExit;

    // Legal popup
    PopupWindow legalPopup;
    Boolean legalPopupOpened;
    Button btnShowLegalPopup;
    Button btnCloseLegalPopup;
    Button btnLegal;
    Button btnAbout;

    MainMenu menu;
    Boolean click;
    String fromSplash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        legalPopupOpened = false;
        Intent intent = getIntent();
        fromSplash = intent.getStringExtra("fromSplash");
        menu = this;
        click = false;

        btnPlay = (Button) findViewById(R.id.btnPlay);
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO Play button method
            }
        });

        btnCollection = (Button) findViewById(R.id.btnCollection);
        btnCollection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO Collection button method
            }
        });

        btnProfile = (Button) findViewById(R.id.btnProfile);
        btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO Profile button method
            }
        });

        btnExit = (Button) findViewById(R.id.btnExit);
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO No se cierra la app (vuelve a la splash screen)
                finish();
                System.exit(0);
            }
        });

        btnShowLegalPopup = (Button) findViewById(R.id.btnShowLegal);
        btnShowLegalPopup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //instantiate the popup.xml layout file

                if(!legalPopupOpened){
                    legalPopupOpened = true;

                    LayoutInflater layoutInflater = (LayoutInflater) MainMenu.this.getSystemService(getApplicationContext().LAYOUT_INFLATER_SERVICE);
                    View customView = layoutInflater.inflate(R.layout.popup_window, null);

                    btnCloseLegalPopup = (Button) customView.findViewById(R.id.btnCloseLegal);

                    //instantiate popup window
                    legalPopup = new PopupWindow(customView, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

                    //display the popup window
                    legalPopup.showAtLocation(findViewById(R.id.mainMenuLayout), Gravity.CENTER, 0, 0);

                    //close the popup window on button click
                    btnCloseLegalPopup.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            legalPopupOpened = false;
                            legalPopup.dismiss();
                        }
                    });

                    btnLegal = (Button) customView.findViewById(R.id.btnTerms);
                    btnLegal.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent myIntent = new Intent(MainMenu.this, TermsAndCond.class);
                            startActivity(myIntent);
                        }
                    });

                    btnAbout = (Button) customView.findViewById(R.id.btnAbout);
                    btnAbout.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent myIntent = new Intent(MainMenu.this, About.class);
                            startActivity(myIntent);
                        }
                    });
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
       if (fromSplash.equals("true")){
           return;
       }
    }

};