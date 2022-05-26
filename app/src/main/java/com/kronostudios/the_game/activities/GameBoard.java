package com.kronostudios.the_game.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.kronostudios.the_game.R;
import com.kronostudios.the_game.core.Action;
import com.kronostudios.the_game.core.AppController;
import com.kronostudios.the_game.core.CharacterIG;
import com.kronostudios.the_game.core.FakeCoreClasses.FakeUserIG;
import com.kronostudios.the_game.core.Game;
import com.kronostudios.the_game.core.UserIG;
import com.kronostudios.the_game.models.*;
import com.kronostudios.the_game.models.Character;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Stack;

/**
 * @author: gperez
 *
 * The GameBoard activity contains the elements of the game.
 * It is divided in three rows of three elements each:
 * the enemy Characters, the allied Characters and the three cards from the player's hand.
 */
public class GameBoard extends AppCompatActivity {

    public Build playerBuild;
    public Build enemyBuild;
    UserIG player;
    FakeUserIG iaPlayer;
    public List<CharacterIG> listPlayerChars;
    public List<CharacterIG> listEnemyChars;
    Game g;

    Activity act;

    //Button btnPlayerChar1;
    //Button btnPlayerChar2;
    //Button btnPlayerChar3;
    LinearLayout char1;
    LinearLayout char2;
    LinearLayout char3;

    LinearLayout enemyChar1;
    LinearLayout enemyChar2;
    LinearLayout enemyChar3;

    LinearLayout hand1;
    LinearLayout hand2;
    LinearLayout hand3;


    Card selectedCard;
    CharacterIG target;
    CharacterIG executor;

    public TextView tvplayer1;
    public TextView tvplayer2;
    public TextView tvplayer3;

    public TextView tvenemy1;
    public TextView tvenemy2;
    public TextView tvenemy3;

