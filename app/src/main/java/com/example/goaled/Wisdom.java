package com.example.goaled;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;

public class Wisdom extends Fragment {
    UserLocal user;
    int days;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_wisdom, container, false);

        user = (UserLocal) getArguments().getSerializable("USER");
        days = getArguments().getInt("days");

        LineChart lineChart = (LineChart) view.findViewById(R.id.line_chart_wisdom);

        ArrayList<Entry> values = new ArrayList<>();
        ArrayList<Double> statValue = user.getStatWithinDays("Wisdom", days);

        for (int i = 0; i < days; i++){
            values.add(new Entry(i, statValue.get(i).floatValue()));
        }

        LineDataSet lineDataSet = new LineDataSet(values, "Wisdom");
        LineData data = new LineData(lineDataSet);
        lineChart.setData(data);

        lineChart.setGridBackgroundColor(Color.WHITE);

        lineDataSet.setDrawCircles(false);

        lineDataSet.setValueTextSize(0);

        XAxis xAxis = lineChart.getXAxis();

        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);

        YAxis yaxis = lineChart.getAxisRight();

        yaxis.setTextColor(00000000);

        lineDataSet.setLineWidth(15);

        Description description = new Description();

        description.setText("");

        description.setTextSize(0);

        Legend legend = lineChart.getLegend();

        legend.setTextSize(20);

        lineChart.setDescription(description);

        lineChart.invalidate();

        return view;
    }
    public static Wisdom newInstance(UserLocal userLocal, int days) {
        Wisdom fragment = new Wisdom();
        Bundle bundle = new Bundle();
        bundle.putInt("days", days);
        bundle.putSerializable("USER", userLocal);
        fragment.setArguments(bundle);

        return fragment;
    }

    public static void refresh(){

    }
}