package com.example.attendancesystem;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

public class LoginPage extends Fragment {
View view;
TextInputEditText usernm;
TextInputEditText passwd;
MaterialButton loginbtn;
String username,password;
ArrayList<FacultyModal> arrayList;
String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
DBHandler dbHandler;

    // creating constant keys for shared preferences.
    public static final String SHARED_PREFS = "shared_prefs";

    // key for storing email.
    public static final String EMAIL_KEY = "email_key";

    // key for storing type.
    public static final String TYPE_KEY = "type_key";

    // key for storing password.
    public static final String PASSWORD_KEY = "password_key";

    // variable for shared preferences.
    SharedPreferences sharedpreferences;
    String userEmail, userPassword,userType;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_login_page, container, false);
        dbHandler=new DBHandler(getActivity());
        sharedpreferences = getActivity().getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);

        usernm=view.findViewById(R.id.usernm);
        passwd=view.findViewById(R.id.passwd);
        username=usernm.getText().toString().trim();
        password=passwd.getText().toString().trim();
        loginbtn=view.findViewById(R.id.loginbtn);

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(usernm.getText().toString().trim().equals("")){
                    usernm.setError("Please Enter UserName");
                }
                else if(!(usernm.getText().toString().trim().matches(emailPattern))){
                    usernm.setError("Please Enter Correct UserName");
                }
                else if(passwd.getText().toString().trim().equals("")){
                    passwd.setError("Please Enter Password");
                }
                else if(usernm.getText().toString().trim().equals("admin@gmail.com") && passwd.getText().toString().trim().equals("admin123")){
                    Toast.makeText(getActivity(),"Login Successfully",Toast.LENGTH_SHORT).show();
                    SharedPreferences.Editor editor = sharedpreferences.edit();

                    // below two lines will put values for
                    // email and password in shared preferences.
                    editor.putString(EMAIL_KEY, usernm.getText().toString());
                    editor.putString(PASSWORD_KEY, passwd.getText().toString());
                    editor.putString(TYPE_KEY,"admin");

                    // to save our data with key and value.
                    editor.apply();
                    Navigation.findNavController(view).navigate(R.id.adminDashboard);
                }
                else if(dbHandler.validateFaculty(username,password)!=null){
                    //arrayList = dbHandler.validateFaculty(username,password);
                    //if(arrayList!=null){
                    Toast.makeText(getActivity(),"Login Successfully",Toast.LENGTH_SHORT).show();
                        SharedPreferences.Editor editor = sharedpreferences.edit();
                        // below two lines will put values for
                        // email and password in shared preferences.
                        editor.putString(EMAIL_KEY, usernm.getText().toString());
                        editor.putString(PASSWORD_KEY, passwd.getText().toString());
                        editor.putString(TYPE_KEY,"faculty");
                        // to save our data with key and value.
                        editor.apply();
                        Navigation.findNavController(view).navigate(R.id.facultyDashboard);
                   // }else{
//                        Toast.makeText(getActivity(),"Login Failed",Toast.LENGTH_SHORT).show();
//                    }
                }
                else {
                    Toast.makeText(getActivity(),"Login Failed",Toast.LENGTH_SHORT).show();
                }
            }



        });
        return view;
    }
}