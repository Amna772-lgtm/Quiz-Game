package com.example.quizgame;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;


public class resultfragmant extends Fragment {
    private RecyclerView recyclerView;
    private ShiftsAdapter adapter;
    private DatabaseHelper databaseHelper;
    private List<List<String>> shiftList;


    public resultfragmant() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.resultfragmant, container, false);

        databaseHelper = new DatabaseHelper(requireContext());
        shiftList = databaseHelper.getAllShifts();
        adapter = new ShiftsAdapter(shiftList);

        recyclerView = rootView.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView.setAdapter(adapter);

        return rootView;
    }
}