package com.example.goaled;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class UserStatsFragment extends Fragment {
    UserLocal user;
    Attributes fragment1Action;

    LastWeek fragment2Action;
    LastMonth fragment3Action;
    Last120Days fragment4Action;
    Spinner spinner;
    List<String> names;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_user_stats, container, false);

        user = (UserLocal) getArguments().getSerializable("USER");

        spinner = view.findViewById(R.id.spinner);
        fragment1Action = new Attributes();
        //fragment2Action = new LastWeek();
        fragment3Action = new LastMonth();
        fragment4Action = new Last120Days();

        names = new ArrayList<>();
        names.add("Attributes");
        names.add("Last Week");
        names.add("Last Month");
        names.add("Last 120 Days");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(view.getContext(), R.layout.item, names);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:
                        selectFragment1(fragment1Action);
                        break;
                    case 1:
                        selectFragment2(fragment2Action);
                        break;
                    case 2:
                        selectFragment3(fragment3Action);
                        break;
                    case 3:
                        selectFragment4(fragment4Action);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        return view;
    }

    public static UserStatsFragment newInstance(UserLocal userLocal) {
        UserStatsFragment fragment = new UserStatsFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("USER", userLocal);
        fragment.setArguments(bundle);

        return fragment;
    }
    private void selectFragment1(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
        fragment = Attributes.newInstance(user);
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit();
    }
    private void selectFragment2(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
        fragment = LastWeek.newInstance(user);
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit();
    }
    private void selectFragment3(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
        fragment = LastMonth.newInstance(user);
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit();
    }
    private void selectFragment4(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
        fragment = Last120Days.newInstance(user);
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit();
    }
}
