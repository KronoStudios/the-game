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

    Button btnPlayerChar1;
    Button btnPlayerChar2;
    Button btnPlayerChar3;

    //Button btnEnemyChar1;
    Button btnEnemyChar2;
    Button btnEnemyChar3;
    LinearLayout enemyChar1;

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

        btnPlayerChar1 = findViewById(R.id.btnChar1);
        btnPlayerChar2 = findViewById(R.id.btnChar2);
        btnPlayerChar3 = findViewById(R.id.btnChar3);

        //btnEnemyChar1 = findViewById(R.id.btnCharEnemy1);
        enemyChar1 = findViewById(R.id.enemyChar1);
        btnEnemyChar2 = findViewById(R.id.btnCharEnemy2);
        btnEnemyChar3 = findViewById(R.id.btnCharEnemy3);

        tvplayer1 = findViewById(R.id.tvHealthChar1);
        tvplayer2 = findViewById(R.id.tvHealthChar2);
        tvplayer3 = findViewById(R.id.tvHealthChar3);

        tvenemy1 = findViewById(R.id.tvCharEnemy1);
        tvenemy2 = findViewById(R.id.tvCharEnemy2);
        tvenemy3 = findViewById(R.id.tvCharEnemy3);

        //btnEnemyChar1.setBackgroundColor(ContextCompat.getColor(getBaseContext(), R.color.gray));
        //btnEnemyChar1.setEnabled(false);
        enemyChar1.setBackgroundColor(ContextCompat.getColor(getBaseContext(), R.color.gray));
        enemyChar1.setEnabled(false);
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

    /*
        Omplim els elements de la gameboard amb els 3 personatges de cada jugador, i les 3 cartes.
     */
    public void populateGameBoard() {

        //allies
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

        //enemies
        //CharacterIG enemyChar1 = enemyBuild.getCharacters().get(0).getCharacterIG();
        CharacterIG enemyChar2 = enemyBuild.getCharacters().get(1).getCharacterIG();
        CharacterIG enemyChar3 = enemyBuild.getCharacters().get(2).getCharacterIG();

        listEnemyChars = new ArrayList<>();
        //listEnemyChars.add(enemyChar1);
        listEnemyChars.add(enemyBuild.getCharacters().get(0).getCharacterIG());
        listEnemyChars.add(enemyChar2);
        listEnemyChars.add(enemyChar3);
        //btnEnemyChar1.setText(enemyChar1.getName());

        LinearLayout auxCharacterLayout = (LinearLayout) getLayoutInflater().inflate(R.layout.character_layout, null);
        Character.populateCharacterView(auxCharacterLayout, enemyBuild.getCharacters().get(0), act);
        enemyChar1.addView(auxCharacterLayout);

        btnEnemyChar2.setText(enemyChar2.getName());
        btnEnemyChar3.setText(enemyChar3.getName());

        reviseHealths();

        //Cards
        omplirHand(hand1, player.getHand().get(0), 1);
        omplirHand(hand2, player.getHand().get(1), 2);
        omplirHand(hand3, player.getHand().get(2), 3);
    }





    public void useCard(View v){

        btnPlayerChar1.setBackgroundColor(ContextCompat.getColor(getBaseContext(), R.color.purple_500));
        btnPlayerChar1.setEnabled(true);
        btnPlayerChar2.setBackgroundColor(ContextCompat.getColor(getBaseContext(), R.color.purple_500));
        btnPlayerChar2.setEnabled(true);
        btnPlayerChar3.setBackgroundColor(ContextCompat.getColor(getBaseContext(), R.color.purple_500));
        btnPlayerChar3.setEnabled(true);

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

        btnPlayerChar1.setBackgroundColor(ContextCompat.getColor(getBaseContext(), R.color.gray));
        btnPlayerChar1.setEnabled(true);
        btnPlayerChar2.setBackgroundColor(ContextCompat.getColor(getBaseContext(), R.color.gray));
        btnPlayerChar2.setEnabled(true);
        btnPlayerChar3.setBackgroundColor(ContextCompat.getColor(getBaseContext(), R.color.gray));
        btnPlayerChar3.setEnabled(true);

        //btnEnemyChar1.setBackgroundColor(ContextCompat.getColor(getBaseContext(), R.color.purple_500));
        //btnEnemyChar1.setEnabled(true);
        enemyChar1.setBackgroundColor(ContextCompat.getColor(getBaseContext(), R.color.purple_500));
        enemyChar1.setEnabled(true);
        btnEnemyChar2.setBackgroundColor(ContextCompat.getColor(getBaseContext(), R.color.purple_500));
        btnEnemyChar2.setEnabled(true);
        btnEnemyChar3.setBackgroundColor(ContextCompat.getColor(getBaseContext(), R.color.purple_500));
        btnEnemyChar3.setEnabled(true);

        if(v.equals(btnPlayerChar1)){
            executor = playerBuild.getCharacters().get(0).getCharacterIG();
        }else if(v.equals(btnPlayerChar2)){
            executor = playerBuild.getCharacters().get(1).getCharacterIG();
        }else if(v.equals(btnPlayerChar3)){
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
        //btnEnemyChar1.setBackgroundColor(ContextCompat.getColor(getBaseContext(), R.color.gray));
        //btnEnemyChar1.setEnabled(false);
        enemyChar1.setBackgroundColor(ContextCompat.getColor(getBaseContext(), R.color.gray));
        enemyChar1.setEnabled(false);
        btnEnemyChar2.setBackgroundColor(ContextCompat.getColor(getBaseContext(), R.color.gray));
        btnEnemyChar2.setEnabled(false);
        btnEnemyChar3.setBackgroundColor(ContextCompat.getColor(getBaseContext(), R.color.gray));
        btnEnemyChar3.setEnabled(false);

        //marcamos como target al char enemigo seleccionado (v)
        if(v.equals(enemyChar1)){
            target = enemyBuild.getCharacters().get(0).getCharacterIG();
        }else if(v.equals(btnEnemyChar2)){
            target = enemyBuild.getCharacters().get(1).getCharacterIG();
        }else{
            target = enemyBuild.getCharacters().get(2).getCharacterIG();
        }
        Action a = new Action(selectedCard, executor, target, this);
        g.getCurrentTurn().getActionList().add(a);
    }

    public void endTurn(View v) {

        /*For each action, saco la carta de la mano
        if(!this.g.getCurrentTurn().getPlayer().equals(player)){
            for (Action a : g.getCurrentTurn().getActionList()){
                 player.getHand().remove(a.getCard());
            }
        }*/

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

        //btnHand1.setText(player.getHand().get(0).getName());
        //btnHand2.setText(player.getHand().get(1).getName());
        //btnHand3.setText(player.getHand().get(2).getName());
        omplirHand(hand1, player.getHand().get(0), 1);
        omplirHand(hand2, player.getHand().get(1), 2);
        omplirHand(hand3, player.getHand().get(2), 3);

        //btnHand1.setBackgroundColor(ContextCompat.getColor(getBaseContext(), R.color.purple_500));
        //btnHand1.setEnabled(true);
        //btnHand2.setBackgroundColor(ContextCompat.getColor(getBaseContext(), R.color.purple_500));
        //btnHand2.setEnabled(true);
        //btnHand3.setBackgroundColor(ContextCompat.getColor(getBaseContext(), R.color.purple_500));
        //btnHand3.setEnabled(true);
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

        tvenemy1.setText(listEnemyChars.get(0).getHealth() + "/" + listEnemyChars.get(0).getMaxHealth());
        //EXTRA, es podria borrar l'anterior

        ((ProgressBar)enemyChar1.findViewById(R.id.healthBar)).setProgress((listEnemyChars.get(0).getProgress()));
        tvenemy2.setText(listEnemyChars.get(1).getHealth() + "/" + listEnemyChars.get(1).getMaxHealth());
        tvenemy3.setText(listEnemyChars.get(2).getHealth() + "/" + listEnemyChars.get(2).getMaxHealth());

        tvplayer1.setText(listPlayerChars.get(0).getHealth() + "/" + listPlayerChars.get(0).getMaxHealth());
        tvplayer2.setText(listPlayerChars.get(1).getHealth() + "/" + listPlayerChars.get(1).getMaxHealth());
        tvplayer3.setText(listPlayerChars.get(2).getHealth() + "/" + listPlayerChars.get(2).getMaxHealth());

    }

}