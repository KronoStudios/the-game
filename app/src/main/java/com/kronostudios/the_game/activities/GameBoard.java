package com.kronostudios.the_game.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.kronostudios.the_game.R;
import com.kronostudios.the_game.core.AppController;
import com.kronostudios.the_game.core.CharacterIG;
import com.kronostudios.the_game.core.DeckIG;
import com.kronostudios.the_game.core.Game;
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

    public void populateGameBoard() {

        CharacterIG playerChar1 = playerBuild.getCharacters().get(0).getCharacterIG();
        CharacterIG playerChar2 = playerBuild.getCharacters().get(1).getCharacterIG();
        CharacterIG playerChar3 = playerBuild.getCharacters().get(2).getCharacterIG();

        tvplayer1.setText(playerChar1.getHealth() + "/" + playerChar1.getMaxHealth());
        btnPlayerChar1.setText(playerChar1.getName());
        tvplayer2.setText(playerChar2.getHealth() + "/" + playerChar2.getMaxHealth());
        btnPlayerChar2.setText(playerChar2.getName());
        tvplayer3.setText(playerChar3.getHealth() + "/" + playerChar3.getMaxHealth());
        btnPlayerChar3.setText(playerChar3.getName());

        CharacterIG enemyChar1 = enemyBuild.getCharacters().get(0).getCharacterIG();
        CharacterIG enemyChar2 = enemyBuild.getCharacters().get(1).getCharacterIG();
        CharacterIG enemyChar3 = enemyBuild.getCharacters().get(2).getCharacterIG();

        tvenemy1.setText(enemyChar1.getHealth() + "/" + enemyChar1.getMaxHealth());
        btnEnemyChar1.setText(enemyChar1.getName());
        tvenemy2.setText(enemyChar2.getHealth() + "/" + enemyChar2.getMaxHealth());
        btnEnemyChar2.setText(enemyChar2.getName());
        tvenemy3.setText(enemyChar3.getHealth() + "/" + enemyChar3.getMaxHealth());
        btnEnemyChar3.setText(enemyChar3.getName());
        
    }

    public void initializeGame() {

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

        playerBuild = AppController.getCurrentGame().getPlayer1().getBuild();
        enemyBuild = AppController.getCurrentGame().getPlayer2().getBuild();

        populateGameBoard();

    }


}