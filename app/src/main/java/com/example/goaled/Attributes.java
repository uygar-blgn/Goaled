package com.example.goaled;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;
import com.github.mikephil.charting.data.RadarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

import java.util.ArrayList;

public class Attributes extends Fragment {

    UserLocal user;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.attributes_chart, container, false);

        user = (UserLocal) getArguments().getSerializable("USER");

        RadarChart radarChart = (RadarChart) view.findViewById(R.id.radarChart);

        ArrayList<RadarEntry> attributes = new ArrayList<>();
        attributes.add(new RadarEntry(user.getUserStats().get("Strength").floatValue()));
        attributes.add(new RadarEntry(user.getUserStats().get("Intellect").floatValue()));
        attributes.add(new RadarEntry(user.getUserStats().get("Endurance").floatValue()));
        attributes.add(new RadarEntry(user.getUserStats().get("Creativity").floatValue()));
        attributes.add(new RadarEntry(user.getUserStats().get("Wisdom").floatValue()));

        RadarDataSet attributesSet = new RadarDataSet(attributes, "Power");
        attributesSet.setColor(Color.RED);
        attributesSet.setLineWidth(2f);
        attributesSet.setValueTextSize(14f);
        attributesSet.setValueTextColor(Color.RED);

        RadarData attributesData = new RadarData();
        attributesData.addDataSet(attributesSet);

        attributesSet.setValueTextSize(0);
        attributesSet.setLineWidth(12);

        String[] labels = {"Strength", "Intellect", "Endurance", "Creativity", "Wisdom"};

        XAxis axis = radarChart.getXAxis();
        axis.setValueFormatter(new IndexAxisValueFormatter(labels));

        radarChart.animate();

        radarChart.setData(attributesData);

        return view;
    }

    public static Attributes newInstance(UserLocal userLocal) {
        Attributes fragment = new Attributes();
        Bundle bundle = new Bundle();
        bundle.putSerializable("USER", userLocal);
        fragment.setArguments(bundle);

        return fragment;
    }
}