package com.example.tic_tac_toe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class TicTacToe2 extends AppCompatActivity implements View.OnClickListener {
    MediaPlayer mySong;

    private TextView playerOneScore, playerTwoScore, playerStatus, playerOne, playerTwo;
    private Button[] buttons = new Button [9];
    private Button reset;

    private int playerOneScoreCount, playerTwoScoreCount, roundCount;
    boolean activePlayer;

//    p1 => 0
//    p2 => 1
//    empty => 2

    int [] gameState = {2,2,2,2,2,2,2,2,2};

    int [] [] winningPositions = {
            {0,1,2}, {3,4,5}, {6,7,8},     //rows
            {0,3,6}, {1,4,7}, {2,5,8},     //columns
            {0,4,8}, {2,4,6}              //diagonal
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tic_tac_toe );
        mySong= MediaPlayer.create(TicTacToe2.this,R.raw.music);

        playerOneScore = (TextView) findViewById(R.id.playerOneScore);
        playerTwoScore = (TextView) findViewById(R.id.playerTwoScore);
        playerStatus = (TextView) findViewById(R.id.playerStatus);
        playerOne = findViewById(R.id.playerOne);
        playerTwo = findViewById(R.id.playerTwo);

        String p1 = getIntent().getStringExtra("keyeditTextPlayerOne");
        String p2 = getIntent().getStringExtra("keyeditTextPlayerTwo");

        playerOne.setText(p1);
        playerTwo.setText(p2);

        reset = (Button) findViewById(R.id.reset);

        for (int i=0; i < buttons.length; i++) {
            String buttonID = "btn_" +i;
            int resourceID = getResources().getIdentifier(buttonID, "id", getPackageName());
            buttons[i] = (Button) findViewById(resourceID);
            buttons[i].setOnClickListener(this);
        }

        roundCount = 0;
        playerOneScoreCount = 0;
        playerTwoScoreCount = 0;
        activePlayer = true;
    }

    public void playMusic(View v){
        mySong.start();
    }

    public void onPause() {
        super.onPause();
        mySong.release();
    }

    @Override
    public void onClick(View v) {

        String x1 = getIntent().getStringExtra("keyeditTextPlayerOne");
        String x2 = getIntent().getStringExtra("keyeditTextPlayerTwo");
        //       Log.i("test","button is pressed");      see if buttons work
        if (!((Button) v).getText().toString().equals("")) {
            return;
        }
        String buttonID = v.getResources().getResourceEntryName(v.getId());
        int gameStatePointer = Integer.parseInt(buttonID.substring(buttonID.length() - 1, buttonID.length()));

        if (activePlayer) {
            ((Button) v).setText("◣");
            ((Button) v).setTextColor(Color.parseColor("#FFC34A"));
            gameState[gameStatePointer] = 0;
        } else {
            ((Button) v).setText("❏");
            ((Button) v).setTextColor(Color.parseColor("#70FFEA"));
            gameState[gameStatePointer] = 1;
        }
        roundCount++;

        if (checkWinner()) {
            if (activePlayer) {
                playerOneScoreCount++;
                updatePlayerScore();
                Toast.makeText(this, x1 + " Won", Toast.LENGTH_SHORT).show();
                playAgain();
            } else {
                playerTwoScoreCount++;
                updatePlayerScore();
                Toast.makeText(this, x2 + " Won", Toast.LENGTH_SHORT).show();
                playAgain();
            }
        } else if (roundCount == 9) {
            playAgain();
            Toast.makeText(this, "No Winner", Toast.LENGTH_SHORT).show();
        } else {
            activePlayer = !activePlayer;
        }
        if (playerOneScoreCount > playerTwoScoreCount) {
            playerStatus.setText(x1 + " Is Winning");
        } else if (playerTwoScoreCount > playerOneScoreCount) {
            playerStatus.setText(x2 + " Is Winning");
        } else {
            playerStatus.setText("What A Nice Day");
        }

        // Will take user to the end screen once either player 1 or 2 reaches 10 wins
        if (playerOneScoreCount == 5) {
            Intent intent = new Intent(this, WinScreen.class);
            String p1 = getIntent().getStringExtra("keyeditTextPlayerOne");
            intent.putExtra("keyeditTextPlayerOne", p1);
            startActivity(intent);
        }

        if (playerTwoScoreCount == 5) {
            Intent intent = new Intent(this, EndScreen.class);
            String p2 = getIntent().getStringExtra("keyeditTextPlayerTwo");
            intent.putExtra("keyeditTextPlayerTwo", p2);
            startActivity(intent);
        }

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playAgain();
                playerOneScoreCount = 0;
                playerTwoScoreCount = 0;
                playerStatus.setText("Breathe Deep, Relax");
                updatePlayerScore();
            }
        });


    }




    public boolean checkWinner () {
        boolean winnerResult = false;

        for (int [] winningPosition : winningPositions) {
            if (gameState[winningPosition[0]] == gameState[winningPosition[1]] &&
                    gameState[winningPosition[1]] == gameState[winningPosition[2]] &&
                    gameState[winningPosition[0]] != 2) {
                winnerResult = true;

            }
        }
        return winnerResult;
    }
    public void updatePlayerScore() {
        playerOneScore.setText(Integer.toString(playerOneScoreCount));
        playerTwoScore.setText(Integer.toString(playerTwoScoreCount));
    }
    public void playAgain () {
        roundCount = 0;
        activePlayer = true;

        for (int i = 0; i < buttons.length; i++) {
            gameState[i] = 2;
            buttons[i].setText("");
        }
    }
}
