package com.example.goaled;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class GettingStarted extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.getting_started);
    }

    public void onClick(View v){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
}