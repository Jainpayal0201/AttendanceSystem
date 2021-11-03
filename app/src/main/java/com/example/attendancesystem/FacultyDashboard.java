package com.example.attendancesystem;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavGraph;
import androidx.navigation.NavInflater;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.card.MaterialCardView;

public class FacultyDashboard extends Fragment {
    View view;

    MaterialCardView view_student,logout,add_attendance,view_attendance;
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
        view= inflater.inflate(R.layout.fragment_faculty_dashboard, container, false);

        // initializing our shared preferences.
        sharedpreferences = getActivity().getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        userEmail = sharedpreferences.getString(EMAIL_KEY, null);

        add_attendance = view.findViewById(R.id.add_attendance);
        add_attendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.addAttendance);
            }
        });

        view_student=view.findViewById(R.id.view_student);
        view_student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.viewStudent);
            }
        });

        view_attendance=view.findViewById(R.id.view_attendance);
        view_attendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.viewAttendance);
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
                Navigation.findNavController(view).navigate(R.id.action_facultyDashboard_to_loginPage);
            }
        });
        return view;
    }
}