package com.kronostudios.the_game.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.kronostudios.the_game.R;
import com.kronostudios.the_game.core.AppController;
import com.kronostudios.the_game.models.Build;
import com.kronostudios.the_game.models.User;

public class BuildSelection extends AppCompatActivity {

    private User user;

    private PopupWindow searchingGamePopup;
    private boolean searchingGamePopupOpened;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_build_selection);

        searchingGamePopupOpened = false;

        // Title
        TextView title = findViewById(R.id.textTitle);
        int id = getResources().getIdentifier(getIntent().getExtras().getString("dest"), "string", getPackageName());
        title.setText(getString(id));

        // List
        ListView list = findViewById(R.id.listBuilds);
        list.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, AppController.getLoggedUser().builds().toArray()));

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Build b = (Build) adapterView.getAdapter().getItem(i);

                if (!searchingGamePopupOpened) {
                    searchingGamePopupOpened = true;

                    LayoutInflater layoutInflater = (LayoutInflater) BuildSelection.this.getSystemService(getApplicationContext().LAYOUT_INFLATER_SERVICE);
                    View customView = layoutInflater.inflate(R.layout.searching_game_popup, null);

                    //instantiate popup window
                    searchingGamePopup = new PopupWindow(customView, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

                    //display the popup window
                    searchingGamePopup.showAtLocation(findViewById(R.id.buildSelectionLayout), Gravity.CENTER, 0, 0);
                    AppController.startFindingGame();
                }
            }
        });
    }

    public void onCancelFindingPressed(View v){
        AppController.stopFindingGame();
        searchingGamePopup.dismiss();
        searchingGamePopupOpened = false;
    }
    @Override
    public void onBackPressed() {
        AppController.changeActivity(this, GamemodeSelection.class);
    }
}