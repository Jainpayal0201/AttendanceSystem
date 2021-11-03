package com.example.attendancesystem;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;


public class StudentViewDetails extends Fragment {


    TextView userName,deptName,rollNo,email,address,year,phoneNo,dateOfBirth;
    String firstName,lastName,deptNameVal,rollNoVal,emailVal,addressVal,yearVal,phoneNoVal,dateOfBirthVal,idVal;
    MaterialButton update,delete;
    ImageView back_arrow;
    ArrayList<StudentModal> arrayList;
    DBHandler dbHandler;
    View view;

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
        if(view==null) {


            // Inflate the layout for this fragment
            view = inflater.inflate(R.layout.fragment_student_view_details, container, false);
            dbHandler = new DBHandler(getActivity());
            userName = view.findViewById(R.id.userName);
            deptName = view.findViewById(R.id.deptName);
            dateOfBirth = view.findViewById(R.id.dateOfBirth);
            rollNo = view.findViewById(R.id.rollNo);
            email = view.findViewById(R.id.email);
            address = view.findViewById(R.id.address);
            year = view.findViewById(R.id.year);
            phoneNo = view.findViewById(R.id.phoneNo);
            update = view.findViewById(R.id.update);
            idVal = getArguments().getString("id");
            firstName = getArguments().getString("firstName");
            lastName = getArguments().getString("lastName");
            deptNameVal = getArguments().getString("department");
            rollNoVal = getArguments().getString("rollNo");
            dateOfBirthVal = getArguments().getString("dateOfBirth");
            emailVal = getArguments().getString("email");
            addressVal = getArguments().getString("address");
            yearVal = getArguments().getString("year");
            phoneNoVal = getArguments().getString("phoneNo");
            userName.setText(firstName + " " + lastName);
            deptName.setText(deptNameVal);
            rollNo.setText(rollNoVal);
            dateOfBirth.setText(dateOfBirthVal);
            email.setText(emailVal);
            address.setText(addressVal);
            year.setText(yearVal);
            phoneNo.setText(phoneNoVal);

            // getting the data which is stored in shared preferences.
            sharedpreferences = getActivity().getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
            userType = sharedpreferences.getString(TYPE_KEY,null);
            userEmail = sharedpreferences.getString(EMAIL_KEY, null);
            userPassword = sharedpreferences.getString(PASSWORD_KEY, null);
            if(userType.equals("faculty")){
                update.setVisibility(View.GONE);
                delete.setVisibility(View.GONE);
            }
            update.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bundle bundle = new Bundle();
                    bundle.putString("id", idVal);
                    bundle.putString("firstName", firstName);
                    bundle.putString("lastName", lastName);
                    bundle.putString("dateOfBirth", dateOfBirthVal);
                    bundle.putString("department", deptNameVal);
                    bundle.putString("address", addressVal);
                    bundle.putString("email", emailVal);
                    bundle.putString("phoneNo", phoneNoVal);
                    bundle.putString("year", yearVal);
                    Navigation.findNavController(view).navigate(R.id.updateDetails, bundle);
                }
            });
            delete = view.findViewById(R.id.delete);
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dbHandler.deleteStudent(idVal, deptNameVal);
                    getActivity().onBackPressed();
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
        }else{
            arrayList = dbHandler.studentId(idVal,deptNameVal);
            for(int i=0;i<arrayList.size();i++){
              if(arrayList.get(i).getId().equals(idVal))
              {
                  userName.setText(arrayList.get(i).getFirst_Name() + " " + arrayList.get(i).getLast_Name());
                  deptName.setText(arrayList.get(i).getDepartment());
                  rollNo.setText(rollNoVal);
                  dateOfBirth.setText(arrayList.get(i).getDate_of_Birth());
                  email.setText(arrayList.get(i).getEmail());
                  address.setText(arrayList.get(i).getAddress());
                  year.setText(arrayList.get(i).getS_Year());
                  phoneNo.setText(arrayList.get(i).getPhone_No());

                  firstName = arrayList.get(i).getFirst_Name();
                  lastName = arrayList.get(i).getLast_Name();
                  deptNameVal = arrayList.get(i).getDepartment();
                  dateOfBirthVal = arrayList.get(i).getDate_of_Birth();
                  emailVal = arrayList.get(i).getEmail();
                  addressVal = arrayList.get(i).getAddress();
                  yearVal = arrayList.get(i).getS_Year();
                  phoneNoVal = arrayList.get(i).getPhone_No();
              }
            }


        }
        return view;
    }
}