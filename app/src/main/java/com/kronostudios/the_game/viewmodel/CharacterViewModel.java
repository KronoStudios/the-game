package com.kronostudios.the_game.viewmodel;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.kronostudios.the_game.activities.fragments.CharacterFragment;
import com.kronostudios.the_game.core.CharacterIG;

public class CharacterViewModel extends ViewModel {

    public CharacterIG model;
    public CharacterFragment view;

    public CharacterViewModel(CharacterFragment view) {
        this.view = view;
    }
    public void setExecutor(CharacterIG src){
        Log.d("ViewModel", "onClickedAt: ");
    }

    public void setTarget(CharacterIG src){
        Log.d("ViewModel", "onClickedAt: ");
    }

    public void nextTurn(){

    }
}
