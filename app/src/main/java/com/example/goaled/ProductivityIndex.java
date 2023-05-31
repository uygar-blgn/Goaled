package com.example.goaled;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;

public class ProductivityIndex extends Fragment {

    UserLocal user;
    int days;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_productivity_index, container, false);
        LineChart lineChart = (LineChart) view.findViewById(R.id.last_week);

        user = (UserLocal) getArguments().getSerializable("USER");
        days = getArguments().getInt("days");

        ArrayList<Double> weeklyPi = user.getPIWithinDays(days);
        ArrayList<Entry> values = new ArrayList<>();

        for(int i = 0; i < days; i++){
            values.add(new Entry(i, weeklyPi.get(i).intValue()));
        }

        LineDataSet lineDataSet = new LineDataSet(values, "PI");
        LineData data = new LineData(lineDataSet);
        lineChart.setData(data);

        lineChart.setDrawGridBackground(false);

        YAxis yaxis = lineChart.getAxisRight();

        yaxis.setTextColor(00000000);

        Description description = new Description();

        description.setText("Productivity Index in the last 7 days");

        description.setTextSize(15);

        lineChart.setDescription(description);

        lineChart.invalidate();

        return view;
    }

    public static ProductivityIndex newInstance(UserLocal userLocal, int days) {
        ProductivityIndex fragment = new ProductivityIndex();
        Bundle bundle = new Bundle();
        bundle.putInt("days", days);
        bundle.putSerializable("USER", userLocal);
        fragment.setArguments(bundle);

        return fragment;
    }
}