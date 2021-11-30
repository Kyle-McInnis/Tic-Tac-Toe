package com.example.tic_tac_toe;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class IconSelect extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_icon_select);
    }

    /** Called when the user clicks the Return button */
    public void menuView(View view) {
        Intent intent = new Intent(this, StartMenu.class);
        startActivity(intent);
    }

    public void startView2(View view) {
        Intent intent2 = new Intent(this, NameSelect2.class);
        startActivity(intent2);
    }


    public void startView3(View view) {
        Intent intent3 = new Intent(this, NameSelect3.class);
        startActivity(intent3);
    }


    public void startView4(View view) {
        Intent intent4 = new Intent(this, NameSelect4.class);
        startActivity(intent4);
    }
}
