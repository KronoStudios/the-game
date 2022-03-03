package com.kronostudios.the_game.core;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.kronostudios.the_game.models.User;

public class AppController {

    // TODO implement real login
    private static User loggedUser = User.getFakeUser();
    private static Game currentGame;

    public static void changeActivityWithParams(Activity src, Class dest, Bundle parameters) {
        Intent intent = new Intent(src, dest);
        if (parameters != null) intent.putExtras(parameters);
        src.startActivity(intent);
        src.finish();
    }

    public static void changeActivity(Activity src, Class dest) {
        Intent intent = new Intent(src, dest);
        src.startActivity(intent);
        src.finish();
    }

    public void startFindingGame() {

    }

    public static User getLoggedUser() {
        return loggedUser;
    }

    public static void setLoggedUser(User loggedUser) {
        AppController.loggedUser = loggedUser;
    }

    public static void setCurrentGame(Game currentGame) {
        AppController.currentGame = currentGame;
    }

    public static Game getCurrentGame() {
        return currentGame;
    }
}
