package com.example.goaled;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;

public class DailyGoals extends Fragment {

    UserLocal user;
    int days;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_daily_goals, container, false);

        user = (UserLocal) getArguments().getSerializable("USER");
        days = getArguments().getInt("days");

        LineChart lineChart = (LineChart) view.findViewById(R.id.line_chart_daily_goals);

        ArrayList<Double> monthlyPi = user.getDailyGoalsProgressWithinDays(days);
        ArrayList<Entry> values = new ArrayList<>();

        for(int i = 0; i < days; i++){
            values.add(new Entry(i, monthlyPi.get(i).intValue()));
        }

        LineDataSet lineDataSet = new LineDataSet(values, "Daily Goals");
        LineData data = new LineData(lineDataSet);
        lineChart.setData(data);

        YAxis yaxis = lineChart.getAxisRight();

        XAxis xAxis = lineChart.getXAxis();

        lineChart.setGridBackgroundColor(Color.WHITE);

        lineDataSet.setLineWidth(15);

        lineDataSet.setDrawCircles(false);

        lineDataSet.setValueTextSize(0);

        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);

        yaxis.setTextColor(00000000);

        Description description = new Description();

        description.setText("");

        description.setTextSize(0);

        lineChart.setDescription(description);

        lineChart.invalidate();

        return view;
    }

    public static DailyGoals newInstance(UserLocal userLocal, int days) {
        DailyGoals fragment = new DailyGoals();
        Bundle bundle = new Bundle();
        bundle.putInt("days",days);
        bundle.putSerializable("USER", userLocal);
        fragment.setArguments(bundle);

        return fragment;
    }
}