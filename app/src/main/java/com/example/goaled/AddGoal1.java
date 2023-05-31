package com.example.goaled;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AddGoal1 extends AppCompatActivity {

    private UserActivity dasAktivitat;


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

        UserLocal userLocal = (UserLocal) getIntent().getSerializableExtra("USER");
        ViewGroup layout = findViewById(R.id.lyt);
        for(UserActivity activity : userLocal.getAllActivities()) {
            View showGoal = LayoutInflater.from(this).inflate(R.layout.goal_activity_container, null);
            TextView namename = showGoal.findViewById(R.id.timegoal);
            namename.setText(activity.getName());
            layout.addView(showGoal);
            showGoal.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dasAktivitat = activity;
                    TextView activityNameView = findViewById(R.id.textView6);
                    activityNameView.setText("Chosen Activity: " + dasAktivitat.getName());
                }
            });
        }


        conwact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserLocal userLocal = (UserLocal) getIntent().getSerializableExtra("USER");
                Intent intent = new Intent(getBaseContext(), AddGoalWithActivity.class);
                intent.putExtra("USER", userLocal);
                intent.putExtra("ACTIVITY", dasAktivitat);
                startActivity(intent);
            }
        });
    }
}