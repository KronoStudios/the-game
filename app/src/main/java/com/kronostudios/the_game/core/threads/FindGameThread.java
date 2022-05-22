package com.kronostudios.the_game.core.threads;

import android.app.Activity;
import android.os.Handler;

import com.kronostudios.the_game.activities.BuildSelection;
import com.kronostudios.the_game.activities.GameBoard;
import com.kronostudios.the_game.activities.MainMenu;
import com.kronostudios.the_game.activities.SplashScreen;
import com.kronostudios.the_game.core.AppController;
import com.kronostudios.the_game.core.FakeCoreClasses.FakeUserIG;
import com.kronostudios.the_game.core.UserIG;
import com.kronostudios.the_game.models.Build;
import com.kronostudios.the_game.models.User;

public class FindGameThread extends Thread {

    private Boolean running;

    public FindGameThread() {
        running = false;
    }

    public Boolean getRunning() {
        return running;
    }

    public void setRunning(Boolean running) {
        this.running = running;
    }

    public void run(BuildSelection act) {
        running = true;

        // TODO implement real enqueuing
        /*
        while !foundGame (and !interrupted)
            request API (with myBuild) every 200ms (is there a rival?)

        - Dismiss popup

        json = {
            idGame: X,
            players: [
                {id: X, build: X},
                {id: X, build: X}
            ]
        }

        UserIG rival = new UserIG(id, build)

        AppController.startGame(rival)
         */

        // Fake game finding
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                act.searchingGamePopup.dismiss();
                FakeUserIG rival = new FakeUserIG(User.getFakeUser().getUserIG(Build.getFakeBuild2()));
                AppController.startGame(rival, act);
                running = false;
            }
        }, 2000);
    }
}
