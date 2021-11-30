package com.example.tic_tac_toe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class StartMenu extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_menu);
    }

    /** Called when the user clicks the Icons button */
    public void iconView(View view) {
        Intent intent = new Intent(this, IconSelect.class);
        startActivity(intent);
    }

    /** Called when the user clicks the Start Game button */
    public void startView(View view) {
        Intent intent2 = new Intent(this, NameSelect.class);
        startActivity(intent2);
    }
}
