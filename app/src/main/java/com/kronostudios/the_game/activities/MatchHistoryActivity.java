package com.kronostudios.the_game.activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.type.DateTime;
import com.kronostudios.the_game.R;
import com.kronostudios.the_game.adapters.MatchResultAdapter;
import com.kronostudios.the_game.core.APIController;
import com.kronostudios.the_game.models.MatchResult;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;

public class MatchHistoryActivity extends AppCompatActivity implements MatchResultAdapter.ItemClickListener {

    MatchResultAdapter adapter;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_history);

        // data to populate the RecyclerView with
        //This function queries the database to get an ArrayList of MatchResults
        ArrayList<MatchResult> matchResultList = MatchResult.matchHistory;

        // set up the RecyclerView
        RecyclerView recyclerView = findViewById(R.id.rvMatchResult);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                new LinearLayoutManager(this).getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MatchResultAdapter(this, matchResultList);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
    }


    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(this, "You clicked " + adapter.getItem(position) + " on row number " + position, Toast.LENGTH_SHORT).show();
    }
}