package com.example.goaled;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddGoal1 extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_goal1);

        Button conwoutact = findViewById(R.id.conwoutact);
        Button conwact = findViewById(R.id.conwact);

        conwoutact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), AddGoalNoActivity.class);
                UserLocal userLocal = (UserLocal) getIntent().getSerializableExtra("USER");
                intent.putExtra("USER", userLocal);
                startActivity(intent);
            }
        });

        conwact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserLocal userLocal = (UserLocal) getIntent().getSerializableExtra("USER");
                Intent intent = new Intent(getBaseContext(), AddGoalWithActivity.class);
                EditText activityNameText = findViewById(R.id.enteraktivitegoal);
                UserActivity akt = new UserActivity("placeholder","Strength","Endurance", 1);
                boolean activityFound = false;
                for(UserActivity aktivite : userLocal.getAllActivities()) {
                        if(aktivite.getName().equals(activityNameText.getText().toString())) {
                            akt = aktivite;
                            activityFound = true;
                        }
                }
                if(!activityFound) {
                    Toast.makeText(getBaseContext(), "Activity not found!", Toast.LENGTH_LONG).show();
                }
                else {
                    intent.putExtra("ACTIVITY", akt);
                    intent.putExtra("USER",userLocal);
                    startActivity(intent);
                }
            }
        });
    }
}