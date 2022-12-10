package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class LoadActivity extends AppCompatActivity {

    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load);

        spinner = (Spinner) findViewById(R.id.spinner);
        loadPlayers();
    }

    public void loadPlayers() {
        if (Serialize.getPlayers() == null){
            return;
        }
        String[] playerNames = new String[Serialize.getPlayers().getLength()];
        for (int i = 0; i < Serialize.getPlayers().getLength(); i++){
            playerNames[i] = Serialize.getPlayers().getElementAt(i).getPlayerName();
        }
        ArrayAdapter<String> items = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, playerNames);
        items.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(items);

        final HangmanPlayer[] selected = {null};

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selected[0] = Serialize.getPlayers().getElementAt(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ((Button) findViewById(R.id.loadbtn)).setOnClickListener(view -> {
            Intent intent = new Intent(this, GameActivity.class);
            intent.putExtra("resumed", selected[0]);
            startActivity(intent);
        });
    }

}