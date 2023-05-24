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

    public void goToCharSelection(View view) {
        Intent intent = new Intent(this, MainAuthActivity.class);
        startActivity(intent);
    }
}