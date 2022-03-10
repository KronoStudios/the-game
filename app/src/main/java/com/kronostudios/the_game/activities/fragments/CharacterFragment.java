package com.kronostudios.the_game.activities.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.kronostudios.the_game.R;

public class CharacterFragment extends Fragment {
    public CharacterFragment() {
        super(R.layout.fragment_character);
    }
    /*
            CharacterFragmentBinding  binding = DataBindingUtil.inflate(
                    inflater, R.layout.fragment_character, container, false);
            binding.getRoot();
            return binding;*/
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        return null;
    }
}