    public static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_board);

        act = this;
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

        initializeGame(); //crida initalizegame + populateGameBoard()

        context = getApplicationContext();
    }

    public void initializeGame() {

        hand1 = findViewById(R.id.hand1);
        hand2 = findViewById(R.id.hand2);
        hand3 = findViewById(R.id.hand3);

        //btnPlayerChar1 = findViewById(R.id.btnChar1);
        //btnPlayerChar2 = findViewById(R.id.btnChar2);
        //btnPlayerChar3 = findViewById(R.id.btnChar3);

        char1 = findViewById(R.id.char1);
        char2 = findViewById(R.id.char2);
        char3 = findViewById(R.id.char3);
        enemyChar1 = findViewById(R.id.enemyChar1);
        enemyChar2 = findViewById(R.id.enemyChar2);
        enemyChar3 = findViewById(R.id.enemyChar3);

//        tvplayer1 = findViewById(R.id.tvHealthChar1);
//        tvplayer2 = findViewById(R.id.tvHealthChar2);
//        tvplayer3 = findViewById(R.id.tvHealthChar3);

//        tvenemy1 = findViewById(R.id.tvCharEnemy1);
//        tvenemy2 = findViewById(R.id.tvCharEnemy2);
//        tvenemy3 = findViewById(R.id.tvCharEnemy3);

        enemyChar1.setBackgroundColor(ContextCompat.getColor(getBaseContext(), R.color.gray));
        enemyChar1.setEnabled(false);
        enemyChar2.setBackgroundColor(ContextCompat.getColor(getBaseContext(), R.color.gray));
        enemyChar2.setEnabled(false);
        enemyChar3.setBackgroundColor(ContextCompat.getColor(getBaseContext(), R.color.gray));
        enemyChar3.setEnabled(false);

        //btnPlayerChar1.setBackgroundColor(ContextCompat.getColor(getBaseContext(), R.color.gray));
        //btnPlayerChar1.setEnabled(false);
        //btnPlayerChar2.setBackgroundColor(ContextCompat.getColor(getBaseContext(), R.color.gray));
        //btnPlayerChar2.setEnabled(false);
        //btnPlayerChar3.setBackgroundColor(ContextCompat.getColor(getBaseContext(), R.color.gray));
        //btnPlayerChar3.setEnabled(false);
        char1.setBackgroundColor(ContextCompat.getColor(getBaseContext(), R.color.gray));
        char1.setEnabled(false);
        char2.setBackgroundColor(ContextCompat.getColor(getBaseContext(), R.color.gray));
        char2.setEnabled(false);
        char3.setBackgroundColor(ContextCompat.getColor(getBaseContext(), R.color.gray));
        char3.setEnabled(false);

        populateGameBoard();

    }

    /*
        Omplim els elements de la gameboard amb els 3 personatges de cada jugador, i les 3 cartes.
     */
    public void populateGameBoard() {

        //allies
        listPlayerChars = new ArrayList<>();
        listPlayerChars.add(playerBuild.getCharacters().get(0).getCharacterIG());
        listPlayerChars.add(playerBuild.getCharacters().get(1).getCharacterIG());
        listPlayerChars.add(playerBuild.getCharacters().get(2).getCharacterIG());
        //btnPlayerChar1.setText(playerChar1.getName());
        //btnPlayerChar2.setText(playerChar2.getName());
        //btnPlayerChar3.setText(playerChar3.getName());

        //enemies
        listEnemyChars = new ArrayList<>();
        listEnemyChars.add(enemyBuild.getCharacters().get(0).getCharacterIG());
        listEnemyChars.add(enemyBuild.getCharacters().get(1).getCharacterIG());
        listEnemyChars.add(enemyBuild.getCharacters().get(2).getCharacterIG());

        //inflo allied
        LinearLayout auxCharacterLayout = (LinearLayout) getLayoutInflater().inflate(R.layout.character_layout, null);
        Character.populateCharacterView(auxCharacterLayout, playerBuild.getCharacters().get(0), act);
        char1.addView(auxCharacterLayout);

        auxCharacterLayout = (LinearLayout) getLayoutInflater().inflate(R.layout.character_layout, null);
        Character.populateCharacterView(auxCharacterLayout, playerBuild.getCharacters().get(1), act);
        char2.addView(auxCharacterLayout);

        auxCharacterLayout = (LinearLayout) getLayoutInflater().inflate(R.layout.character_layout, null);
        Character.populateCharacterView(auxCharacterLayout, playerBuild.getCharacters().get(2), act);
        char3.addView(auxCharacterLayout);

        //inflo enemies
        auxCharacterLayout = (LinearLayout) getLayoutInflater().inflate(R.layout.character_layout, null);
        int ecr = this.getResources().getIdentifier("com.kronostudios.the_game:drawable/cornered_rectangle_enemy", null, null);
        auxCharacterLayout.setBackgroundResource(ecr);
        Character.populateCharacterView(auxCharacterLayout, enemyBuild.getCharacters().get(0), act);
        enemyChar1.addView(auxCharacterLayout);

        auxCharacterLayout = (LinearLayout) getLayoutInflater().inflate(R.layout.character_layout, null);
        auxCharacterLayout.setBackgroundResource(ecr);
        Character.populateCharacterView(auxCharacterLayout, enemyBuild.getCharacters().get(1), act);
        enemyChar2.addView(auxCharacterLayout);

        auxCharacterLayout = (LinearLayout) getLayoutInflater().inflate(R.layout.character_layout, null);
        auxCharacterLayout.setBackgroundResource(ecr);
        Character.populateCharacterView(auxCharacterLayout, enemyBuild.getCharacters().get(2), act);
        enemyChar3.addView(auxCharacterLayout);


        reviseHealths();

        //Cards
        omplirHand(hand1, player.getHand().get(0), 1);
        omplirHand(hand2, player.getHand().get(1), 2);
        omplirHand(hand3, player.getHand().get(2), 3);
    }





    public void useCard(View v){

        char1.setBackgroundColor(ContextCompat.getColor(getBaseContext(), R.color.purple_500));
        char1.setEnabled(true);
        char2.setBackgroundColor(ContextCompat.getColor(getBaseContext(), R.color.purple_500));
        char2.setEnabled(true);
        char3.setBackgroundColor(ContextCompat.getColor(getBaseContext(), R.color.purple_500));
        char3.setEnabled(true);

        hand1.setBackgroundColor(ContextCompat.getColor(getBaseContext(), R.color.gray));
        hand1.setEnabled(false);
        hand2.setBackgroundColor(ContextCompat.getColor(getBaseContext(), R.color.gray));
        hand2.setEnabled(false);
        hand3.setBackgroundColor(ContextCompat.getColor(getBaseContext(), R.color.gray));
        hand3.setEnabled(false);


        //Miro a quina carta he clickat, per seleccionarla com a selectedCard
        if(v.equals(findViewById(R.id.hand1))){
            selectedCard = player.getHand().get(0);
            //hand1.setBackgroundColor(ContextCompat.getColor(getBaseContext(), R.color.gray));
            ((TextView)findViewById(R.id.hand1name)).setText("");
            //TO-DO falta fer algo per veure uqe la carta està "USADA"
        //}else if(v.equals(findViewById(R.id.hand2))){
        }else if(v.equals(hand2)){
            selectedCard = player.getHand().get(1);
            //hand1.setBackgroundColor(ContextCompat.getColor(getBaseContext(), R.color.gray));
            ((TextView)findViewById(R.id.hand2name)).setText("");
            //TODO vaciarCardView(hand2) --> borrar setText("")
        }else if(v.equals(findViewById(R.id.hand3))){
            selectedCard = player.getHand().get(2);
            //hand1.setBackgroundColor(ContextCompat.getColor(getBaseContext(), R.color.gray));
            ((TextView)findViewById(R.id.hand3name)).setText("");
        }
    }

    /**
     * Primero cambiamos todos los playerChars a fondo gris y enabled.
     * Y también los enemigos.
     *
     * @param v
     */
    public void selectExecutor(View v){

        //això hauria de ser enabled = false, no? sinó pots "cambiar" de executor
        //ho canvio. SI ALGO PETA, POTSER ERA AIXÒ
        char1.setBackgroundColor(ContextCompat.getColor(getBaseContext(), R.color.gray));
        char1.setEnabled(false);
        char2.setBackgroundColor(ContextCompat.getColor(getBaseContext(), R.color.gray));
        char2.setEnabled(false);
        char3.setBackgroundColor(ContextCompat.getColor(getBaseContext(), R.color.gray));
        char3.setEnabled(false);

        enemyChar1.setBackgroundColor(ContextCompat.getColor(getBaseContext(), R.color.purple_500));
        enemyChar1.setEnabled(true);
        enemyChar2.setBackgroundColor(ContextCompat.getColor(getBaseContext(), R.color.purple_500));
        enemyChar2.setEnabled(true);
        enemyChar3.setBackgroundColor(ContextCompat.getColor(getBaseContext(), R.color.purple_500));
        enemyChar3.setEnabled(true);

        if(v.equals(char1)){
            executor = playerBuild.getCharacters().get(0).getCharacterIG();
        }else if(v.equals(char2)){
            executor = playerBuild.getCharacters().get(1).getCharacterIG();
        }else if(v.equals(char3)){
            executor = playerBuild.getCharacters().get(2).getCharacterIG();
        }
    }

    public void selectTarget(View v){

        hand1.setBackgroundColor(ContextCompat.getColor(getBaseContext(), R.color.purple_500));
        hand1.setEnabled(true);
        hand2.setBackgroundColor(ContextCompat.getColor(getBaseContext(), R.color.purple_500));
        hand2.setEnabled(true);
        hand3.setBackgroundColor(ContextCompat.getColor(getBaseContext(), R.color.purple_500));
        hand3.setEnabled(true);


        //torno a desactivar les cartes usades (en aquest cas, que name == "")
        if(((TextView)findViewById(R.id.hand1name)).getText().equals("")){
            hand1.setBackgroundColor(ContextCompat.getColor(getBaseContext(), R.color.gray));
            hand1.setEnabled(false);
        }
        if(((TextView)findViewById(R.id.hand2name)).getText().equals("")){
            hand2.setBackgroundColor(ContextCompat.getColor(getBaseContext(), R.color.gray));
            hand2.setEnabled(false);
        }
        if(((TextView)findViewById(R.id.hand3name)).getText().equals("")){
            hand3.setBackgroundColor(ContextCompat.getColor(getBaseContext(), R.color.gray));
            hand3.setEnabled(false);
        }


        //desactivamos los 3 enemigos
        enemyChar1.setBackgroundColor(ContextCompat.getColor(getBaseContext(), R.color.gray));
        enemyChar1.setEnabled(false);
        enemyChar2.setBackgroundColor(ContextCompat.getColor(getBaseContext(), R.color.gray));
        enemyChar2.setEnabled(false);
        enemyChar3.setBackgroundColor(ContextCompat.getColor(getBaseContext(), R.color.gray));
        enemyChar3.setEnabled(false);


        //marcamos como target al char enemigo seleccionado (v)
        if(v.equals(enemyChar1)){
            target = enemyBuild.getCharacters().get(0).getCharacterIG();
        }else if(v.equals(enemyChar2)){
            target = enemyBuild.getCharacters().get(1).getCharacterIG();
        }else if(v.equals(enemyChar3)){
            target = enemyBuild.getCharacters().get(2).getCharacterIG();
        }
        Action a = new Action(selectedCard, executor, target, this);
        g.getCurrentTurn().getActionList().add(a);
    }

    public void endTurn(View v) {

        g.changeTurn();

        if(g.getStatus().equals(Game.FINISHED)){

            Toast toast = Toast.makeText(context, "You won the game!!", Toast.LENGTH_SHORT);
            toast.show();

            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    //no faig res
                }
            }, 3000);

            AppController.changeActivity(this,MainMenu.class);
            //If the game is finished, no need to play the IA's turn.
            return;
        }
        reviseHealths();
        Stack<Action> iaAction = iaPlayer.playARandomTurn(listPlayerChars);
        g.getCurrentTurn().setActionList(iaAction);
        g.changeTurn();

        if(g.getStatus().equals(Game.FINISHED)){

            Toast toast = Toast.makeText(context, "You lost the game :(", Toast.LENGTH_SHORT);
            toast.show();

            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    //no faig res
                }
            }, 3000);

            AppController.changeActivity(this,MainMenu.class);
        }

        omplirHand(hand1, player.getHand().get(0), 1);
        omplirHand(hand2, player.getHand().get(1), 2);
        omplirHand(hand3, player.getHand().get(2), 3);

        hand1.setBackgroundColor(ContextCompat.getColor(getBaseContext(), R.color.purple_500));
        hand1.setEnabled(true);
        hand2.setBackgroundColor(ContextCompat.getColor(getBaseContext(), R.color.purple_500));
        hand2.setEnabled(true);
        hand3.setBackgroundColor(ContextCompat.getColor(getBaseContext(), R.color.purple_500));
        hand3.setEnabled(true);

        reviseHealths();
    }

    private void omplirHand(LinearLayout handLayout, Card card, int handPos) {

        String image_name = card.getImageName();
        int id_image = getResources().getIdentifier("com.kronostudios.the_game:drawable/" + image_name, null, null);
        int image_id = getResources().getIdentifier("com.kronostudios.the_game:id/hand" + handPos + "image", null, null);
        int name_id = getResources().getIdentifier("com.kronostudios.the_game:id/hand" + handPos + "name", null, null);
        int desc_id = getResources().getIdentifier("com.kronostudios.the_game:id/hand" + handPos + "desc", null, null);

        ImageView iv = handLayout.findViewById(image_id);
        iv.setImageResource(id_image);

        TextView name = handLayout.findViewById(name_id);
        name.setText(card.getName());

        TextView desc = handLayout.findViewById(desc_id);
        desc.setText(card.getDescription());
    }

    //Aquesta funció actualitza la vida del character
    private void reviseHealths() {

        //EXTRA, es podria borrar l'anterior
//        tvenemy1.setText(listEnemyChars.get(0).getHealth() + "/" + listEnemyChars.get(0).getMaxHealth());
//        tvenemy2.setText(listEnemyChars.get(1).getHealth() + "/" + listEnemyChars.get(1).getMaxHealth());
//        tvenemy3.setText(listEnemyChars.get(2).getHealth() + "/" + listEnemyChars.get(2).getMaxHealth());

        ((ProgressBar)enemyChar1.findViewById(R.id.healthBar)).setProgress((listEnemyChars.get(0).getProgress()));
        ((ProgressBar)enemyChar2.findViewById(R.id.healthBar)).setProgress((listEnemyChars.get(1).getProgress()));
        ((ProgressBar)enemyChar3.findViewById(R.id.healthBar)).setProgress((listEnemyChars.get(2).getProgress()));

//        tvplayer1.setText(listPlayerChars.get(0).getHealth() + "/" + listPlayerChars.get(0).getMaxHealth());
//        tvplayer2.setText(listPlayerChars.get(1).getHealth() + "/" + listPlayerChars.get(1).getMaxHealth());
//        tvplayer3.setText(listPlayerChars.get(2).getHealth() + "/" + listPlayerChars.get(2).getMaxHealth());

        ((ProgressBar)char1.findViewById(R.id.healthBar)).setProgress((listPlayerChars.get(0).getProgress()));
        ((ProgressBar)char2.findViewById(R.id.healthBar)).setProgress((listPlayerChars.get(1).getProgress()));
        ((ProgressBar)char3.findViewById(R.id.healthBar)).setProgress((listPlayerChars.get(2).getProgress()));

    }

}