package com.example.goaled;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddGoalNoActivity extends AppCompatActivity {

    private int PIGoal;
    private String PIorStat = "";
    private String stat = "";
    private UserGoal.Frequency frequency;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_goal_no);


        RadioGroup PIorStatGroup = findViewById(R.id.radioGroup2);
        RadioGroup statGroup = findViewById(R.id.grupYorum1);
        statGroup.setVisibility(View.GONE);
        PIorStatGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton r1 = findViewById(R.id.radioButton);
                RadioButton r2 = findViewById(R.id.radioButton5);

                if(r1.isChecked()) {
                    r2.setChecked(false);
                    PIorStat = "PI";
                    statGroup.setVisibility(View.GONE);
                }
                else if(r2.isChecked()) {
                    r1.setChecked(false);
                    PIorStat = "Stat";
                    statGroup.setVisibility(View.VISIBLE);
                }
            }
        });



        statGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton r1 = findViewById(R.id.radioButton8);
                RadioButton r2 = findViewById(R.id.radioButton7);
                RadioButton r3 = findViewById(R.id.radioButton6);
                RadioButton r4 = findViewById(R.id.radioButton4);
                RadioButton r5 = findViewById(R.id.radioButton3);

                if(r1.isChecked()) {
                    r2.setChecked(false);
                    r3.setChecked(false);
                    r4.setChecked(false);
                    r5.setChecked(false);
                    stat = "Strength";
                }
                if(r2.isChecked()) {
                    r1.setChecked(false);
                    r3.setChecked(false);
                    r4.setChecked(false);
                    r5.setChecked(false);
                    stat = "Intellect";
                }
                if(r3.isChecked()) {
                    r2.setChecked(false);
                    r1.setChecked(false);
                    r4.setChecked(false);
                    r5.setChecked(false);
                    stat = "Endurance";
                }
                if(r4.isChecked()) {
                    r2.setChecked(false);
                    r3.setChecked(false);
                    r1.setChecked(false);
                    r5.setChecked(false);
                    stat = "Creativity";
                }
                if(r5.isChecked()) {
                    r2.setChecked(false);
                    r3.setChecked(false);
                    r4.setChecked(false);
                    r1.setChecked(false);
                    stat = "Wisdom";
                }
            }
        });


        RadioGroup frequencyGroup = findViewById(R.id.basis);
        frequencyGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton r1 = findViewById(R.id.daily);
                RadioButton r2 = findViewById(R.id.monthly);
                RadioButton r3 = findViewById(R.id.weekly);

                if(r1.isChecked()) {
                    r2.setChecked(false);
                    r3.setChecked(false);
                    frequency = UserGoal.Frequency.DAILY;
                }
                if(r2.isChecked()) {
                    r1.setChecked(false);
                    r3.setChecked(false);
                    frequency = UserGoal.Frequency.MONTHLY;
                }
                if(r3.isChecked()) {
                    r1.setChecked(false);
                    r2.setChecked(false);
                    frequency = UserGoal.Frequency.WEEKLY;
                }
            }
        });

        Button finitobuton = findViewById(R.id.button7);
        finitobuton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                UserLocal userLocal = (UserLocal) getIntent().getSerializableExtra("USER");
                EditText PIGoalText = findViewById(R.id.editTextText);

                if (!PIGoalText.getText().toString().trim().equals("")) {
                    PIGoal = Integer.parseInt(PIGoalText.getText().toString());
                }

                Intent intent = new Intent(getBaseContext(), MainPage.class);
                intent.putExtra("USER", userLocal);
                
                boolean allDetailsEntered;
                
                allDetailsEntered = !PIGoalText.getText().toString().trim().equals("");
                
                if (allDetailsEntered) {
                    
                    if (PIorStat.trim().equals("")) {
                        allDetailsEntered = false;
                    }
                    
                    if (frequency == null) {
                        allDetailsEntered = false;
                    }
                    
                    if (PIGoal <= 0) {
                        Toast.makeText(AddGoalNoActivity.this, "Please enter a goal amount greater than zero", Toast.LENGTH_LONG).show();
                        allDetailsEntered = false;
                    }
                    
                }
                
                if (allDetailsEntered) {
                    
                    if (PIorStat.equals("Stat")) {

                        userLocal.newGoal(new UserGoal((double) PIGoal, stat, frequency));
                        startActivity(intent);

                    } else if (PIorStat.equals("PI")) {

                        userLocal.newGoal(new UserGoal(PIGoal, frequency));
                        startActivity(intent);

                    }
                } else {
                    Toast.makeText(AddGoalNoActivity.this, "Please enter all details!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}