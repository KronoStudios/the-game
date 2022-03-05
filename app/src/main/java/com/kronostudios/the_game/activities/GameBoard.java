package com.kronostudios.the_game.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.kronostudios.the_game.R;
import com.kronostudios.the_game.models.Card;

import java.util.List;

public class GameBoard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_board);
    }

    public void populateGameBoard(List<Card> hand, List<Character> alliedChars, List<Character> enemyChars){

        for(int i = 0 ; i < alliedChars.size(); i++){

        }

    }

}