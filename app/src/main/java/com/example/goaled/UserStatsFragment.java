package com.example.goaled;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class UserStatsFragment extends Fragment {
    private Spinner timeDropdown;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_user_stats, container, false);

        timeDropdown = rootView.findViewById(R.id.timeDropdown);

        // Options for different time intervals of the graph.
        String[] times = {"Last week","Last month","Last 120 Days"};

        // ArrayAdapter will convert String[] to dropdown menu items in Spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_dropdown_item, times);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        timeDropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                // Retrieve the selected time
                String selectedTime = (String) parent.getItemAtPosition(position);

                // Apply the selected time to the graph interval.

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });

        // Inflate the layout for this fragment
        return rootView;
    }
}