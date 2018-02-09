package com.example.davide.tris;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        char [ ] [ ] gioco = new char [3] [3];
        for (int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                gioco [i] [j] = 'a'; //nel caso in cui il valore interno sia 'a' la casella è segnata come vuota
            }
        }
        char Player = 'x';//viene impostato "X" come giocatore
        boolean isX = true; // booleano che indica se il giocatore coerrente è X o meno, e gestisce i turni
    }


}
