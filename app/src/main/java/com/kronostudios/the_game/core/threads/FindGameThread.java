package com.kronostudios.the_game.core.threads;

import android.os.Handler;

import com.kronostudios.the_game.activities.MainMenu;
import com.kronostudios.the_game.activities.SplashScreen;
import com.kronostudios.the_game.core.AppController;

public class FindGameThread extends Thread{

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

    public void run() {
        running= true;
        try {
            Thread.sleep(3000);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        running = false;
    }
}
