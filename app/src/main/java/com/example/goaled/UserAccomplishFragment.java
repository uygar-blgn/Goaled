package com.example.goaled;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

public class UserAccomplishFragment extends Fragment {

    private UserLocal userLocal;
    private String activityName;
    private int hours;
    private int intensity;

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
        finito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText name = rootView.findViewById(R.id.activityAcc);
                EditText hour = rootView.findViewById(R.id.activityHour);
                EditText intense = rootView.findViewById(R.id.intensitynum);
                activityName = name.getText().toString();
                hours = Integer.parseInt(hour.getText().toString());
                intensity = Integer.parseInt(intense.getText().toString());
                UserActivity akt = new UserActivity("placeholder","placeholder","placeholder",1);
                for(UserActivity aktivite : userLocal.getAllActivities()) {
                    if(aktivite.getName().equals(activityName)) {
                        akt = aktivite;
                    }
                }
                //Log.d("bruhsj",akt.getName());
                userLocal.newAccomplishment(new UserAccomplishment(akt, (double) hours, (double) intensity));
                //Log.d("cusal", userLocal.getAllAccomplishments().get(0).getIntensity() + "");
                Intent intent = new Intent(getContext(), MainPage.class);
                intent.putExtra("USER", userLocal);
                startActivity(intent);
            }
        });



        return rootView;
    }
}