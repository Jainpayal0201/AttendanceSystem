package com.example.attendancesystem;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.card.MaterialCardView;


public class AdminDashboard extends Fragment {
View view;
MaterialCardView add_student;
MaterialCardView add_faculty;
MaterialCardView view_student;
MaterialCardView view_faculty;
MaterialCardView logout;

// creating constant keys for shared preferences.
public static final String SHARED_PREFS = "shared_prefs";

// key for storing email.
public static final String EMAIL_KEY = "email_key";

// key for storing password.
public static final String PASSWORD_KEY = "password_key";

// variable for shared preferences.
SharedPreferences sharedpreferences;
String userEmail;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_admin_dashboard, container, false);
        // initializing our shared preferences.
        sharedpreferences = getActivity().getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);

        // getting data from shared prefs and
        // storing it in our string variable.
        userEmail = sharedpreferences.getString(EMAIL_KEY, null);

        add_student=view.findViewById(R.id.add_student);
        view_student=view.findViewById(R.id.view_student);
        add_student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.addStudent);
            }
        });

        add_faculty=view.findViewById(R.id.add_faculty);
        add_faculty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.addFaculty);
            }
        });
        view_student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.viewStudent);
            }
        });
        view_faculty=view.findViewById(R.id.view_faculty);
        view_faculty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.viewFaculty);
            }
        });
        logout=view.findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // calling method to edit values in shared prefs.
                SharedPreferences.Editor editor = sharedpreferences.edit();
                // below line will clear
                // the data in shared prefs.
                editor.clear();
                // below line will apply empty data to shared prefs.
                editor.apply();
                Navigation.findNavController(view).navigate(R.id.action_adminDashboard_to_loginPage);
            }
        });
        return view;
    }


}