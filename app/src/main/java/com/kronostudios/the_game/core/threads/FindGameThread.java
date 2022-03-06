package com.kronostudios.the_game.core.threads;

import android.app.Activity;
import android.os.Handler;

import com.kronostudios.the_game.activities.BuildSelection;
import com.kronostudios.the_game.activities.GameBoard;
import com.kronostudios.the_game.activities.MainMenu;
import com.kronostudios.the_game.activities.SplashScreen;
import com.kronostudios.the_game.core.AppController;

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
        running= true;

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                act.searchingGamePopup.dismiss();
                AppController.changeActivity(act, GameBoard.class);
                running = false;
            }
        }, 2000);
    }
}
