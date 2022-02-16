package com.kronostudios.the_game.model;

import java.util.List;

public class Turn {
    private int number;
    private Player player;
    private int timer;
    private List<Action> actionList;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public int getTimer() {
        return timer;
    }

    public void setTimer(int timer) {
        this.timer = timer;
    }

    public List<Action> getActionList() {
        return actionList;
    }

    public void setActionList(List<Action> actionList) {
        this.actionList = actionList;
    }

    public void executeActions(){
        while(actionList.size()<0){
            actionList.remove(0).doAction();
        }
    }
}
