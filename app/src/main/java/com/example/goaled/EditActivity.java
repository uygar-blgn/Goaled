package com.example.goaled;

import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

public class EditActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        RadioGroup grup1 = findViewById(R.id.radioGroup1);
        RadioGroup grup2 = findViewById(R.id.radioGroup2);

        grup1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton r1 = findViewById(R.id.radyo1);
                RadioButton r2 = findViewById(R.id.radyo2);
                RadioButton r3 = findViewById(R.id.radyo3);

                if(r1.isChecked()) {
                    r2.setChecked(false);
                    r3.setChecked(false);
                }
                else if(r2.isChecked()) {
                    r1.setChecked(false);
                    r3.setChecked(false);
                }
                else if(r3.isChecked()) {
                    r1.setChecked(false);
                    r2.setChecked(false);
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
                }
                else if(r2.isChecked()) {
                    r1.setChecked(false);
                    r3.setChecked(false);
                }
                else if(r3.isChecked()) {
                    r1.setChecked(false);
                    r2.setChecked(false);
                }
            }
        });
    }
}