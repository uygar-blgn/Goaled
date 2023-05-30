package com.example.goaled;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class UserGoalsFragment extends Fragment {

    private UserLocal userLocal;

    public static UserGoalsFragment newInstance(UserLocal userLocal) {
        UserGoalsFragment fragment = new UserGoalsFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("USER", userLocal);
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_user_goals, container, false);

        userLocal = (UserLocal) getArguments().getSerializable("USER");

        Log.d("logloglog", userLocal.getFullName());

        for(UserGoal goal : userLocal.getAllGoals()) {

            if(!userLocal.getAllGoals().isEmpty()) {

                View goalView = LayoutInflater.from(getContext()).inflate(R.layout.add_goal, null);

                TextView goalActivity = goalView.findViewById(R.id.goalactivity);
                TextView timeGoal = goalView.findViewById(R.id.timegoal);
                TextView PIGoal = goalView.findViewById(R.id.pigoal);

                if(goal.getGoalType() == UserGoal.GoalType.UserActivityWithHours) {
                    goalActivity.setText(goal.getUserActivity().getName());
                    PIGoal.setText("Hours: " + goal.getGoalAmount());
                }
                else if(goal.getGoalType() == UserGoal.GoalType.UserActivityWithPI) {
                    goalActivity.setText(goal.getUserActivity().getName());
                    PIGoal.setText("PI: " + goal.getGoalAmount());
                }
                else if(goal.getGoalType() == UserGoal.GoalType.OnlyStat) {
                    goalActivity.setText(goal.getStat());
                    PIGoal.setText(goal.getGoalAmount() + "");
                }
                else {
                    PIGoal.setText("PI: " + goal.getGoalAmount());
                }

                    timeGoal.setText(goal.getGoalFrequency() + "");

                ViewGroup mainLayout = rootView.findViewById(R.id.lnar);
                mainLayout.addView(goalView);
            }

        }

        rootView.findViewById(R.id.goaled).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), AddGoal1.class);
                intent.putExtra("USER", userLocal);
                startActivity(intent);
            }
        });


        return rootView;
    }
}