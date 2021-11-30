package com.example.tic_tac_toe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class EndScreen extends AppCompatActivity {

    private TextView textViewYouLose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_screen);

        textViewYouLose = findViewById(R.id.textViewYouLose);

        String pTwoWin = getIntent().getStringExtra("keyeditTextPlayerTwo");

        textViewYouLose.setText(pTwoWin);

        MediaPlayer play= MediaPlayer.create(EndScreen.this,R.raw.zapsplatwin);
        play.start();
    }

    /** Called when the user clicks the Main Menu button */
    public void mainView(View view) {
        Intent intent = new Intent(this, StartMenu.class);
        startActivity(intent);
    }

    /** Called when the user clicks the Play Again button */
    public void playView(View view) {
        Intent intent2 = new Intent(this, NameSelect.class);
        startActivity(intent2);
    }
}