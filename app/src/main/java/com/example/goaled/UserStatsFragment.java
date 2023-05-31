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
    Attributes fragment1;
    StatDistribution fragment2;
    ProductivityIndex fragment3;
    DailyGoals fragment4;
    Wisdom fragment5;
    Strength fragment6;
    Intellect fragment7;
    Endurance fragment8;
    Creativity fragment9;
    Spinner spinner;
    Spinner spinner1;
    List<String> names;
    List<String> noOfDays;
    int selection;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_user_stats, container, false);

        user = (UserLocal) getArguments().getSerializable("USER");

        spinner = view.findViewById(R.id.spinner);
        fragment1 = new Attributes();
        fragment2 = new StatDistribution();
        fragment3 = new ProductivityIndex();
        fragment4 = new DailyGoals();
        fragment5 = new Wisdom();
        fragment6 = new Strength();
        fragment7 = new Intellect();
        fragment8 = new Endurance();
        fragment9 = new Creativity();

        names = new ArrayList<>();
        names.add("Attributes");
        names.add("Stat Distribution");
        names.add("Productivity Index");
        names.add("Daily Goals");
        names.add("Wisdom");
        names.add("Strength");
        names.add("Intellect");
        names.add("Endurance");
        names.add("Creativity");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(view.getContext(), R.layout.item, names);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:
                        attributes(fragment1);
                        break;
                    case 1:
                        statDistribution(fragment2, selection);
                        break;
                    case 2:
                        productivityIndex(fragment3, selection);
                        break;
                    case 3:
                        dailyGoals(fragment4, selection);
                        break;
                    case 4:
                        wisdom(fragment5, selection);
                        break;
                    case 5:
                        strength(fragment6, selection);
                        break;
                    case 6:
                        intellect(fragment7, selection);
                        break;
                    case 7:
                        endurance(fragment8, selection);
                        break;
                    case 8:
                        creativity(fragment9, selection);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        noOfDays = new ArrayList<>();
        noOfDays.add("0");
        noOfDays.add("Last Week");
        noOfDays.add("Last Month");
        noOfDays.add("Last 120 Days");

        ArrayAdapter<String> arrayAdapter1 = new ArrayAdapter<>(view.getContext(), R.layout.item, names);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(arrayAdapter);

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:
                        selection = 7;
                        break;
                    case 1:
                        selection = 30;
                        break;
                    case 2:
                        selection = 120;
                        break;
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
    private void attributes(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
        fragment = Attributes.newInstance(user);
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit();
    }
    private void statDistribution(Fragment fragment, int days) {
        FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
        fragment = StatDistribution.newInstance(user, days);
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit();
    }
    private void productivityIndex(Fragment fragment, int days) {
        FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
        fragment = ProductivityIndex.newInstance(user, days);
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit();
    }
    private void dailyGoals(Fragment fragment, int days) {
        FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
        fragment = DailyGoals.newInstance(user, days);
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit();
    }

    private void wisdom(Fragment fragment, int days) {
        FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
        fragment = Wisdom.newInstance(user, days);
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit();
    }

    private void strength(Fragment fragment, int days) {
        FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
        fragment = Strength.newInstance(user, days);
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit();
    }

    private void intellect(Fragment fragment, int days) {
        FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
        fragment = Intellect.newInstance(user, days);
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit();
    }

    private void endurance(Fragment fragment, int days) {
        FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
        fragment = Endurance.newInstance(user, days);
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit();
    }

    private void creativity(Fragment fragment, int days) {
        FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
        fragment = Creativity.newInstance(user, days);
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit();
    }
}
