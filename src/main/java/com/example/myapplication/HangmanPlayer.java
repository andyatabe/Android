package com.example.myapplication;

import java.io.Serializable;

public class HangmanPlayer implements Serializable {
    private String playerName, guessedWord;
    private int totalWins, totalLosses, gamesPlayed, wrongGuesses;

    // Constructors
    public HangmanPlayer() {
        playerName = "";
        guessedWord = "";
        totalWins = 0;
        totalLosses = 0;
        gamesPlayed = 0;
        wrongGuesses = 0;
    } // HangmanPlayer()

    public HangmanPlayer(String pName, int wins, int losses) {
        playerName = pName;
        guessedWord = "";
        totalWins = wins;
        totalLosses = losses;
        gamesPlayed = 0;
        wrongGuesses = 0;
    }// HangmanPlayer(String, int, int)

    public HangmanPlayer(String pName, String gWord, int wins, int losses, int gPlayed, int wGuesses) {
        playerName = pName;
        guessedWord = gWord;
        totalWins = wins;
        totalLosses = losses;
        gamesPlayed = gPlayed;
        wrongGuesses = wGuesses;
    }// HangmanPlayer(String, int, int, int)

    // Accessor and Mutator methods
    public String getGuessedWord() {
        return guessedWord;
    } // getGuessedWord()

    public void setGuessedWord(String gWord) {
        guessedWord = gWord;
    } // setGuessedWord()

    public int getWrongGuesses() {
        return wrongGuesses;
    } // getWrongGuesses()

    public void setWrongGuesses(int wGuesses) {
        wrongGuesses = wGuesses;
    } // setWrongGuesses()

    public String getPlayerName() {
        return playerName;
    } // getPlayerName()

    public void setPlayerName(String pName) {
        playerName = pName;
    } // setTotalWins()

    public int getTotalWins() {
        return totalWins;
    } // setTotalWins()

    public void setTotalWins(int wins) {
        totalWins = wins;
    } // setTotalWins()

    public int getTotalLosses() {
        return totalLosses;
    } // getTotalLosses()

    public void setTotalLosses(int losses) {
        totalLosses = losses;
    } // setTotalLosses()

    public int getGamesPlayed() {
        return gamesPlayed;
    } // getGamesPlayed()

    public void setGamesPlayed(int gPlayed) {
        gamesPlayed = gPlayed;
    } // setGamesPlayed

    // methods adds the number of time the user has made a wrong guess
    public void incrementWrongGuesses() {
        wrongGuesses++;
    }// incrementWrongGuesses()

    public void incrementWins() {
        ++totalWins;
    }

    public void incrementLosses() {
        ++totalLosses;
    }

    public void incrementGamesPlayed() {
        ++gamesPlayed;
    }
} // HangmanPlayer()