package com.example.myapplication;

import android.content.Context;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

import com.example.myapplication.linked_data_structures.*;



public class Dictionary {
    HangmanPlayer aPlayer = new HangmanPlayer();

    SinglyLinkedList<String> theWord = new SinglyLinkedList<String>();
    String fileWord, aWord;
    int minIndex, maxIndex, wordIndex;
    InputStream input;
    private Context context;

    public Dictionary(Context context) {
        this.context = context;
    }

    public boolean wordList() {
        boolean flag = false;
        try {
            input = context.getAssets().open("dictionary.txt");
            BufferedReader buffer = new BufferedReader(new InputStreamReader(input));
            String line = null;
            while((line = buffer.readLine()) != null)
                theWord.add(line);

        } catch (Exception e1) {
            flag = true;
        } // catch
        return flag;
    } // wordList()


    // Method selects a random word's index and displays the word in the frame.
    public HangmanPlayer selectWord() {
        Random random = new Random();
        maxIndex = theWord.getLength();
        System.out.println(maxIndex);
        wordIndex = random.nextInt((maxIndex));
        aWord = theWord.getElementAt(wordIndex);
        aPlayer.setGuessedWord(aWord);
        System.out.println(aWord);
        theWord.remove(wordIndex);
        return aPlayer;
    } // selectWord()
}

