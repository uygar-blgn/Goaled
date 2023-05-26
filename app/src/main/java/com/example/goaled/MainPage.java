package com.example.goaled;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseUser;

public class MainPage extends AppCompatActivity {

    BottomNavigationView bottomNavBar;

    private UserAccomplishFragment accomplishFragment = new UserAccomplishFragment();
    private UserActivitiesFragment activitiesFragment = new UserActivitiesFragment();
    private UserGoalsFragment goalsFragment = new UserGoalsFragment();
    private UserProfileFragment profileFragment = new UserProfileFragment();
    private UserStatsFragment statsFragment = new UserStatsFragment();

    private UserLocal userLocal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        bottomNavBar = findViewById(R.id.bottom_navigation);
        bottomNavBar.setItemIconTintList(null);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, profileFragment).commit();
        bottomNavBar.setSelectedItemId(R.id.profile);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();


        bottomNavBar.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.accomplish:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, accomplishFragment).commit();
                        return true;
                    case R.id.activities:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, activitiesFragment).commit();
                        return true;
                    case R.id.goals:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, goalsFragment).commit();
                        return true;
                    case R.id.profile:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, profileFragment).commit();
                        return true;
                    case R.id.stats:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, statsFragment).commit();
                        return true;
                }
                return false;
            }
        });


    }
}