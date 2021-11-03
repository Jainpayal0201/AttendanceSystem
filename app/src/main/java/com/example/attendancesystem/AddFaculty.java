package com.example.attendancesystem;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class AddFaculty extends Fragment {
    View view;
    DBHandler dbHandler;
    TextInputEditText faculty_first_name,faculty_last_name,faculty_address,faculty_phone_no,faculty_email,faculty_passwd,faculty_qualification;
    MaterialButton add_faculty_btn,faculty_cancel_btn;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    ImageView back_arrow;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_add_faculty, container, false);
        dbHandler=new DBHandler(getActivity());

        faculty_first_name=view.findViewById(R.id.faculty_first_name);
        faculty_last_name=view.findViewById(R.id.faculty_last_name);
        faculty_address=view.findViewById(R.id.faculty_address);
        faculty_phone_no=view.findViewById(R.id.faculty_phone_no);
        faculty_email=view.findViewById(R.id.faculty_email);
        faculty_passwd=view.findViewById(R.id.faculty_passwd);
        faculty_qualification=view.findViewById(R.id.faculty_qualification);

        //Click listener on back arrow
        back_arrow=view.findViewById(R.id.back_arrow);
        back_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.adminDashboard);
            }
        });

        //Cancel Button
        faculty_cancel_btn=view.findViewById(R.id.faculty_cancel_btn);
        faculty_cancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.adminDashboard);
            }
        });

        add_faculty_btn=view.findViewById(R.id.add_faculty_btn);
        add_faculty_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String first_name=faculty_first_name.getText().toString().trim();
                String last_name=faculty_last_name.getText().toString().trim();
                String address=faculty_address.getText().toString().trim();
                String phone_no=faculty_phone_no.getText().toString().trim();
                String email=faculty_email.getText().toString().trim();
                String password=faculty_passwd.getText().toString().trim();
                String qualification=faculty_qualification.getText().toString().trim();

                if(first_name.isEmpty()){
                    faculty_first_name.setError("Please Enter FirstName");
                }
                else if(!(first_name.matches("^[a-zA-Z]*$"))){
                    faculty_first_name.setError("Invalid FirstName");
                }
                else if(last_name.isEmpty()){
                    faculty_last_name.setError("Please Enter Last Name");
                }
                else if(!(last_name.matches("^[a-zA-Z]*$"))){
                    faculty_last_name.setError("Invalid LastName");
                }
                else if(address.isEmpty()){
                    faculty_address.setError("Please Enter Address");
                }
                else if(phone_no.isEmpty()){
                    faculty_phone_no.setError("Please Enter Phone Number");
                }
                else if(phone_no.length()!=10){
                    faculty_phone_no.setError("Enter 10 digits number");
                }
                else if(email.isEmpty()){
                    faculty_email.setError("Please Enter Email");
                }else if(!(email.matches(emailPattern))){
                    faculty_email.setError("Please Enter Correct Email");
                }
                else if(password.isEmpty()){
                    faculty_passwd.setError("Please Enter Password");
                }
                else if(password.length()<8){
                    faculty_passwd.setError("Password should be atleast 8 digit long");
                }
                else if(qualification.isEmpty()){
                    faculty_qualification.setError("Please Enter Qualification");
                }else{
                    dbHandler.addFaculty(first_name,last_name,address,phone_no,email,password,qualification);
                    Toast.makeText(getActivity(), "Faculty Added Successfully", Toast.LENGTH_SHORT).show();
                    faculty_first_name.setText("");
                    faculty_last_name.setText("");
                    faculty_address.setText("");
                    faculty_phone_no.setText("");
                    faculty_email.setText("");
                    faculty_passwd.setText("");
                    faculty_qualification.setText("");
                }

            }
        });
        return view;
    }
}