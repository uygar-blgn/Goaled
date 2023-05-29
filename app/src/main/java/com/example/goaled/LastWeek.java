package com.example.goaled;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;

public class LastWeek extends Fragment {

    UserLocal user;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.last_week_chart, container, false);
        LineChart lineChart = (LineChart) view.findViewById(R.id.last_week);

        user = (UserLocal) getArguments().getSerializable("USER");

        ArrayList<Double> weeklyPi = user.getPIWithinDays(7);
        ArrayList<Entry> values = new ArrayList<>();

        for(int i = 0; i < 7; i++){
            values.add(new Entry(i, weeklyPi.get(i).intValue()));
        }

        LineDataSet lineDataSet = new LineDataSet(values, "PI");

        LineData data = new LineData(lineDataSet);

        lineChart.setData(data);

        lineChart.invalidate();

        return view;
    }

    public static LastWeek newInstance(UserLocal userLocal) {
        LastWeek fragment = new LastWeek();
        Bundle bundle = new Bundle();
        bundle.putSerializable("USER", userLocal);
        fragment.setArguments(bundle);

        return fragment;
    }
}