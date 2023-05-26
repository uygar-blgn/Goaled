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
        UserActivity activity = new UserActivity("31", "Endurance", "Strength", 5);
        for(int i = 0; i < 1; i++) {

            View aktivite = LayoutInflater.from(getContext()).inflate(R.layout.add_activity, null);
            TextView activityName = aktivite.findViewById(R.id.activityname);
            TextView firstStat = aktivite.findViewById(R.id.firststat);
            TextView secondStat = aktivite.findViewById(R.id.secondstat);

            activityName.setText(activity.getName());
            firstStat.setText(activity.getPrimaryStat());
            secondStat.setText(activity.getSecondaryStat());


            ViewGroup mainLayout = rootView.findViewById(R.id.lnr);
            mainLayout.addView(aktivite);
        }

        rootView.findViewById(R.id.addadd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), EditActivity.class);
                startActivity(intent);
            }
        });

        return rootView;
    }
}