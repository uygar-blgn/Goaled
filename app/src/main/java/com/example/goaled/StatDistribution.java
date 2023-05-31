package com.example.goaled;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

import java.util.ArrayList;
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

        HashMap<String,Double> statDist = user.getStatDistributionWithinDays(days);

        ArrayList<BarEntry> values = new ArrayList<>();
        values.add(new BarEntry(0, statDist.get("Wisdom").floatValue()));
        values.add(new BarEntry(0, statDist.get("Endurance").floatValue()));
        values.add(new BarEntry(0, statDist.get("Intellect").floatValue()));
        values.add(new BarEntry(0, statDist.get("Strength").floatValue()));
        values.add(new BarEntry(0, statDist.get("Creativity").floatValue()));

        XAxis xAxis = barChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM); // Set the position of the x-axis labels
        xAxis.setDrawGridLines(false);

        ArrayList<String> labels = new ArrayList<>();
        labels.add("Wisdom");
        labels.add("Endurance");
        labels.add("Intellect");
        labels.add("Strength");
        labels.add("Creativity");

        xAxis.setValueFormatter(new IndexAxisValueFormatter(labels));

        BarDataSet barDataSet = new BarDataSet(values, "Data");
        BarData barData = new BarData(barDataSet);
        barData.setBarWidth(0.8f);

        float groupSpace = 0.1f;
        float barSpace = 0.05f;
        float barWidth = 0.8f - barSpace;

        barData.groupBars(0, groupSpace, barSpace);
        barChart.setData(barData);
        barChart.invalidate();

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