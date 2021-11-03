package com.example.attendancesystem;

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

public class FacultyViewDetails extends Fragment {

    TextView userName,address,phoneNo,email,password,qualification;
    String firstName,lastName,emailVal,addressVal,phoneNoVal,passwordVal,qualificationVal,idVal;
    MaterialButton update,delete;
    ImageView back_arrow;
    ArrayList<FacultyModal> arrayList;
    DBHandler dbHandler;
    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if(view==null){
            view= inflater.inflate(R.layout.fragment_faculty_view_details, container, false);
            dbHandler = new DBHandler(getActivity());
            userName = view.findViewById(R.id.userName);
            email = view.findViewById(R.id.email);
            address = view.findViewById(R.id.address);
            phoneNo = view.findViewById(R.id.phoneNo);
            password=view.findViewById(R.id.password);
            qualification=view.findViewById(R.id.qualification);
            update = view.findViewById(R.id.update);
            idVal = getArguments().getString("id");
            firstName = getArguments().getString("firstName");
            lastName = getArguments().getString("lastName");
            emailVal = getArguments().getString("email");
            addressVal = getArguments().getString("address");
            phoneNoVal = getArguments().getString("phoneNo");
            passwordVal=getArguments().getString("password");
            qualificationVal=getArguments().getString("qualification");
            userName.setText(firstName + " " + lastName);
            email.setText(emailVal);
            address.setText(addressVal);
            phoneNo.setText(phoneNoVal);
            password.setText(passwordVal);
            qualification.setText(qualificationVal);
            update.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bundle bundle = new Bundle();
                    bundle.putString("id", idVal);
                    bundle.putString("firstName", firstName);
                    bundle.putString("lastName", lastName);
                    bundle.putString("address", addressVal);
                    bundle.putString("email", emailVal);
                    bundle.putString("phoneNo", phoneNoVal);
                    bundle.putString("password", passwordVal);
                    bundle.putString("qualification",qualificationVal);
                    Navigation.findNavController(view).navigate(R.id.updateFacultyDetails, bundle);
                }
            });
            delete = view.findViewById(R.id.delete);
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dbHandler.deleteFaculty(idVal);
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
            arrayList = dbHandler.facultyId(idVal);
            for(int i=0;i<arrayList.size();i++){
                if(arrayList.get(i).getId().equals(idVal))
                {
                    userName.setText(arrayList.get(i).getFirst_Name() + " " + arrayList.get(i).getLast_Name());
                    email.setText(arrayList.get(i).getEmail());
                    address.setText(arrayList.get(i).getAddress());
                    phoneNo.setText(arrayList.get(i).getPhone_No());
                    password.setText(arrayList.get(i).getPassword());
                    qualification.setText(arrayList.get(i).getQualification());

                    firstName = arrayList.get(i).getFirst_Name();
                    lastName = arrayList.get(i).getLast_Name();
                    emailVal = arrayList.get(i).getEmail();
                    addressVal = arrayList.get(i).getAddress();
                    passwordVal = arrayList.get(i).getPassword();
                    phoneNoVal = arrayList.get(i).getPhone_No();
                    qualificationVal=arrayList.get(i).getQualification();
                }
            }
        }

        return view;
    }
}