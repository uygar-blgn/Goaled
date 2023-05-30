package com.example.goaled;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class EditActivity extends AppCompatActivity {

    private String primaryStat;
    private String secondaryStat;
    private double primaryDifficulty;
    private double secondaryDifficulty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        RadioGroup grup1 = findViewById(R.id.radioGroup1);
        RadioGroup grup2 = findViewById(R.id.radioGroup2);
        RadioGroup yorum1 = findViewById(R.id.grupYorum1);
        RadioGroup yorum2 = findViewById(R.id.grupYorum2);

        grup1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton r1 = findViewById(R.id.radyo1);
                RadioButton r2 = findViewById(R.id.radyo2);
                RadioButton r3 = findViewById(R.id.radyo3);

                if(r1.isChecked()) {
                    r2.setChecked(false);
                    r3.setChecked(false);
                    primaryDifficulty = 3;
                }
                else if(r2.isChecked()) {
                    r1.setChecked(false);
                    r3.setChecked(false);
                    primaryDifficulty = 5;
                }
                else if(r3.isChecked()) {
                    r1.setChecked(false);
                    r2.setChecked(false);
                    primaryDifficulty = 8;
                }
            }
        });

        grup2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton r1 = findViewById(R.id.radyo4);
                RadioButton r2 = findViewById(R.id.radyo5);
                RadioButton r3 = findViewById(R.id.radyo6);

                if(r1.isChecked()) {
                    r2.setChecked(false);
                    r3.setChecked(false);
                    secondaryDifficulty = 3;
                }
                else if(r2.isChecked()) {
                    r1.setChecked(false);
                    r3.setChecked(false);
                    secondaryDifficulty = 5;
                }
                else if(r3.isChecked()) {
                    r1.setChecked(false);
                    r2.setChecked(false);
                    secondaryDifficulty = 8;
                }
            }
        });

        yorum1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
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
                    primaryStat = "Strength";
                }
                else if(r2.isChecked()) {
                    r1.setChecked(false);
                    r3.setChecked(false);
                    r4.setChecked(false);
                    r5.setChecked(false);
                    primaryStat = "Intellect";
                }
                else if(r3.isChecked()) {
                    r1.setChecked(false);
                    r2.setChecked(false);
                    r4.setChecked(false);
                    r5.setChecked(false);
                    primaryStat = "Endurance";
                }
                else if(r4.isChecked()) {
                    r1.setChecked(false);
                    r2.setChecked(false);
                    r3.setChecked(false);
                    r5.setChecked(false);
                    primaryStat = "Creativity";
                }
                else if(r5.isChecked()) {
                    r1.setChecked(false);
                    r2.setChecked(false);
                    r3.setChecked(false);
                    r4.setChecked(false);
                    primaryStat = "Wisdom";
                }
            }
        });

        yorum2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton r1 = findViewById(R.id.radioButton24);
                RadioButton r2 = findViewById(R.id.radioButton25);
                RadioButton r3 = findViewById(R.id.radioButton26);
                RadioButton r4 = findViewById(R.id.radioButton27);
                RadioButton r5 = findViewById(R.id.radioButton28);

                if(r1.isChecked()) {
                    r2.setChecked(false);
                    r3.setChecked(false);
                    r4.setChecked(false);
                    r5.setChecked(false);
                    secondaryStat = "Strength";
                }
                else if(r2.isChecked()) {
                    r1.setChecked(false);
                    r3.setChecked(false);
                    r4.setChecked(false);
                    r5.setChecked(false);
                    secondaryStat = "Intellect";
                }
                else if(r3.isChecked()) {
                    r1.setChecked(false);
                    r2.setChecked(false);
                    r4.setChecked(false);
                    r5.setChecked(false);
                    secondaryStat = "Endurance";
                }
                else if(r4.isChecked()) {
                    r1.setChecked(false);
                    r2.setChecked(false);
                    r3.setChecked(false);
                    r5.setChecked(false);
                    secondaryStat = "Creativity";
                }
                else if(r5.isChecked()) {
                    r1.setChecked(false);
                    r2.setChecked(false);
                    r3.setChecked(false);
                    r4.setChecked(false);
                    secondaryStat = "Wisdom";
                }
            }
        });

        Button buton = findViewById(R.id.finito);
        buton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText name = findViewById(R.id.activityNameText);
                String activityName = name.getText().toString();
                UserLocal user = (UserLocal) getIntent().getSerializableExtra("USER");

                if ( activityName.length() < 21 ) {

                    if (user.isActivityNameNew(activityName)) {
                        Log.d("uygarsama", user.getFullName());
                        double diff = (primaryDifficulty * 2 + secondaryDifficulty) / 3;
                        user.newActivity(new UserActivity(activityName, primaryStat, secondaryStat, diff));
                        Intent intent = new Intent(getBaseContext(), MainPage.class);
                        intent.putExtra("USER", user);
                        startActivity(intent);
                    } else {
                        Toast.makeText(EditActivity.this, "The activity name should be different than other activities!", Toast.LENGTH_LONG).show();
                    }

                } else {
                    Toast.makeText(EditActivity.this, "The activity name should be at most 20 characters!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}