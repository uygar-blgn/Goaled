package com.example.goaled;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

public class UserAccomplishFragment extends Fragment {

    private UserLocal userLocal;
    private int hours;
    private int intensity;
    private UserActivity dasAktivitat;

    public static UserAccomplishFragment newInstance(UserLocal userLocal) {
        UserAccomplishFragment fragment = new UserAccomplishFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("USER", userLocal);
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_user_accomplish, container, false);

        userLocal = (UserLocal) getArguments().getSerializable("USER");

        Button finito = rootView.findViewById(R.id.addAccomp);

        ViewGroup layout = rootView.findViewById(R.id.lyt);
        for(UserActivity activity : userLocal.getAllActivities()) {
            View showGoal = LayoutInflater.from(getContext()).inflate(R.layout.goal_activity_container, null);
            TextView namename = showGoal.findViewById(R.id.timegoal);
            namename.setText(activity.getName());
            layout.addView(showGoal);
            showGoal.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dasAktivitat = activity;
                    TextView activityNameView = rootView.findViewById(R.id.textView13);
                    activityNameView.setText("Chosen Activity: " + dasAktivitat.getName());
                }
            });
        }

        finito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText hour = rootView.findViewById(R.id.activityHour);
                EditText intense = rootView.findViewById(R.id.intensitynum);
                if(dasAktivitat == null || hour.getText().toString().equals("") || intense.getText().toString().equals("")) {
                    Toast.makeText(getContext(), "Please enter all details!", Toast.LENGTH_SHORT).show();
                }
                else {
                    hours = Integer.parseInt(hour.getText().toString());
                    intensity = Integer.parseInt(intense.getText().toString());
                    userLocal.newAccomplishment(new UserAccomplishment(dasAktivitat, (double) hours, (double) intensity));
                    Intent intent = new Intent(getContext(), MainPage.class);
                    intent.putExtra("USER", userLocal);
                    startActivity(intent);
                }
            }
        });



        return rootView;
    }
}