package com.example.attendancesystem;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;

public class AddAttendance extends Fragment implements StudentViewAdapter.EventListener{
    View view;
    AutoCompleteTextView dept,year,subject;
    MaterialButton submit;
    ImageView back_arrow;
    ArrayList<StudentModal> arrayList;
    DBHandler dbHandler;
    RecyclerView recyclerView;
    MaterialButton add_attendance_btn;
    StudentViewAdapter adapter;
    String student_dept="",student_year="",subject1="",type="attendance";
    ArrayList<AttendaceArrayList> attendaceArrayLists;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_add_attendance, container, false);
        dbHandler=new DBHandler(getActivity());
        attendaceArrayLists=new ArrayList<>();
        dept=view.findViewById(R.id.dept);
        add_attendance_btn=view.findViewById(R.id.add_attendance_btn);
        year=view.findViewById(R.id.year);
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
                                arrayList = dbHandler.readStudent(student_dept, student_year);
                                adapter = new StudentViewAdapter(getActivity(), arrayList,type,AddAttendance.this);
                                recyclerView.setAdapter(adapter);
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
                                arrayList = dbHandler.readStudent(student_dept, student_year);
                                adapter = new StudentViewAdapter(getActivity(), arrayList,type,AddAttendance.this);
                                recyclerView.setAdapter(adapter);
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
                                arrayList = dbHandler.readStudent(student_dept, student_year);
                                adapter = new StudentViewAdapter(getActivity(), arrayList,type,AddAttendance.this);
                                recyclerView.setAdapter(adapter);
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
                                arrayList = dbHandler.readStudent(student_dept, student_year);
                                adapter = new StudentViewAdapter(getActivity(), arrayList,type,AddAttendance.this);
                                recyclerView.setAdapter(adapter);
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
                                arrayList = dbHandler.readStudent(student_dept, student_year);
                                adapter = new StudentViewAdapter(getActivity(), arrayList,type,AddAttendance.this);
                                recyclerView.setAdapter(adapter);
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
                                arrayList = dbHandler.readStudent(student_dept, student_year);
                                adapter = new StudentViewAdapter(getActivity(), arrayList,type,AddAttendance.this);
                                recyclerView.setAdapter(adapter);
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
                                arrayList = dbHandler.readStudent(student_dept, student_year);
                                adapter = new StudentViewAdapter(getActivity(), arrayList,type,AddAttendance.this);
                                recyclerView.setAdapter(adapter);
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
                                arrayList = dbHandler.readStudent(student_dept, student_year);
                                adapter = new StudentViewAdapter(getActivity(), arrayList,type,AddAttendance.this);
                                recyclerView.setAdapter(adapter);
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
                                arrayList = dbHandler.readStudent(student_dept, student_year);
                                adapter = new StudentViewAdapter(getActivity(), arrayList,type,AddAttendance.this);
                                recyclerView.setAdapter(adapter);
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
                                arrayList = dbHandler.readStudent(student_dept, student_year);
                                adapter = new StudentViewAdapter(getActivity(), arrayList,type,AddAttendance.this);
                                recyclerView.setAdapter(adapter);
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
                                arrayList = dbHandler.readStudent(student_dept, student_year);
                                adapter = new StudentViewAdapter(getActivity(), arrayList,type,AddAttendance.this);
                                recyclerView.setAdapter(adapter);
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
                                arrayList = dbHandler.readStudent(student_dept, student_year);
                                adapter = new StudentViewAdapter(getActivity(), arrayList,type,AddAttendance.this);
                                recyclerView.setAdapter(adapter);
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
                                arrayList = dbHandler.readStudent(student_dept, student_year);
                                adapter = new StudentViewAdapter(getActivity(), arrayList,type,AddAttendance.this);
                                recyclerView.setAdapter(adapter);
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
                                arrayList = dbHandler.readStudent(student_dept, student_year);
                                adapter = new StudentViewAdapter(getActivity(), arrayList,type,AddAttendance.this);
                                recyclerView.setAdapter(adapter);
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
                                arrayList = dbHandler.readStudent(student_dept, student_year);
                                adapter = new StudentViewAdapter(getActivity(), arrayList,type,AddAttendance.this);
                                recyclerView.setAdapter(adapter);
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
                                arrayList = dbHandler.readStudent(student_dept, student_year);
                                adapter = new StudentViewAdapter(getActivity(), arrayList,type,AddAttendance.this);
                                recyclerView.setAdapter(adapter);
                            }
                        });
                    }
                }
            }
        });
        add_attendance_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             if(attendaceArrayLists.size()>0){
               for(int i=0;i<attendaceArrayLists.size();i++){
                   dbHandler.addAttendance(attendaceArrayLists.get(i).getStudentFirstName(),attendaceArrayLists.get(i).getStudentLastName(),attendaceArrayLists.get(i).getStudentId(),attendaceArrayLists.get(i).getRollNo(),attendaceArrayLists.get(i).getAttendanceStatus(),student_dept,student_year,subject1);
                   Toast.makeText(getActivity(),"Attendance Added Successfully",Toast.LENGTH_SHORT).show();
                   Navigation.findNavController(view).navigate(R.id.facultyDashboard);
               }

             }
            }
        });

        return view;
    }

    @Override
    public void onClick(String studentFirstName, String studentLastName, String studentId, String rollNo, String attendanceStatus, String dept, String year, String subject) {
//        if(attendaceArrayLists.size()>0){
////            for (int i=0;i<attendaceArrayLists.size();i++){
////                if(attendaceArrayLists.get(i).getStudentId().equals(studentId))
////                {
////                    attendaceArrayLists.get(i).setAttendanceStatus(attendanceStatus);
////                }
////                else {
////                    attendaceArrayLists.add(new AttendaceArrayList(studentFirstName,studentLastName,rollNo,attendanceStatus,studentId));
////                }
////            }
//
//
//
//        }
//        else {
            attendaceArrayLists.add(new AttendaceArrayList(studentFirstName,studentLastName,rollNo,attendanceStatus,studentId));
//        }
    }
}