package com.kronostudios.the_game.loginUtils;

import com.google.gson.annotations.SerializedName;

public class Account {

    @SerializedName("token")
    private String token;

    @SerializedName("user_id")
    private String user_id;

    public Account(){
        this.token = "";
        this.user_id = "";
    }


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "Account{" +
                "token='" + token + '\'' +
                ", user_id='" + user_id + '\'' +
                '}';
    }
}
