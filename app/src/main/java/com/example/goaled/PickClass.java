package com.example.goaled;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class PickClass extends AppCompatActivity {
    private UserLocal userLocal;
    private String chosenClass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_class);

        userLocal = (UserLocal) getIntent().getSerializableExtra("USER");

        CardView c1 = findViewById(R.id.legoal);
        CardView c2 = findViewById(R.id.cardView2);
        CardView c3 = findViewById(R.id.cardView3);
        CardView c4 = findViewById(R.id.cardView4);
        CardView c5 = findViewById(R.id.cardView);
        TextView tw = findViewById(R.id.textView4);

        c1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chosenClass = "Strength";
                userLocal.setUserClass(chosenClass);
                userLocal.updateStatMultipliers();
                tw.setText("Chosen Class: " + chosenClass);
            }
        });
        c2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chosenClass = "Intellect";
                userLocal.setUserClass(chosenClass);
                userLocal.updateStatMultipliers();
                tw.setText("Chosen Class: " + chosenClass);
            }
        });
        c3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chosenClass = "Endurance";
                userLocal.setUserClass(chosenClass);
                userLocal.updateStatMultipliers();
                tw.setText("Chosen Class: " + chosenClass);
            }
        });
        c4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chosenClass = "Creativity";
                userLocal.setUserClass(chosenClass);
                userLocal.updateStatMultipliers();
                tw.setText("Chosen Class: " + chosenClass);
            }
        });
        c5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chosenClass = "Wisdom";
                userLocal.setUserClass(chosenClass);
                userLocal.updateStatMultipliers();
                tw.setText("Chosen Class: " + chosenClass);
            }
        });

    }

    public void goToCharSelection(View view) {
        userLocal.setUserClass(chosenClass);
        Intent intent = new Intent(this, MainPage.class);
        intent.putExtra("USER", userLocal);
        startActivity(intent);
    }
}