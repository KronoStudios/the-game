package com.kronostudios.the_game.core;

import com.kronostudios.the_game.activities.BuildSelection;

public class Game {
    private UserIG userIG1;
    private UserIG userIG2;
    private Turn currentTurn;

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
        double firstPlayer = Math.random();

        this.currentTurn = new Turn((firstPlayer % 2 == 0 ? this.getPlayer1() : this.getPlayer2()));

        this.getPlayer1().draw();
        this.getPlayer2().draw();
    }

    public void changeTurn() {
        this.currentTurn.executeActions();

        Turn nextTurn = new Turn(this.getPlayerWhosNotHisTurn());

        this.currentTurn = nextTurn;

        this.currentTurn.getPlayer().draw();
    }

    public void finishGame() {
        // TODO
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
