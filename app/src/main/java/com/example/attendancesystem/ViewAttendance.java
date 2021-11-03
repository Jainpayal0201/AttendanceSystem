package com.example.attendancesystem;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
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
import android.widget.DatePicker;
import android.widget.ImageView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.Calendar;

public class ViewAttendance extends Fragment implements StudentViewAdapter.EventListener {
View view;
DBHandler dbHandler;
    String student_dept="",student_year="",subject1="",type="attendance",date1="";
    AutoCompleteTextView year,dept,subject;
    DatePickerDialog.OnDateSetListener mOnDateSetListener;
    ArrayList<AttendanceModal> arrayList;
    AttendanceAdapter adapter;
    MaterialButton submit;
    TextInputEditText date;
    RecyclerView recyclerView;
ImageView back_arrow;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_view_attendance, container, false);
        dbHandler = new DBHandler(getActivity());
        //Click listerner on back arrow
        back_arrow = view.findViewById(R.id.back_arrow);
        back_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });

        //Adding calender to select date of birth
        date=view.findViewById(R.id.date);
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal=Calendar.getInstance();
                int year=cal.get(Calendar.YEAR);
                int month=cal.get(Calendar.MONTH);
                int day=cal.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog=new DatePickerDialog(getActivity(),
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mOnDateSetListener,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
        mOnDateSetListener =new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month=month+1;
               date1= day + "/" + month + "/" + year;
                date.setText(date1);
                arrayList = dbHandler.readStudentAttendance(student_dept, student_year,subject1,date1);
                adapter = new AttendanceAdapter(getActivity(), arrayList,type);
                recyclerView.setAdapter(adapter);
            }
        };

        year = view.findViewById(R.id.year);
        dept = view.findViewById(R.id.dept);
        submit = view.findViewById(R.id.submit);

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

        subject=view.findViewById(R.id.subject);
        submit=view.findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (student_dept.equals("")) {
                    dept.setError("Please Select Department");
                } else if (student_year.equals("")) {
                    year.setError("Please Select Year");
                }else{
                    if((student_dept.equals("IT")) && (student_year.equals("FY"))){
                        String[] sub = getResources().getStringArray(R.array.it_fy_subj);
                        ArrayAdapter<String> arrayAdapter2 = new ArrayAdapter<String>(getActivity(), R.layout.dropdown_item, sub);
                        subject.setAdapter(arrayAdapter2);
                        subject.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                subject1 = adapterView.getItemAtPosition(i).toString();
                                if(date1.equals("")){
                                    date.setError("Select Date");
                                }else{
                                    arrayList = dbHandler.readStudentAttendance(student_dept, student_year,subject1,date1);
                                    adapter = new AttendanceAdapter(getActivity(), arrayList,type);
                                    recyclerView.setAdapter(adapter);
                                }
                            }
                        });
                    }else if((student_dept.equals("IT")) && (student_year.equals("SY"))){
                        String[] sub = getResources().getStringArray(R.array.it_sy_subj);
                        ArrayAdapter<String> arrayAdapter2 = new ArrayAdapter<String>(getActivity(), R.layout.dropdown_item, sub);
                        subject.setAdapter(arrayAdapter2);
                        subject.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                subject1 = adapterView.getItemAtPosition(i).toString();
                                if(date1.equals("")){
                                    date.setError("Select Date");
                                }else{
                                    arrayList = dbHandler.readStudentAttendance(student_dept, student_year,subject1,date1);
                                    adapter = new AttendanceAdapter(getActivity(), arrayList,type);
                                    recyclerView.setAdapter(adapter);
                                }
                            }
                        });
                    }
                    else if((student_dept.equals("IT")) && (student_year.equals("TY"))){
                        String[] sub = getResources().getStringArray(R.array.it_ty_subj);
                        ArrayAdapter<String> arrayAdapter2 = new ArrayAdapter<String>(getActivity(), R.layout.dropdown_item, sub);
                        subject.setAdapter(arrayAdapter2);
                        subject.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                subject1 = adapterView.getItemAtPosition(i).toString();
                                if(date1.equals("")){
                                    date.setError("Select Date");
                                }else{
                                    arrayList = dbHandler.readStudentAttendance(student_dept, student_year,subject1,date1);
                                    adapter = new AttendanceAdapter(getActivity(), arrayList,type);
                                    recyclerView.setAdapter(adapter);
                                }
                            }
                        });
                    }
                    else if((student_dept.equals("IT")) && (student_year.equals("BE"))){
                        String[] sub = getResources().getStringArray(R.array.it_ly_subj);
                        ArrayAdapter<String> arrayAdapter2 = new ArrayAdapter<String>(getActivity(), R.layout.dropdown_item, sub);
                        subject.setAdapter(arrayAdapter2);
                        subject.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                subject1 = adapterView.getItemAtPosition(i).toString();
                                if(date1.equals("")){
                                    date.setError("Select Date");
                                }else{
                                    arrayList = dbHandler.readStudentAttendance(student_dept, student_year,subject1,date1);
                                    adapter = new AttendanceAdapter(getActivity(), arrayList,type);
                                    recyclerView.setAdapter(adapter);
                                }
                            }
                        });
                    }else if((student_dept.equals("CO")) && (student_year.equals("FY"))){
                        String[] sub = getResources().getStringArray(R.array.co_fy_subj);
                        ArrayAdapter<String> arrayAdapter2 = new ArrayAdapter<String>(getActivity(), R.layout.dropdown_item, sub);
                        subject.setAdapter(arrayAdapter2);
                        subject.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                subject1 = adapterView.getItemAtPosition(i).toString();
                                if(date1.equals("")){
                                    date.setError("Select Date");
                                }else{
                                    arrayList = dbHandler.readStudentAttendance(student_dept, student_year,subject1,date1);
                                    adapter = new AttendanceAdapter(getActivity(), arrayList,type);
                                    recyclerView.setAdapter(adapter);
                                }
                            }
                        });
                    }
                    else if((student_dept.equals("CO")) && (student_year.equals("SY"))){
                        String[] sub = getResources().getStringArray(R.array.co_sy_subj);
                        ArrayAdapter<String> arrayAdapter2 = new ArrayAdapter<String>(getActivity(), R.layout.dropdown_item, sub);
                        subject.setAdapter(arrayAdapter2);
                        subject.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                subject1 = adapterView.getItemAtPosition(i).toString();
                                if(date1.equals("")){
                                    date.setError("Select Date");
                                }else{
                                    arrayList = dbHandler.readStudentAttendance(student_dept, student_year,subject1,date1);
                                    adapter = new AttendanceAdapter(getActivity(), arrayList,type);
                                    recyclerView.setAdapter(adapter);
                                }
                            }
                        });
                    }
                    else if((student_dept.equals("CO")) && (student_year.equals("TY"))){
                        String[] sub = getResources().getStringArray(R.array.co_ty_subj);
                        ArrayAdapter<String> arrayAdapter2 = new ArrayAdapter<String>(getActivity(), R.layout.dropdown_item, sub);
                        subject.setAdapter(arrayAdapter2);
                        subject.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                subject1 = adapterView.getItemAtPosition(i).toString();
                                if(date1.equals("")){
                                    date.setError("Select Date");
                                }else{
                                    arrayList = dbHandler.readStudentAttendance(student_dept, student_year,subject1,date1);
                                    adapter = new AttendanceAdapter(getActivity(), arrayList,type);
                                    recyclerView.setAdapter(adapter);
                                }
                            }
                        });
                    }
                    else if((student_dept.equals("CO")) && (student_year.equals("BE"))){
                        String[] sub = getResources().getStringArray(R.array.co_ly_subj);
                        ArrayAdapter<String> arrayAdapter2 = new ArrayAdapter<String>(getActivity(), R.layout.dropdown_item, sub);
                        subject.setAdapter(arrayAdapter2);
                        subject.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                subject1 = adapterView.getItemAtPosition(i).toString();
                                if(date1.equals("")){
                                    date.setError("Select Date");
                                }else{
                                    arrayList = dbHandler.readStudentAttendance(student_dept, student_year,subject1,date1);
                                    adapter = new AttendanceAdapter(getActivity(), arrayList,type);
                                    recyclerView.setAdapter(adapter);
                                }
                            }
                        });
                    }
                    else if((student_dept.equals("ME")) && (student_year.equals("FY"))){
                        String[] sub = getResources().getStringArray(R.array.me_fy_subj);
                        ArrayAdapter<String> arrayAdapter2 = new ArrayAdapter<String>(getActivity(), R.layout.dropdown_item, sub);
                        subject.setAdapter(arrayAdapter2);
                        subject.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                subject1 = adapterView.getItemAtPosition(i).toString();
                                if(date1.equals("")){
                                    date.setError("Select Date");
                                }else{
                                    arrayList = dbHandler.readStudentAttendance(student_dept, student_year,subject1,date1);
                                    adapter = new AttendanceAdapter(getActivity(), arrayList,type);
                                    recyclerView.setAdapter(adapter);
                                }
                            }
                        });
                    }
                    else if((student_dept.equals("ME")) && (student_year.equals("SY"))){
                        String[] sub = getResources().getStringArray(R.array.me_sy_subj);
                        ArrayAdapter<String> arrayAdapter2 = new ArrayAdapter<String>(getActivity(), R.layout.dropdown_item, sub);
                        subject.setAdapter(arrayAdapter2);
                        subject.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                subject1 = adapterView.getItemAtPosition(i).toString();
                                if(date1.equals("")){
                                    date.setError("Select Date");
                                }else{
                                    arrayList = dbHandler.readStudentAttendance(student_dept, student_year,subject1,date1);
                                    adapter = new AttendanceAdapter(getActivity(), arrayList,type);
                                    recyclerView.setAdapter(adapter);
                                }
                            }
                        });
                    }
                    else if((student_dept.equals("ME")) && (student_year.equals("TY"))){
                        String[] sub = getResources().getStringArray(R.array.me_ty_subj);
                        ArrayAdapter<String> arrayAdapter2 = new ArrayAdapter<String>(getActivity(), R.layout.dropdown_item, sub);
                        subject.setAdapter(arrayAdapter2);
                        subject.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                subject1 = adapterView.getItemAtPosition(i).toString();
                                if(date1.equals("")){
                                    date.setError("Select Date");
                                }else{
                                    arrayList = dbHandler.readStudentAttendance(student_dept, student_year,subject1,date1);
                                    adapter = new AttendanceAdapter(getActivity(), arrayList,type);
                                    recyclerView.setAdapter(adapter);
                                }
                            }
                        });
                    }
                    else if((student_dept.equals("ME")) && (student_year.equals("BE"))){
                        String[] sub = getResources().getStringArray(R.array.me_ly_subj);
                        ArrayAdapter<String> arrayAdapter2 = new ArrayAdapter<String>(getActivity(), R.layout.dropdown_item, sub);
                        subject.setAdapter(arrayAdapter2);
                        subject.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                subject1 = adapterView.getItemAtPosition(i).toString();
                                if(date1.equals("")){
                                    date.setError("Select Date");
                                }else{
                                    arrayList = dbHandler.readStudentAttendance(student_dept, student_year,subject1,date1);
                                    adapter = new AttendanceAdapter(getActivity(), arrayList,type);
                                    recyclerView.setAdapter(adapter);
                                }
                            }
                        });
                    }
                    else if((student_dept.equals("CE")) && (student_year.equals("FY"))){
                        String[] sub = getResources().getStringArray(R.array.ce_fy_subj);
                        ArrayAdapter<String> arrayAdapter2 = new ArrayAdapter<String>(getActivity(), R.layout.dropdown_item, sub);
                        subject.setAdapter(arrayAdapter2);
                        subject.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                subject1 = adapterView.getItemAtPosition(i).toString();
                                if(date1.equals("")){
                                    date.setError("Select Date");
                                }else{
                                    arrayList = dbHandler.readStudentAttendance(student_dept, student_year,subject1,date1);
                                    adapter = new AttendanceAdapter(getActivity(), arrayList,type);
                                    recyclerView.setAdapter(adapter);
                                }
                            }
                        });
                    }
                    else if((student_dept.equals("CE")) && (student_year.equals("SY"))){
                        String[] sub = getResources().getStringArray(R.array.ce_sy_subj);
                        ArrayAdapter<String> arrayAdapter2 = new ArrayAdapter<String>(getActivity(), R.layout.dropdown_item, sub);
                        subject.setAdapter(arrayAdapter2);
                        subject.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                subject1 = adapterView.getItemAtPosition(i).toString();
                                if(date1.equals("")){
                                    date.setError("Select Date");
                                }else{
                                    arrayList = dbHandler.readStudentAttendance(student_dept, student_year,subject1,date1);
                                    adapter = new AttendanceAdapter(getActivity(), arrayList,type);
                                    recyclerView.setAdapter(adapter);
                                }
                            }
                        });
                    }
                    else if((student_dept.equals("CE")) && (student_year.equals("TY"))){
                        String[] sub = getResources().getStringArray(R.array.ce_ty_subj);
                        ArrayAdapter<String> arrayAdapter2 = new ArrayAdapter<String>(getActivity(), R.layout.dropdown_item, sub);
                        subject.setAdapter(arrayAdapter2);
                        subject.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                subject1 = adapterView.getItemAtPosition(i).toString();
                                if(date1.equals("")){
                                    date.setError("Select Date");
                                }else{
                                    arrayList = dbHandler.readStudentAttendance(student_dept, student_year,subject1,date1);
                                    adapter = new AttendanceAdapter(getActivity(), arrayList,type);
                                    recyclerView.setAdapter(adapter);
                                }
                            }
                        });
                    }
                    else if((student_dept.equals("CE")) && (student_year.equals("BE"))){
                        String[] sub = getResources().getStringArray(R.array.ce_ly_subj);
                        ArrayAdapter<String> arrayAdapter2 = new ArrayAdapter<String>(getActivity(), R.layout.dropdown_item, sub);
                        subject.setAdapter(arrayAdapter2);
                        subject.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                subject1 = adapterView.getItemAtPosition(i).toString();
                                if(date1.equals("")){
                                    date.setError("Select Date");
                                }else{
                                    arrayList = dbHandler.readStudentAttendance(student_dept, student_year,subject1,date1);
                                    adapter = new AttendanceAdapter(getActivity(), arrayList,type);
                                    recyclerView.setAdapter(adapter);
                                }
                            }
                        });
                    }
                }
            }
        });

        return view;
    }

    @Override
    public void onClick(String studentFirstName, String studentLastName, String studentId, String rollNo, String attendanceStatus, String dept, String year, String subject) {

    }
}