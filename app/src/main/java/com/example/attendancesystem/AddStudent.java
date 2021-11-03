package com.example.attendancesystem;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.nfc.Tag;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Calendar;

public class AddStudent extends Fragment {
    View view;
    ImageView back_arrow;
    TextInputEditText student_first_name,student_last_name,student_email,student_address,dob,student_phone_no;
    String student_dept,student_year,emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    MaterialButton add_student_btn,cancel_btn;
    DatePickerDialog.OnDateSetListener mOnDateSetListener;
    AutoCompleteTextView autoCompleteTextView,autoCompleteTextView1;
    DBHandler dbHandler;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_add_student, container, false);
        dbHandler=new DBHandler(getActivity());

        student_first_name=view.findViewById(R.id.student_first_name);
        student_last_name=view.findViewById(R.id.student_last_name);
        student_address=view.findViewById(R.id.student_address);
        student_phone_no=view.findViewById(R.id.student_phone_no);
        student_email=view.findViewById(R.id.student_email);


        //Click listerner on back arrow
        back_arrow=view.findViewById(R.id.back_arrow);
        back_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.adminDashboard);
            }
        });

        //Adding calender to select date of birth
        dob=view.findViewById(R.id.dob);
        dob.setOnClickListener(new View.OnClickListener() {
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
                String date= month + "/" + day + "/" + year;
                dob.setText(date);
            }
        };

        //Dropdwon for Department
        String[] department= getResources().getStringArray(R.array.department);
        ArrayAdapter<String> arrayAdapter= new ArrayAdapter(getActivity(),R.layout.dropdown_item,department);
        autoCompleteTextView = view.findViewById(R.id.dept);
        autoCompleteTextView.setAdapter(arrayAdapter);
        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                student_dept = adapterView.getItemAtPosition(i).toString();
            }
        });

        //Dropdwon for year
        String[] year=getResources().getStringArray(R.array.year);
        ArrayAdapter<String> arrayAdapter1=new ArrayAdapter<String>(getActivity(),R.layout.dropdown_item,year);
        autoCompleteTextView1=view.findViewById(R.id.year);
        autoCompleteTextView1.setAdapter(arrayAdapter1);
        autoCompleteTextView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                student_year = adapterView.getItemAtPosition(i).toString();
            }
        });

        //Cancel Button
        cancel_btn=view.findViewById(R.id.cancel_btn);
        cancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.adminDashboard);
            }
        });

        //Add Student button
        add_student_btn=view.findViewById(R.id.add_student_btn);
        add_student_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dob.setError(null);
                autoCompleteTextView.setError(null);
                autoCompleteTextView1.setError(null);
                String first_name=student_first_name.getText().toString().trim();
                String last_name=student_last_name.getText().toString().trim();
                String email=student_email.getText().toString().trim();
                String address=student_address.getText().toString().trim();
                String phone_no=student_phone_no.getText().toString();
                String date_of_birth=dob.getText().toString();
                String department=autoCompleteTextView.getText().toString();
                String s_year=autoCompleteTextView1.getText().toString();
                if(first_name.isEmpty()){
                    student_first_name.setError("Please Enter FirstName");
                }
                else if(!(first_name.matches("^[a-zA-Z]*$"))){
                    student_first_name.setError("Please Enter Correct FirstName");
                }
                else if(last_name.isEmpty()){
                    student_last_name.setError("Please Enter LastName");
                }
                else if(!(last_name.matches("^[a-zA-Z]*$"))){
                    student_last_name.setError("Please Enter Correct LastName");
                }
                else if(email.isEmpty()){
                    student_email.setError("Please Enter Email");
                }
                else if(!(email.matches(emailPattern))){
                    student_email.setError("Please Enter Correct Email");
                }
                else if(address.isEmpty()){
                    student_address.setError("Please Enter Address");
                }
                else if(date_of_birth.isEmpty()){
                    dob.setError("Please Enter DOB");
                }
                else if(phone_no.isEmpty()){
                    student_phone_no.setError("Please Enter Phone Number");
                }
                else if(phone_no.length()!=10){
                    student_phone_no.setError("Please Enter 10 digits");
                }
                else if(department.isEmpty()){
                    autoCompleteTextView.setError("Please Select Department");
                }
                else if(s_year.isEmpty()){
                    autoCompleteTextView1.setError("Please Select Year");
                }
                else{
                    dbHandler.addStudent(first_name,last_name,email,address,date_of_birth,phone_no,department,s_year);
                    Toast.makeText(getActivity(), "Student Added Successfully", Toast.LENGTH_SHORT).show();
                    student_first_name.setText("");
                    student_last_name.setText("");
                    student_address.setText("");
                    student_phone_no.setText("");
                    student_email.setText("");
                    dob.setText("");
                    autoCompleteTextView.setText("");
                    autoCompleteTextView1.setText("");
                }
            }
        });

        return view;
    }
}