package com.kronostudios.the_game.core;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.kronostudios.the_game.activities.MainMenu;

public class AppController {

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

}
