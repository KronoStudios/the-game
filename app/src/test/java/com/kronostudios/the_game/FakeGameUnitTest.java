package com.kronostudios.the_game;

import com.kronostudios.the_game.cards.Fireball;
import com.kronostudios.the_game.core.Action;
import com.kronostudios.the_game.core.CharacterIG;
import com.kronostudios.the_game.core.Game;
import com.kronostudios.the_game.core.UserIG;
import com.kronostudios.the_game.models.Build;
import com.kronostudios.the_game.models.User;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class FakeGameUnitTest {

    private UserIG currentUser;

    private Game initializeGame() {
        UserIG u1 = User.getFakeUser().getUserIG(Build.getFakeBuild());
        UserIG u2 = User.getFakeUser().getUserIG(Build.getFakeBuild());

        return new Game(u1, u2);
    }

    @Test
    public void player1_is_valid() {
        Game g = initializeGame();
        assertEquals(UserIG.class, g.getPlayer1().getClass());
        assertNotNull(g.getPlayer1().getBuild());
        assertEquals(3, g.getPlayer1().getBuild().getCharacters().size());
        assertEquals(6, g.getPlayer1().getBuild().getDeck().getCards().size());
        assertEquals(3, g.getPlayer1().getHand().size());
    }

    @Test
    public void player2_is_valid() {
        Game g = initializeGame();
        assertEquals(UserIG.class, g.getPlayer2().getClass());
        assertNotNull(g.getPlayer2().getBuild());
        assertEquals(3, g.getPlayer2().getBuild().getCharacters().size());
        assertEquals(6, g.getPlayer2().getBuild().getDeck().getCards().size());
        assertEquals(3, g.getPlayer2().getHand().size());
    }

    @Test
    public void start_game_works() {
        Game g = initializeGame();
        assertNotNull(g.getCurrentTurn());
        assertNotNull(g.getCurrentTurn().getPlayer());
        currentUser = g.getCurrentTurn().getPlayer();
    }

    @Test
    public void turn_works() {
        Game g = initializeGame();

        CharacterIG source = g.getCurrentTurn().getPlayer().getBuild().getCharacters().get(0).getCharacterIG();
        CharacterIG target = g.getPlayerWhosNotHisTurn().getBuild().getCharacters().get(0).getCharacterIG();
        Fireball card1 = (Fireball) g.getCurrentTurn().getPlayer().getHand().get(0);
        Fireball card2 = (Fireball) g.getCurrentTurn().getPlayer().getHand().get(1);

        // Fake action 1
        Action a1 = new Action();
        a1.setExecutor(source);
        a1.setCard(card1);
        a1.setTarget(target);

        // Fake action 2
        Action a2 = new Action();
        a2.setExecutor(source);
        a2.setCard(card2);
        a2.setTarget(target);

        g.getCurrentTurn().getActionList().add(a1);
        g.getCurrentTurn().getActionList().add(a2);

        assertEquals(2, g.getCurrentTurn().getActionList().size());

        g.changeTurn();

        int expectedHp = target.getMaxHealth() - card1.effect(source.getStats()).getAmount() * 2;

        assertEquals(0, g.getCurrentTurn().getActionList().size());
        assertEquals(expectedHp, target.getHealth());
    }

}
