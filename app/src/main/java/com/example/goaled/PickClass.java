package com.example.goaled;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class PickClass extends AppCompatActivity {
    private UserLocal userLocal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_class);

        userLocal = (UserLocal) getIntent().getSerializableExtra("USER");
    }

    public void goToCharSelection(View view) {
        Intent intent = new Intent(this, MainPage.class);
        intent.putExtra("USER", userLocal);
        startActivity(intent);
    }
}