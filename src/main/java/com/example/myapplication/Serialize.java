package com.example.myapplication;

import com.example.myapplication.linked_data_structures.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Serialize implements Serializable {
    static DoublyLinkedList<HangmanPlayer> players ;
    public Serialize(){
        players = new DoublyLinkedList<HangmanPlayer>();
        System.out.println(players.getLength());
    }
    public void deserialize()
    {
        try {
            // Reading the object from a file
            File file = new File(GameActivity.getGame().getBaseContext().getFilesDir(), "savedGame.ser");

            FileInputStream fileStream = new FileInputStream(file);
            ObjectInputStream in = new ObjectInputStream(fileStream);

            // Method for deserialization of object
            players = (DoublyLinkedList<HangmanPlayer>) in.readObject();
//            in.close();
//            fileStream.close();

        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
        System.out.println("num players --> " + players.getLength());
    }
//
    public void serialize() {
        try {
            // Reading the object from a file
            File file = new File(GameActivity.getGame().getBaseContext().getFilesDir(), "savedGame.ser");

            FileOutputStream fileStream = new FileOutputStream(file);
            ObjectOutputStream out = new ObjectOutputStream(fileStream);

            // Method for deserialization of object
            System.out.println("num players --> " + players.getLength());
            out.writeObject(players);
//            out.close();
//            fileStream.close();

        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
        System.out.println("num players --> " + players.getLength());
    }

    public static DoublyLinkedList<HangmanPlayer> getPlayers(){
        return Serialize.players;
    }
}
