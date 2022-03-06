package com.kronostudios.the_game.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.kronostudios.the_game.R;
import com.kronostudios.the_game.core.DeckIG;
import com.kronostudios.the_game.models.Build;
import com.kronostudios.the_game.models.Card;
import com.kronostudios.the_game.models.Character;

import java.util.List;

public class GameBoard extends AppCompatActivity {

    Build playerBuild;
    Build enemyBuild;

    Button btnPlayerChar1;
    Button btnPlayerChar2;
    Button btnPlayerChar3;

    Button btnEnemyChar1;
    Button btnEnemyChar2;
    Button btnEnemyChar3;


    Button btnHand1;
    Button btnHand2;
    Button btnHand3;


    TextView tvplayer1;
    TextView tvplayer2;
    TextView tvplayer3;

    TextView tvenemy1;
    TextView tvenemy2;
    TextView tvenemy3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_board);
        initializeGame();
    }

    public void populateGameBoard(){

        Character playerChar1 = playerBuild.getCharacters().get(0);
        Character playerChar2 = playerBuild.getCharacters().get(1);
        Character playerChar3 = playerBuild.getCharacters().get(2);

        tvplayer1.setText(playerChar1.getStats().getSta() + "/" + playerChar1.getStats().getSta());
        tvplayer2.setText(playerChar2.getStats().getSta() + "/" + playerChar2.getStats().getSta());
        tvplayer3.setText(playerChar3.getStats().getSta() + "/" + playerChar3.getStats().getSta());

        Character enemyChar1 = enemyBuild.getCharacters().get(0);
        Character enemyChar2 = enemyBuild.getCharacters().get(1);
        Character enemyChar3 = enemyBuild.getCharacters().get(2);

        tvenemy1.setText(enemyChar1.getStats().getSta() + "/" + enemyChar1.getStats().getSta());
        tvenemy2.setText(enemyChar2.getStats().getSta() + "/" + enemyChar2.getStats().getSta());
        tvenemy3.setText(enemyChar3.getStats().getSta() + "/" + enemyChar3.getStats().getSta());
        
    }

    public void initializeGame(){

        btnHand1 = findViewById(R.id.btnHand1);
        btnHand2= findViewById(R.id.btnHand2);
        btnHand3= findViewById(R.id.btnHand3);

        btnPlayerChar1 = findViewById(R.id.btnChar1);
        btnPlayerChar2 = findViewById(R.id.btnChar2);
        btnPlayerChar3 = findViewById(R.id.btnChar3);

        btnEnemyChar1 = findViewById(R.id.btnCharEnemy1);
        btnEnemyChar2 = findViewById(R.id.btnCharEnemy2);
        btnEnemyChar3 = findViewById(R.id.btnCharEnemy3);


        tvplayer1 = findViewById(R.id.tvHealthChar1);
        tvplayer2 = findViewById(R.id.tvHealthChar2);
        tvplayer3 = findViewById(R.id.tvHealthChar3);

        tvenemy1 = findViewById(R.id.tvCharEnemy1);
        tvenemy2 = findViewById(R.id.tvCharEnemy2);
        tvenemy3 = findViewById(R.id.tvCharEnemy3);

        playerBuild = BuildSelection.getSelectedBuild();
        enemyBuild = Build.getFakeBuild();

        populateGameBoard();

    }


}