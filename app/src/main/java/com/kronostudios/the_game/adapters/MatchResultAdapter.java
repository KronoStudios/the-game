package com.kronostudios.the_game.adapters;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kronostudios.the_game.R;
import com.kronostudios.the_game.loginUtils.PreferencesProvider;
import com.kronostudios.the_game.models.MatchResult;

import java.util.List;

public class MatchResultAdapter extends RecyclerView.Adapter<MatchResultAdapter.ViewHolder> {

    private List<MatchResult> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    // data is passed into the constructor
    public MatchResultAdapter(Context context, List<MatchResult> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }


    @Override
    public void onBindViewHolder(@NonNull MatchResultAdapter.ViewHolder holder, int position) {

        // TODO
        // agafar ID de user loged per mirar si sóc el player1 o player2
        String logedUserName = "zstevens";
        String email = PreferencesProvider.providePreferences().getString("email", "");
        Log.d("Agafo email -->: ", email);

        MatchResult matchResult = mData.get(position);

        holder.tvPlayedAt.setText(matchResult.getPlayedAt());
        holder.tvPlayer1.setText(matchResult.getUser1_id());    
        holder.tvPlayer2.setText((matchResult.getUser2_id()));

        //Paint the background
        String color = email.equals(matchResult.getUser_winner_id())?"#a4c639": "#c65b39";
        holder.itemView.setBackgroundColor(Color.parseColor(color));

    }

    @NonNull
    @Override
    public MatchResultAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.matchresult_row, parent, false);
        return new ViewHolder(view);
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvPlayer1;
        TextView tvPlayer2;
        TextView tvPlayedAt;

        ViewHolder(View itemView) {
            super(itemView);
            tvPlayer1 = itemView.findViewById(R.id.tvPlayer1);
            tvPlayer2 = itemView.findViewById(R.id.tvPlayer2);
            tvPlayedAt = itemView.findViewById(R.id.tvPlayedAt);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // convenience method for getting data at click position
    public MatchResult getItem(int id) {
        return mData.get(id);
    }

    // allows clicks events to be caught
    public void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}
