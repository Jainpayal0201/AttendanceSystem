package com.example.attendancesystem;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;


public class ViewFaculty extends Fragment {
    View view;
    ImageView back_arrow;
    DBHandler dbHandler;
    RecyclerView recyclerView;
    FacultyViewAdapter adapter;
    ArrayList<FacultyModal> arrayList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_view_faculty, container, false);
        dbHandler = new DBHandler(getActivity());
        //Click listerner on back arrow
        back_arrow = view.findViewById(R.id.back_arrow);
        back_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });

        recyclerView = view.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        arrayList = dbHandler.readFaculty();
        adapter = new FacultyViewAdapter(getActivity(), arrayList);
        recyclerView.setAdapter(adapter);

    return view;
    }
}