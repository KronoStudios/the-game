package com.kronostudios.the_game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainMenu extends AppCompatActivity {

    Boolean opened;
    // Legal popup
    PopupWindow popUp;
    Button btnShowLegalPopup;
    Button btnCloseLegalPopup;
    Button termsBtn;
    Button aboutBtn;

    MainMenu menu;
    Boolean click;
    String fromSplash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        opened = false;
        Intent intent = getIntent();
        fromSplash = intent.getStringExtra("fromSplash");
        menu = this;
        click = false;

        btnShowLegalPopup = (Button) findViewById(R.id.btnShowLegal);
        btnShowLegalPopup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //instantiate the popup.xml layout file

                if(!opened){
                    opened = true;

                    LayoutInflater layoutInflater = (LayoutInflater) MainMenu.this.getSystemService(getApplicationContext().LAYOUT_INFLATER_SERVICE);
                    View customView = layoutInflater.inflate(R.layout.popup_window, null);

                    btnCloseLegalPopup = (Button) customView.findViewById(R.id.btnCloseLegal);

                    //instantiate popup window
                    popUp = new PopupWindow(customView, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

                    //display the popup window
                    popUp.showAtLocation(findViewById(R.id.mainMenuLayout), Gravity.CENTER, 0, 0);

                    //close the popup window on button click
                    btnCloseLegalPopup.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            opened = false;
                            popUp.dismiss();
                        }
                    });

                    termsBtn = (Button) customView.findViewById(R.id.btnTerms);
                    termsBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent myIntent = new Intent(MainMenu.this, TermsAndCond.class);
                            startActivity(myIntent);
                        }
                    });

                    aboutBtn = (Button) customView.findViewById(R.id.btnAbout);
                    aboutBtn.setOnClickListener(new View.OnClickListener() {
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
       if(fromSplash.equals("true")){
           return;
       }
    }

};