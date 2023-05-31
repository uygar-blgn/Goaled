package com.example.goaled;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.BarChart;

import java.util.HashMap;

public class StatDistribution extends Fragment {

    UserLocal user;
    int days;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_stat_distribution, container, false);
        BarChart barChart = (BarChart) view.findViewById(R.id.bar_chart);

        user = (UserLocal) getArguments().getSerializable("USER");
        days = getArguments().getInt("days");

        HashMap statDist = user.getStatDistributionWithinDays(days);


        return view;
    }

    public static StatDistribution newInstance(UserLocal userLocal, int days) {
        StatDistribution fragment = new StatDistribution();
        Bundle bundle = new Bundle();
        bundle.putInt("days", days);
        bundle.putSerializable("USER", userLocal);
        fragment.setArguments(bundle);

        return fragment;
    }
}