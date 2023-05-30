package com.example.goaled;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.content.res.AppCompatResources;
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

        TextView level = rootView.findViewById(R.id.level);
        level.setText("Lvl. " + userLocal.getLevel());

        TextView xp = rootView.findViewById(R.id.xp);
        xp.setText("Xp. " + userLocal.getXp());

        ProgressBar progBar = rootView.findViewById(R.id.lvlprog);
        progBar.setMax(userLocal.getXpForNextLevel());
        progBar.setProgress(userLocal.getXp());

        TextView username = rootView.findViewById(R.id.username);
        username.setText(userLocal.getFullName());

        ImageView pfp = rootView.findViewById(R.id.pfp);
        TextView cls = rootView.findViewById(R.id.classclass);

        if(userLocal.getUserClass().equals("Strength")) {
            pfp.setBackground(AppCompatResources.getDrawable(getContext(), R.drawable.strguy));
            cls.setText("Strength Class");
        }
        else if(userLocal.getUserClass().equals("Intellect")) {
            pfp.setBackground(AppCompatResources.getDrawable(getContext(), R.drawable.intguy));
            cls.setText("Intellect Class");
        }
        else if(userLocal.getUserClass().equals("Endurance")) {
            pfp.setBackground(AppCompatResources.getDrawable(getContext(), R.drawable.endguy));
            cls.setText("Endurance Class");
        }
        else if(userLocal.getUserClass().equals("Creativity")) {
            pfp.setBackground(AppCompatResources.getDrawable(getContext(), R.drawable.creguy));
            cls.setText("Creativity Class");
        }
        else if(userLocal.getUserClass().equals("Wisdom")) {
            pfp.setBackground(AppCompatResources.getDrawable(getContext(), R.drawable.wisguy));
            cls.setText("Wisdom Class");
        }

        return rootView;
    }
}