package com.example.goaled;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class CharSelection extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_char_selection);
    }

    public void goToMain(View view) {
        Intent intent = new Intent(this, MainPage.class);
        startActivity(intent);
    }
}