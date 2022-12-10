package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnContinue = findViewById(R.id.btnContinue);
        EditText editTextUser = findViewById(R.id.editTextUserName);

        btnContinue.setOnClickListener(click -> {
            Intent intent = new Intent(this, GameActivity.class);
            if (TextUtils.isEmpty(editTextUser.getText().toString())){
                Toast.makeText(MainActivity.this, "Please enter a username.",
                        Toast.LENGTH_SHORT).show();
                return;
            } else {
                username = editTextUser.getEditableText().toString();
                intent.putExtra("pName", username);
                startActivity(intent);
            }
        });
        editTextUser.setText("");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.game_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.exit_menu:
                System.exit(0);
                return true;
            case R.id.about_menu:
                aboutGame();
                return true;
            case R.id.learn_menu:
                learnGame();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void aboutGame() {
        Intent intent = new Intent(this, AboutActivity.class);
        startActivity(intent);
    }

    public void learnGame() {
        Intent intent = new Intent(this, LearnActivity.class);
        startActivity(intent);
    }
}
