package com.kronostudios.the_game.core.FakeCoreClasses;

import com.kronostudios.the_game.core.Action;
import com.kronostudios.the_game.core.CharacterIG;
import com.kronostudios.the_game.core.UserIG;
import com.kronostudios.the_game.models.Build;
import com.kronostudios.the_game.models.Character;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class FakeUserIG extends UserIG {
    public FakeUserIG(UserIG userIG) {
        super(userIG.getId(), "IA", userIG.getRating(), userIG.getBuild());
    }

    /** DEPRECATED
    public Stack<Action> playARandomTurn(List<CharacterIG> enemyChars){
        Stack<Action> executedActions = new Stack<>();
        CharacterIG executor = null;
        CharacterIG target = null;

        for(int i = 0; i<3; i++){
            if(getBuild().getCharacters().get(i).getCharacterIG().getHealth() > 0){
                executor = getBuild().getCharacters().get(i).getCharacterIG();
            }
            if(enemyChars.get(i).getHealth()>0){
                target = enemyChars.get(i);
            }
        }
        for(int i = 0; i<3; i++){
            Action currentAction = new Action();
            currentAction.setCard(getHand().get(i));
            currentAction.setExecutor(executor);
            currentAction.setTarget(target);
            executedActions.add(currentAction);
        }
        return executedActions;
    }*/

    /** Only called from the IA, so enemyCHars is playerChars
     *
     * @param enemyChars
     * @return
     */
    public Stack<Action> playARandomTurn(List<CharacterIG> enemyChars){
        Stack<Action> actionsStack = new Stack<>();
        CharacterIG executor = null;
        CharacterIG target = null;

        for(int i = 0; i<3; i++){
            int e = (int) (Math.random()*3);
            int t = (int) (Math.random()*3);

            executor = getBuild().getCharacters().get(e).getCharacterIG();
            target = enemyChars.get(t);

            Action currentAction = new Action();
            currentAction.setCard(getHand().get(i));
            currentAction.setExecutor(executor);
            currentAction.setTarget(target);
            actionsStack.add(currentAction);
        }
        return actionsStack;
    }


}
