package com.example.goaled;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class UserActivitiesFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_user_activities, container, false);

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