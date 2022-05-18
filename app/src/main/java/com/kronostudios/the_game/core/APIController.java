package com.kronostudios.the_game.core;

import android.content.Context;
import android.util.Base64;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.kronostudios.the_game.loginUtils.PreferencesProvider;
import com.kronostudios.the_game.loginUtils.Utils;
import com.kronostudios.the_game.models.Card;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class APIController {

    private static final String HOST = "http://192.168.1.52";//10.0.2.2
    private static final String PORT = ":8000";
    private static final String CHARSET = java.nio.charset.StandardCharsets.UTF_8.name();

    public static void Token_Get(Context context, String user, String pass) throws AuthFailureError {
        RequestQueue queue = Volley.newRequestQueue(context);


        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, HOST + PORT + "/session", null, Utils::saveToken, error -> {
            // TODO Handle response error
            Log.e("CARDS_GET", error.getLocalizedMessage());
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                String credentials = user + ":" + pass;
                String auth = "Basic "  + Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);
                headers.put("Authorization", auth);
                return headers;
            }
        };

        queue.add(request);
    }
    
    public static void Cards_Get(Context context) {
        RequestQueue queue = Volley.newRequestQueue(context);

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, HOST + PORT + "/cards", null, Card::populateCards, error -> {
            // TODO Handle response error
            Log.e("CARDS_GET", error.getLocalizedMessage());
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                String auth = PreferencesProvider.providePreferences().getString("authToken","");
                headers.put("Authorization", auth);
                return headers;
            }
        };

        queue.add(request);
    }

    /**
     * Aquesta crida es un INSERT de match.
     * @param context
     */
    public static void Match_Post(Context context) {
        RequestQueue queue = Volley.newRequestQueue(context);

        JSONObject jsonBody = new JSONObject();
        //ara mateix HardCoded per que no podem executar l'aplicació.
        //TO-DO: pasar aquests parametres a la funcio Match_Post
        try {
            jsonBody.put("user1_id", "1");
            jsonBody.put("user2_id", "2");
            jsonBody.put("user_winner_id", "1");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, HOST + PORT + "/matches", jsonBody, Card::populateCards, error -> {
            // TODO Handle response error
            Log.e("MATCH_POST", error.getLocalizedMessage());
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                String auth = PreferencesProvider.providePreferences().getString("authToken","");
                headers.put("Authorization", auth);
                return headers;
            }
        };

        queue.add(request);
    }

    
}
