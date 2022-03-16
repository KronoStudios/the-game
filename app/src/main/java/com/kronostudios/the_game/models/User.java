package com.kronostudios.the_game.models;

import com.kronostudios.the_game.core.DeckIG;
import com.kronostudios.the_game.core.UserIG;

import java.util.Arrays;
import java.util.List;
/**
 * @author: gcaballe
 *
 * The User class is user for login and saving Builds.
 * Contains a UserIG class, which will be the user in game.
 */
public class User {
    private String id;
    private String name;
    private int rating;
    private UserIG userIG;

    public User(String id, String name, int rating) {
        this.id = id;
        this.name = name;
        this.rating = rating;
    }

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

    public UserIG getUserIG(Build build)
    {
        if(userIG == null){
            userIG = new UserIG(this.id, this.name, this.rating, build);
        }
        return  userIG;
    }

    public static User getFakeUser(){
        User u = new User("001", "Pepe", 0);
        return u;
    }

    public List<Build> builds() {
        // TODO Change fake data to DB
        return Arrays.asList(
            Build.getFakeBuild(),
            Build.getFakeBuild(),
            Build.getFakeBuild()
        );
    }

}
