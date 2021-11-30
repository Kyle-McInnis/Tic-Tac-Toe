package com.example.tic_tac_toe;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class NameSelect2 extends AppCompatActivity {

    private EditText editTextPlayerOne, editTextPlayerTwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name_select);
        editTextPlayerOne = findViewById(R.id.editTextPlayerOne);
        editTextPlayerTwo = findViewById(R.id.editTextPlayerTwo);

    }

    /** Called when the user clicks the Play Game button */
    public void gameView(View view) {

        String etp1 = editTextPlayerOne.getText().toString();
        String etp2 = editTextPlayerTwo.getText().toString();

        Intent intent = new Intent(this, TicTacToe2.class);
        intent.putExtra("keyeditTextPlayerOne", etp1);
        intent.putExtra("keyeditTextPlayerTwo", etp2);
        startActivity(intent);
    }
}
