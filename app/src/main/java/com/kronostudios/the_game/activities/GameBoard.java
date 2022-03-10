package com.kronostudios.the_game.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.kronostudios.the_game.R;
import com.kronostudios.the_game.core.Action;
import com.kronostudios.the_game.core.AppController;
import com.kronostudios.the_game.core.CharacterIG;
import com.kronostudios.the_game.core.DeckIG;
import com.kronostudios.the_game.core.FakeCoreClasses.FakeUserIG;
import com.kronostudios.the_game.core.Game;
import com.kronostudios.the_game.core.UserIG;
import com.kronostudios.the_game.models.Build;
import com.kronostudios.the_game.models.Card;
import com.kronostudios.the_game.models.Character;
import com.kronostudios.the_game.models.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class GameBoard extends AppCompatActivity {

    Build playerBuild;
    Build enemyBuild;
    UserIG player;
    FakeUserIG iaPlayer;
    List<CharacterIG> listPlayerChars;
    List<CharacterIG> listEnemyChars;
    Game g;

    Button btnPlayerChar1;
    Button btnPlayerChar2;
    Button btnPlayerChar3;
    Button btnEnemyChar1;
    Button btnEnemyChar2;
    Button btnEnemyChar3;

    Button btnHand1;
    Button btnHand2;
    Button btnHand3;

    Card selectedCard;
    CharacterIG target;
    CharacterIG executor;

    public TextView tvplayer1;
    public TextView tvplayer2;
    public TextView tvplayer3;

    public TextView tvenemy1;
    public TextView tvenemy2;
    public TextView tvenemy3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_board);
        playerBuild = AppController.getCurrentGame().getPlayer1().getBuild();
        enemyBuild = AppController.getCurrentGame().getPlayer2().getBuild();
        player = AppController.getLoggedUser().getUserIG(playerBuild);
        g = AppController.getCurrentGame();
        if(g.getPlayer1() == player){
            iaPlayer =  new FakeUserIG(g.getPlayer2());
            iaPlayer.setHand(g.getPlayer2().getHand());
        }
        if(g.getPlayer2() == player){
            iaPlayer = new FakeUserIG(g.getPlayer1());
            iaPlayer.setHand(g.getPlayer1().getHand());
        }
        initializeGame();
    }


    public void populateGameBoard() {


        CharacterIG playerChar1 = playerBuild.getCharacters().get(0).getCharacterIG();
        CharacterIG playerChar2 = playerBuild.getCharacters().get(1).getCharacterIG();
        CharacterIG playerChar3 = playerBuild.getCharacters().get(2).getCharacterIG();

        listPlayerChars = new ArrayList<>();
        listPlayerChars.add(playerChar1);
        listPlayerChars.add(playerChar2);
        listPlayerChars.add(playerChar3);
        btnPlayerChar1.setText(playerChar1.getName());
        btnPlayerChar2.setText(playerChar2.getName());
        btnPlayerChar3.setText(playerChar3.getName());

        CharacterIG enemyChar1 = enemyBuild.getCharacters().get(0).getCharacterIG();
        CharacterIG enemyChar2 = enemyBuild.getCharacters().get(1).getCharacterIG();
        CharacterIG enemyChar3 = enemyBuild.getCharacters().get(2).getCharacterIG();

        listEnemyChars = new ArrayList<>();
        listEnemyChars.add(enemyChar1);
        listEnemyChars.add(enemyChar2);
        listEnemyChars.add(enemyChar3);
        btnEnemyChar1.setText(enemyChar1.getName());
        btnEnemyChar2.setText(enemyChar2.getName());
        btnEnemyChar3.setText(enemyChar3.getName());

        reviseHealths();
        btnHand1.setText(player.getHand().get(0).getName());
        btnHand2.setText(player.getHand().get(1).getName());
        btnHand3.setText(player.getHand().get(2).getName());
    }

    public void initializeGame() {

        btnHand1 = findViewById(R.id.btnHand1);
        btnHand2 = findViewById(R.id.btnHand2);
        btnHand3 = findViewById(R.id.btnHand3);

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

        btnEnemyChar1.setBackgroundColor(ContextCompat.getColor(getBaseContext(), R.color.gray));
        btnEnemyChar1.setEnabled(false);
        btnEnemyChar2.setBackgroundColor(ContextCompat.getColor(getBaseContext(), R.color.gray));
        btnEnemyChar2.setEnabled(false);
        btnEnemyChar3.setBackgroundColor(ContextCompat.getColor(getBaseContext(), R.color.gray));
        btnEnemyChar3.setEnabled(false);

        btnPlayerChar1.setBackgroundColor(ContextCompat.getColor(getBaseContext(), R.color.gray));
        btnPlayerChar1.setEnabled(false);
        btnPlayerChar2.setBackgroundColor(ContextCompat.getColor(getBaseContext(), R.color.gray));
        btnPlayerChar2.setEnabled(false);
        btnPlayerChar3.setBackgroundColor(ContextCompat.getColor(getBaseContext(), R.color.gray));
        btnPlayerChar3.setEnabled(false);

        populateGameBoard();

    }

    public void useCard(View v){

        btnPlayerChar1.setBackgroundColor(ContextCompat.getColor(getBaseContext(), R.color.purple_500));
        btnPlayerChar1.setEnabled(true);
        btnPlayerChar2.setBackgroundColor(ContextCompat.getColor(getBaseContext(), R.color.purple_500));
        btnPlayerChar2.setEnabled(true);
        btnPlayerChar3.setBackgroundColor(ContextCompat.getColor(getBaseContext(), R.color.purple_500));
        btnPlayerChar3.setEnabled(true);


        btnHand1.setBackgroundColor(ContextCompat.getColor(getBaseContext(), R.color.gray));
        btnHand1.setEnabled(false);
        btnHand2.setBackgroundColor(ContextCompat.getColor(getBaseContext(), R.color.gray));
        btnHand2.setEnabled(false);
        btnHand3.setBackgroundColor(ContextCompat.getColor(getBaseContext(), R.color.gray));
        btnHand3.setEnabled(false);

        if(v.equals(btnHand1)){
            selectedCard = player.getHand().get(0);
            btnHand1.setText("");
        }else if(v.equals(btnHand2)){
            selectedCard = player.getHand().get(1);
            btnHand2.setText("");
        }else{
            selectedCard = player.getHand().get(2);
            btnHand3.setText("");
        }
    }



    public void selectExecutor(View v){

        btnPlayerChar1.setBackgroundColor(ContextCompat.getColor(getBaseContext(), R.color.gray));
        btnPlayerChar1.setEnabled(true);
        btnPlayerChar2.setBackgroundColor(ContextCompat.getColor(getBaseContext(), R.color.gray));
        btnPlayerChar2.setEnabled(true);
        btnPlayerChar3.setBackgroundColor(ContextCompat.getColor(getBaseContext(), R.color.gray));
        btnPlayerChar3.setEnabled(true);

        btnEnemyChar1.setBackgroundColor(ContextCompat.getColor(getBaseContext(), R.color.purple_500));
        btnEnemyChar1.setEnabled(true);
        btnEnemyChar2.setBackgroundColor(ContextCompat.getColor(getBaseContext(), R.color.purple_500));
        btnEnemyChar2.setEnabled(true);
        btnEnemyChar3.setBackgroundColor(ContextCompat.getColor(getBaseContext(), R.color.purple_500));
        btnEnemyChar3.setEnabled(true);

        if(v.equals(btnPlayerChar1)){
            executor = playerBuild.getCharacters().get(0).getCharacterIG();
        }else if(v.equals(btnPlayerChar2)){
            executor = playerBuild.getCharacters().get(1).getCharacterIG();
        }else{
            executor = playerBuild.getCharacters().get(2).getCharacterIG();
        }
    }

    public void selectTarget(View v){

        btnHand1.setBackgroundColor(ContextCompat.getColor(getBaseContext(), R.color.purple_500));
        btnHand1.setEnabled(true);
        btnHand2.setBackgroundColor(ContextCompat.getColor(getBaseContext(), R.color.purple_500));
        btnHand2.setEnabled(true);
        btnHand3.setBackgroundColor(ContextCompat.getColor(getBaseContext(), R.color.purple_500));
        btnHand3.setEnabled(true);

        if(btnHand1.getText().equals("")){
            btnHand1.setBackgroundColor(ContextCompat.getColor(getBaseContext(), R.color.gray));
            btnHand1.setEnabled(false);
        }
        if(btnHand2.getText().equals("")){
            btnHand2.setBackgroundColor(ContextCompat.getColor(getBaseContext(), R.color.gray));
            btnHand2.setEnabled(false);
        }
        if(btnHand3.getText().equals("")) {
            btnHand3.setBackgroundColor(ContextCompat.getColor(getBaseContext(), R.color.gray));
            btnHand3.setEnabled(false);
        }

        btnEnemyChar1.setBackgroundColor(ContextCompat.getColor(getBaseContext(), R.color.gray));
        btnEnemyChar1.setEnabled(false);
        btnEnemyChar2.setBackgroundColor(ContextCompat.getColor(getBaseContext(), R.color.gray));
        btnEnemyChar2.setEnabled(false);
        btnEnemyChar3.setBackgroundColor(ContextCompat.getColor(getBaseContext(), R.color.gray));
        btnEnemyChar3.setEnabled(false);

        if(v.equals(btnEnemyChar1)){
            target = enemyBuild.getCharacters().get(0).getCharacterIG();
        }else if(v.equals(btnEnemyChar2)){
            target = enemyBuild.getCharacters().get(1).getCharacterIG();
        }else{
            target = enemyBuild.getCharacters().get(2).getCharacterIG();
        }
        Action a = new Action(selectedCard, executor, target);
        g.getCurrentTurn().getActionList().add(a);
    }

    public void endTurn(View v){
        g.changeTurn();

        int duration = Toast.LENGTH_SHORT;
        if(g.getStatus().equals(Game.FINISHED)){
            AppController.changeActivity(this,MainMenu.class);
        }
        reviseHealths();
        Stack<Action> iaAction = iaPlayer.playARandomTurn(listPlayerChars);
        g.getCurrentTurn().setActionList(iaAction);
        g.changeTurn();

        if(g.getStatus().equals(Game.FINISHED)){
            AppController.changeActivity(this,MainMenu.class);
        }

        btnHand1.setText(player.getHand().get(0).getName());
        btnHand2.setText(player.getHand().get(1).getName());
        btnHand3.setText(player.getHand().get(2).getName());

        btnHand1.setBackgroundColor(ContextCompat.getColor(getBaseContext(), R.color.purple_500));
        btnHand1.setEnabled(true);
        btnHand2.setBackgroundColor(ContextCompat.getColor(getBaseContext(), R.color.purple_500));
        btnHand2.setEnabled(true);
        btnHand3.setBackgroundColor(ContextCompat.getColor(getBaseContext(), R.color.purple_500));
        btnHand3.setEnabled(true);
        reviseHealths();
    }

    private void reviseHealths() {

        tvenemy1.setText(listEnemyChars.get(0).getHealth() + "/" + listEnemyChars.get(0).getMaxHealth());
        tvenemy2.setText(listEnemyChars.get(1).getHealth() + "/" + listEnemyChars.get(1).getMaxHealth());
        tvenemy3.setText(listEnemyChars.get(2).getHealth() + "/" + listEnemyChars.get(2).getMaxHealth());

        tvplayer1.setText(listPlayerChars.get(0).getHealth() + "/" + listPlayerChars.get(0).getMaxHealth());
        tvplayer2.setText(listPlayerChars.get(1).getHealth() + "/" + listPlayerChars.get(1).getMaxHealth());
        tvplayer3.setText(listPlayerChars.get(2).getHealth() + "/" + listPlayerChars.get(2).getMaxHealth());

    }

}