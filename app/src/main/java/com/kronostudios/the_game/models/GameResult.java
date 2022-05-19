package com.kronostudios.the_game.models;

import com.google.type.DateTime;

public class GameResult {

    private int user1_id;
    private int user2_id;
    private int user_winner_id;
    private DateTime datetime;

    public GameResult(int user1_id, int user2_id, int user_winner_id, DateTime datetime) {
        this.user1_id = user1_id;
        this.user2_id = user2_id;
        this.user_winner_id = user_winner_id;
        this.datetime = datetime;
    }

    public int getUser1_id() {
        return user1_id;
    }

    public void setUser1_id(int user1_id) {
        this.user1_id = user1_id;
    }

    public int getUser2_id() {
        return user2_id;
    }

    public void setUser2_id(int user2_id) {
        this.user2_id = user2_id;
    }

    public int getUser_winner_id() {
        return user_winner_id;
    }

    public void setUser_winner_id(int user_winner_id) {
        this.user_winner_id = user_winner_id;
    }

    public DateTime getDatetime() {
        return datetime;
    }

    public void setDatetime(DateTime datetime) {
        this.datetime = datetime;
    }
}
