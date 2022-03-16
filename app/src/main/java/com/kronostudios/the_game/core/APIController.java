package com.kronostudios.the_game.core;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.kronostudios.the_game.models.Card;

import org.json.JSONObject;

public class APIController {

    private static final String HOST = "http://192.168.1.52";
    private static final String PORT = ":8000";
    private static final String CHARSET = java.nio.charset.StandardCharsets.UTF_8.name();

    public static void Cards_Get(Context context) {
        RequestQueue queue = Volley.newRequestQueue(context);

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, HOST + PORT + "/cards", null, Card::populateCards, error -> {
            // TODO Handle response error
            Log.e("CARDS_GET", error.getLocalizedMessage());
        });

        queue.add(request);
    }

}
