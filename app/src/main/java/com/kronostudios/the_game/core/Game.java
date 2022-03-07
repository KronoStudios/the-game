package com.kronostudios.the_game.core;

import android.util.Log;

import com.kronostudios.the_game.activities.BuildSelection;
import com.kronostudios.the_game.models.Character;

import java.util.List;

public class Game {
    private UserIG userIG1;
    private UserIG userIG2;
    public static final String FINISHED = "F";
    public static final String RUNNING = "R";
    private UserIG winner;
    private Turn currentTurn;

    private String status;

    public Game(UserIG userIG1, UserIG userIG2) {
        this.userIG1 = userIG1;
        this.userIG2 = userIG2;

        startGame();
    }

    public Game(UserIG userIG2) {
        this.userIG1 = AppController.getLoggedUser().getUserIG(BuildSelection.getSelectedBuild());
        this.userIG2 = userIG2;

        startGame();
    }

    public void startGame() {
        // TODO implement first player selection in server
        status = RUNNING;
        double firstPlayer = Math.random();

        this.currentTurn = new Turn((firstPlayer % 2 == 0 ? this.getPlayer1() : this.getPlayer2()));

        this.getPlayer1().draw();
        this.getPlayer2().draw();
    }

    public void changeTurn() {
        this.currentTurn.executeActions();
        checkFinishGame();

        Turn nextTurn = new Turn(this.getPlayerWhosNotHisTurn());

        this.currentTurn = nextTurn;

        this.currentTurn.getPlayer().draw();
    }

    public void checkFinishGame() {
        List<Character> charsUsr1 = currentTurn.getPlayer().getBuild().getCharacters();
        List<Character> charsUsr2 = getPlayerWhosNotHisTurn().getBuild().getCharacters();

        if(charsUsr1.get(0).getCharacterIG().getHealth()<=0 && charsUsr1.get(1).getCharacterIG().getHealth()<=0 && charsUsr1.get(2).getCharacterIG().getHealth()<=0){
            //end game
            winner = getPlayerWhosNotHisTurn();
            status = FINISHED;
            System.out.println("----- WINNER PLAYER WHO IS NOT HIS TURN-------");
            //Log.d("FINISH GAME", "WINNER PLAYER 1");

        }
        if(charsUsr2.get(0).getCharacterIG().getHealth()<=0 && charsUsr2.get(1).getCharacterIG().getHealth()<=0 && charsUsr2.get(2).getCharacterIG().getHealth()<=0){
            //end game
            winner = currentTurn.getPlayer();
            status = FINISHED;
            System.out.println("----- WINNER PLAYER 1-------");
        }


    }


    public UserIG getWinner() {
        return winner;
    }
    public String getStatus() {
        return status;
    }

    public UserIG getPlayerWhosNotHisTurn() {
        return this.getCurrentTurn().getPlayer() == this.getPlayer1() ? this.getPlayer2() : this.getPlayer1();
    }

    public UserIG getPlayer1() {
        return userIG1;
    }

    public void setPlayer1(UserIG userIG1) {
        this.userIG1 = userIG1;
    }

    public UserIG getPlayer2() {
        return userIG2;
    }

    public void setPlayer2(UserIG userIG2) {
        this.userIG2 = userIG2;
    }

    public Turn getCurrentTurn() {
        return currentTurn;
    }

    public void setCurrentTurn(Turn currentTurn) {
        this.currentTurn = currentTurn;
    }

}
