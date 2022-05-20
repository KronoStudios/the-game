package com.kronostudios.the_game.models;

import android.util.Log;

import com.google.type.DateTime;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MatchResult {

    private String user1_id;
    private String user2_id;
    private String user_winner_id;
    private String playedAt;

    public static ArrayList<MatchResult> matchHistory;

    public MatchResult(String user1_id, String user2_id, String user_winner_id, String datetime) {
        this.user1_id = user1_id;
        this.user2_id = user2_id;
        this.user_winner_id = user_winner_id;
        this.playedAt = playedAt;
    }

    public static void populateMatchHistory(JSONObject jsonObject) {

        try {
            matchHistory = new ArrayList<MatchResult>();
            JSONArray jsonArray = jsonObject.getJSONArray("games");

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject o = jsonArray.getJSONObject(i);

                MatchResult mr = new MatchResult(o.getJSONObject("user_1").getString("username"),
                        o.getJSONObject("user_2").getString("username"),
                        o.getJSONObject("user_winner").getString("username"),
                        o.getString("played_at")
                );
                matchHistory.add(mr);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    public String getUser1_id() {
        return user1_id;
    }

    public void setUser1_id(String user1_id) {
        this.user1_id = user1_id;
    }

    public String getUser2_id() {
        return user2_id;
    }

    public void setUser2_id(String user2_id) {
        this.user2_id = user2_id;
    }

    public String getUser_winner_id() {
        return user_winner_id;
    }

    public void setUser_winner_id(String user_winner_id) {
        this.user_winner_id = user_winner_id;
    }

    public String getPlayedAt() {
        return playedAt;
    }

    public void setPlayedAt(String playedAt) {
        this.playedAt = playedAt;
    }

}
