package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

public class GameActivity extends AppCompatActivity implements View.OnClickListener{
    HangmanPlayer aPlayer;
    String displayHidden;
    TextView wordTextView;
    Dictionary dyk;
    HangmanPlayer thePlayer;
    Serialize serialize;
    static GameActivity game;
    Button btnHint;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);
        ViewGroup letterContainer = findViewById(R.id.LinearLayout);
        wordTextView = findViewById(R.id.theWordTextView);
        dyk = new Dictionary(getApplicationContext());
        btnHint = findViewById(R.id.btnHint);
        TextView nameTextView = findViewById(R.id.nameTextView);
        thePlayer = (HangmanPlayer) getIntent().getExtras().get("resumed");
        if(thePlayer == null) {
            thePlayer = new HangmanPlayer((String) getIntent().getExtras().get("pName"), 0, 0); // object for name
        }
        nameTextView.setText(thePlayer.getPlayerName() + "'s gameplay. ");
        game = this;

        for (Character x = 'A'; x <= 'Z'; x++) {
            button = new Button(this);
            button.setText(x.toString());
            button.setOnClickListener(this);
            letterContainer.addView(button);
        }

        dyk.wordList();
        aPlayer = dyk.selectWord();
        displayHidden = aPlayer.getGuessedWord();
        replaceLetters();

        String temp = "";
        for (int i = 0; i < displayHidden.length(); i++)
            temp += displayHidden.charAt(i) + " ";

        wordTextView.setText(temp);

        btnHint.setOnClickListener(click -> {
            getHint();
        });


        serialize = new Serialize();
        serialize.getPlayers().add(thePlayer);
        serialize.deserialize();
    }

    @Override
    protected void onStop(){
        super.onStop();
        serialize.serialize();
    }

    public void replaceLetters() {
        String temp = "";
        System.out.println(displayHidden);
        for(int i = 0; i < displayHidden.length(); i++)
            if(!displayHidden.equals(" "))
                temp += "_";
            else
                temp += " ";
        System.out.println(temp);
        displayHidden = temp;
    } // replace letter

    // method replaces the word which the user has to guess with underscores
    public boolean changeUnderscoreToLetter(String guessedLetter) {
        boolean flag = false;
        for (int x = 0; x < aPlayer.getGuessedWord().length(); x++) {
            if (guessedLetter.toLowerCase().charAt(0) == aPlayer.getGuessedWord().toLowerCase().charAt(x)) {
                System.out.println(displayHidden);
                displayHidden = changeChar(displayHidden, guessedLetter.charAt(0), x);
                flag = true;
            } // if
            String temp = "";
            for(int i = 0; i < displayHidden.length(); i++)
                temp += displayHidden.charAt(i) + " ";
            wordTextView.setText(temp);
        } // for
        return flag;
    } // changeUnderscoreToLetter(String)

    public String changeChar(String word, char letter, int idx) {
        char[] chars = word.toCharArray();
        chars[idx] = letter;
        return String.valueOf(chars);
    }// changeChar(String, char, int)

    // method loops through the word and selects a random letter then displays it to
    // user
    public void getHint() {
        Random random = new Random();
        int minIndex = 0;
        int maxIndex = aPlayer.getGuessedWord().length() - 1;
        int wordIndex = random.nextInt((maxIndex - minIndex) + 1) + minIndex;
        for (int x = 0; x < aPlayer.getGuessedWord().length(); x++) {
            char aLetter = aPlayer.getGuessedWord().toUpperCase().charAt(wordIndex);
            if (aLetter == aPlayer.getGuessedWord().toUpperCase().charAt(x)) {
                displayHidden = changeChar(displayHidden, aLetter, x);
                changeUnderscoreToLetter((aLetter + "").toUpperCase());
            } // if
        } // for
        btnHint.setEnabled(false);
    }// getHint()

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.game_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            this.finish();
            return true;
        }
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
            case R.id.restart_menu:
                restartGame();
                return true;
            case R.id.score_menu:
                scoreBoard();
                return true;
            case R.id.load_menu:
                loadGame();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void aboutGame() {
        Intent intent = new Intent(this, AboutActivity.class);
        startActivity(intent);
    }

    public void learnGame() {
        Intent intent = new Intent(this, LearnActivity.class);
        startActivity(intent);
    }

    public void loadGame() {
        Intent intent = new Intent(this, LoadActivity.class);
        startActivity(intent);
    }

    public void scoreBoard() {
        Intent intent = new Intent(this, ScoreActivity.class);
        intent.putExtra("player", aPlayer);
        startActivity(intent);
    }

    public void winningCondition() {
        if (aPlayer.getGuessedWord().toUpperCase().equals(displayHidden.trim())) {
            aPlayer.setTotalLosses(aPlayer.getTotalLosses() + 1);
            Toast.makeText(GameActivity.this,
                    "Congratulations, you won the game! Select New Game to continue playing.",Toast.LENGTH_SHORT).show();
            aPlayer.incrementWins();
            aPlayer.incrementGamesPlayed();
        } else if (aPlayer.getWrongGuesses() == 6) {
            aPlayer.setTotalLosses(aPlayer.getTotalLosses() + 1);
            Toast.makeText(GameActivity.this,
                    "Unfortunately, You ran out of guesses. Better luck next time. Start a \"New Game\" to continue playing.",
                    Toast.LENGTH_SHORT).show();
            aPlayer.incrementLosses();
            aPlayer.incrementGamesPlayed();
        } // else if
        changeImage((aPlayer.getWrongGuesses() + 1));
    }// winningCondition()

    @Override
    public void onClick(View view) {
        Button btn = (Button) view;
        String btnCaption = btn.getText().toString();
        if (!changeUnderscoreToLetter(btnCaption)) {
            aPlayer.incrementWrongGuesses();
        }
        winningCondition();
    }

    public void restartGame() {
        dyk.wordList();
        aPlayer = dyk.selectWord();
        displayHidden = aPlayer.getGuessedWord();
        replaceLetters();
        String temp = "";
        for (int i = 0; i < displayHidden.length(); i++) {
            temp += displayHidden.charAt(i) + " ";
        }
        wordTextView.setText(temp);
        aPlayer.setWrongGuesses(0);
        changeImage((1));

    }

    public void changeImage(int lives)  {
        try {
            InputStream stream = getAssets().open("hangman" + lives + ".png");
            Drawable drawable = Drawable.createFromStream(stream, null);
            ((ImageView) findViewById(R.id.imageView2)).setImageDrawable(drawable);
        } catch (Exception e) {

        }
    }
    public static GameActivity getGame(){
        return GameActivity.game;
    }

}