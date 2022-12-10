package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

public class ScoreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);
        TextView playerName = findViewById(R.id.pnameTextView);
        TextView playerWins = findViewById(R.id.winsTextView);
        TextView playerLosses = findViewById(R.id.lossesTextView);
        TextView TotalGames = findViewById(R.id.gamesTextView);

        HangmanPlayer player = (HangmanPlayer) getIntent().getExtras().get("player");
        System.out.println(player.getPlayerName());
        playerName.append("\n" + player.getPlayerName());
        playerWins.append("\n" + player.getTotalWins());
        playerLosses.append("\n" + player.getTotalLosses());
        TotalGames.append("\n" + player.getGamesPlayed());
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            this.finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}