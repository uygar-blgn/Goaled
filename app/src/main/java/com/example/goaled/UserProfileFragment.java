package com.example.goaled;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class UserProfileFragment extends Fragment {

    private UserLocal userLocal;

    public static UserProfileFragment newInstance(UserLocal userLocal) {
        UserProfileFragment fragment = new UserProfileFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("USER", userLocal);
        fragment.setArguments(bundle);

        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_user_profile, container, false);

        userLocal = (UserLocal) getArguments().getSerializable("USER");

        //TODO level returning null, emincim pls fix
        //TextView level = rootView.findViewById(R.id.level);
        //level.setText("Lvl. " + userLocal.getLevel());

        //TODO progress bar

        TextView username = rootView.findViewById(R.id.username);
        username.setText(userLocal.getFullName());

        //TODO keep profile pic as a variable

        return rootView;
    }
}