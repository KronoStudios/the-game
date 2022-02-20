package com.kronostudios.the_game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainMenu extends AppCompatActivity {

    FloatingActionButton termsBtn;
    PopupWindow popUp;
    MainMenu menu;
    Boolean click;
    FloatingActionButton aboutBtn;
    String fromSplash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        Intent intent = getIntent();
        fromSplash = intent.getStringExtra("fromSplash");
        termsBtn = findViewById(R.id.floatingActionButton);
        menu = this;
        click = false;
        termsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(MainMenu.this, TermsAndCond.class);
                startActivity(myIntent);
            }
        });
        aboutBtn = findViewById(R.id.aboutButton);
        aboutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(MainMenu.this, About.class);
                startActivity(myIntent);
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