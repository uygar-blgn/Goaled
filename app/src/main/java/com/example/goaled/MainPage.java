package com.example.goaled;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        BottomNavigationView bottomNavBar = findViewById(R.id.bottom_navigation);
        bottomNavBar.setItemIconTintList(null);
    }
}