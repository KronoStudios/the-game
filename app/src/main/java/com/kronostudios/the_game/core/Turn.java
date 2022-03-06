package com.kronostudios.the_game.core;

import java.util.List;
import java.util.Stack;

public class Turn {
    private static int counter = 0;

    private int number;
    private UserIG userIG;
    private int timer;
    private Stack<Action> actionList;

    public Turn(UserIG userIG) {
        this.userIG = userIG;
        this.number = ++counter;
        this.timer = 0;
        this.actionList = new Stack<>();
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public UserIG getPlayer() {
        return userIG;
    }

    public void setPlayer(UserIG userIG) {
        this.userIG = userIG;
    }

    public int getTimer() {
        return timer;
    }

    public void setTimer(int timer) {
        this.timer = timer;
    }

    public Stack<Action> getActionList() {
        return actionList;
    }

    public void setActionList(Stack<Action> actionList) {
        this.actionList = actionList;
    }

    public void executeActions() {
        while(actionList.size() > 0) {
            Action a = actionList.pop();
            userIG.useCard(a.getCard());
            a.doAction();
        }
    }
}
