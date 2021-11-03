package com.example.attendancesystem;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;


public class ViewStudent extends Fragment implements StudentViewAdapter.EventListener {

    View view;
    AutoCompleteTextView year,dept;
    MaterialButton view_student_btn;
    String student_dept="",student_year="",type="student";
    ImageView back_arrow;
    DBHandler dbHandler;
    RecyclerView recyclerView;
    StudentViewAdapter adapter;
    ArrayList<StudentModal> arrayList;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if(view==null) {
            view = inflater.inflate(R.layout.fragment_view_student, container, false);
            dbHandler = new DBHandler(getActivity());

            year = view.findViewById(R.id.year);
            dept = view.findViewById(R.id.dept);
            view_student_btn = view.findViewById(R.id.view_student_btn);

            //Dropdwon for Department
            String[] department = getResources().getStringArray(R.array.department);
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter(getActivity(), R.layout.dropdown_item, department);
            dept.setAdapter(arrayAdapter);
            dept.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    student_dept = adapterView.getItemAtPosition(i).toString();
                }
            });

            //Click listerner on back arrow
            back_arrow = view.findViewById(R.id.back_arrow);
            back_arrow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    getActivity().onBackPressed();
                }
            });

            //Dropdwon for year
            String[] year1 = getResources().getStringArray(R.array.year);
            ArrayAdapter<String> arrayAdapter1 = new ArrayAdapter<String>(getActivity(), R.layout.dropdown_item, year1);
            year.setAdapter(arrayAdapter1);
            year.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    student_year = adapterView.getItemAtPosition(i).toString();
                }
            });
            recyclerView = view.findViewById(R.id.recyclerview);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

            //Click Listener on view student button
            view_student_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (student_dept.equals("")) {
                        dept.setError("Please Select Department");
                    } else if (student_year.equals("")) {
                        year.setError("Please Select Year");
                    } else {
                        arrayList = dbHandler.readStudent(student_dept, student_year);
                        adapter = new StudentViewAdapter(getActivity(), arrayList,type,ViewStudent.this);
                        recyclerView.setAdapter(adapter);
                    }
                }
            });
        }
        else {
            arrayList = dbHandler.readStudent(student_dept, student_year);
            adapter = new StudentViewAdapter(getActivity(), arrayList,type,ViewStudent.this);
            recyclerView.setAdapter(adapter);
        }


        return view;
    }

    @Override
    public void onClick(String studentFirstName, String studentLastName, String studentId, String rollNo, String attendanceStatus, String dept, String year, String subject) {

    }
}