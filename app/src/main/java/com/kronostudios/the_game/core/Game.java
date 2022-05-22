package com.kronostudios.the_game.core;

import com.kronostudios.the_game.activities.BuildSelection;
import com.kronostudios.the_game.activities.GameBoard;
import com.kronostudios.the_game.models.Character;
import com.kronostudios.the_game.models.MatchResult;

import java.util.List;

/**
 * @author gcaballe
 *
 * The Game class represents a whole Game. This game is played by two UserIG in a loop of turns.
 *
 */
public class Game {
    private UserIG userIG1;
    private UserIG userIG2;
    public static final String FINISHED = "F";
    public static final String RUNNING = "R";
    private UserIG winner;
    private Turn currentTurn;

    private String status;

    /**
     * Game constructor for PvP mode. Assigns the parameter userIG's to userIG1 and userIG2.
     * Then, starts the game.
     *
     * @param userIG2
     */
    public Game(UserIG userIG1, UserIG userIG2) {
        this.userIG1 = userIG1;
        this.userIG2 = userIG2;

        startGame();
    }

    /**
     * Game constructor for PvE mode. Assigns the @param userIG to userIG2 and the current logged
     * user as userIG1. Then, starts the game.
     *
     * @param userIG2
     */
    public Game(UserIG userIG2) {
        this.userIG1 = AppController.getLoggedUser().getUserIG(BuildSelection.getSelectedBuild());
        this.userIG2 = userIG2;

        startGame();
    }

    /**
     * Initializes the first Turn, and draws cards for each player.
     *
     */
    public void startGame() {
        // TODO implement first player selection in server
        status = RUNNING;
        double firstPlayer = Math.random();

        this.currentTurn = new Turn((firstPlayer % 2 == 0 ? this.getPlayer1() : this.getPlayer2()));

        this.getPlayer1().draw();
        this.getPlayer2().draw();
    }

    /**
     * This function execute the end of turn.
     * Executes all queued up actions of the current player.
     * Checks if the game has already finished with checkFinishGame().
     * Changes turns to the other player, and draws cards.
     */
    public void changeTurn() { //hauriem de refactoritzar el nom a endTurn(), no? @gcaballe
        this.currentTurn.executeActions();
        checkFinishGame();

        Turn nextTurn = new Turn(this.getPlayerWhosNotHisTurn());

        this.currentTurn = nextTurn;

        this.currentTurn.getPlayer().draw();
    }

    public void checkFinishGame() {
        List<Character> charsUsr1 = currentTurn.getPlayer().getBuild().getCharacters();
        List<Character> charsUsr2 = getPlayerWhosNotHisTurn().getBuild().getCharacters();

        //if all 3 chars of Player1 are dead
        if(charsUsr1.get(0).getCharacterIG().getHealth()<=0&& charsUsr1.get(1).getCharacterIG().getHealth()<=0 && charsUsr1.get(2).getCharacterIG().getHealth()<=0){
            //end game
            winner = getPlayerWhosNotHisTurn();
            status = FINISHED;
            System.out.println("----- WINNER PLAYER "+winner.getName()+"-------");


        }
        if(charsUsr2.get(0).getCharacterIG().getHealth()<=0 && charsUsr2.get(1).getCharacterIG().getHealth()<=0 && charsUsr2.get(2).getCharacterIG().getHealth()<=0){
            //end game
            winner = currentTurn.getPlayer();
            status = FINISHED;
            System.out.println("----- WINNER PLAYER "+winner.getName()+"-------");
        }

        if(winner != null) {
            //Creo un objecte GameResult per inserirlo a BD.
            MatchResult gameResult = new MatchResult(
                    userIG1.getId(),
                    userIG2.getId(),
                    winner.getId(), null);
            APIController.Match_Post(GameBoard.context, gameResult);
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
