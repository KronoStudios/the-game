package com.kronostudios.the_game.models;

import java.util.List;

public class User {
    private String id;
    private String name;
    private int rating;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public static User getFakeUser(){
        User u = new User();
        u.id = "001";
        u.name = "Pepe";
        u.rating = 0;
        return u;
    }

    public List<Build> builds(){
        //TODO
        return null;
    }

}
