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

public class LastMonth extends Fragment {

    UserLocal user;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.last_month_chart, container, false);

        user = (UserLocal) getArguments().getSerializable("USER");

        LineChart lineChart = (LineChart) view.findViewById(R.id.last_month);

        ArrayList<Double> monthlyPi = user.getPIWithinDays(30);
        ArrayList<Entry> values = new ArrayList<>();

        for(int i = 0; i < 7; i++){
            values.add(new Entry(i, monthlyPi.get(i).intValue()));
        }

        LineDataSet lineDataSet = new LineDataSet(values, "PI");

        LineData data = new LineData(lineDataSet);

        lineChart.setData(data);

        lineChart.invalidate();

        return view;
    }

    public static LastMonth newInstance(UserLocal userLocal) {
        LastMonth fragment = new LastMonth();
        Bundle bundle = new Bundle();
        bundle.putSerializable("USER", userLocal);
        fragment.setArguments(bundle);

        return fragment;
    }
}