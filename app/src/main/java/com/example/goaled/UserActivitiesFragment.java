package com.example.goaled;

import android.os.Bundle;
import android.util.Log;
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
        Log.d("Uygarsama", userLocal.getAllActivities().get(0).getName());
        for(UserActivity activity : userLocal.getAllActivities()) {

            TextView text = new TextView(getContext());
            text.setText(activity.getName());
            ViewGroup mainLayout = rootView.findViewById(R.id.lnr);
            mainLayout.addView(text);

        }

        rootView.findViewById(R.id.addadd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater layoutInflater = LayoutInflater.from(getContext());
                View dynamicLayout = layoutInflater.inflate(R.layout.add_activity, container, false);

                ViewGroup mainLayout = rootView.findViewById(R.id.lnr);
                mainLayout.addView(dynamicLayout);
            }
        });

        return rootView;
    }
}