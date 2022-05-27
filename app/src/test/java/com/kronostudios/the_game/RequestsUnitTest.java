package com.kronostudios.the_game;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import com.kronostudios.the_game.models.Card;

import org.junit.Test;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class RequestsUnitTest {

    @Test
    public void cards() {
        System.out.println(Card.getCards().toString());
    }


}
