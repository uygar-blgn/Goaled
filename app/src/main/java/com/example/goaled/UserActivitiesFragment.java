package com.example.goaled;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class UserActivitiesFragment extends Fragment {

    private UserLocal userLocal;

    public static UserActivitiesFragment newInstance(UserLocal userLocal) {
        UserActivitiesFragment fragment = new UserActivitiesFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("USER", userLocal);
        fragment.setArguments(bundle);

        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_user_activities, container, false);

        userLocal = (UserLocal) getArguments().getSerializable("USER");

        for(UserActivity activity : userLocal.getAllActivities()) {

            if(!userLocal.getAllActivities().isEmpty()) {

                View activityView = LayoutInflater.from(getContext()).inflate(R.layout.add_activity, null);

                TextView activityName = activityView.findViewById(R.id.activityname);
                TextView firstStat = activityView.findViewById(R.id.firststat);
                TextView secondStat = activityView.findViewById(R.id.secondstat);
                TextView difficulty = activityView.findViewById(R.id.diff);

                activityName.setText(activity.getName());
                firstStat.setText(activity.getPrimaryStat());
                secondStat.setText(activity.getSecondaryStat());
                difficulty.setText("Difficulty: " + (int) activity.getDifficulty());

                ViewGroup mainLayout = rootView.findViewById(R.id.lin);
                mainLayout.addView(activityView);


            }
        }

        rootView.findViewById(R.id.legoal).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), EditActivity.class);
                intent.putExtra("USER", userLocal);
                startActivity(intent);
            }
        });

        return rootView;
    }
}