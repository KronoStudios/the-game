package com.kronostudios.the_game.core;

import com.kronostudios.the_game.activities.BuildSelection;

public class Game {
    private UserIG userIG1;
    private UserIG userIG2;
    private Turn currentTurn;

    public Game(UserIG userIG2) {
        this.userIG1 = AppController.getLoggedUser().getUserIG();
        this.userIG1.setBuild(BuildSelection.getSelectedBuild());
        this.userIG2 = userIG2;
    }

    public static void changeTurn(){
        //TODO
    }

    public static void finishGame(){
        //TODO
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
