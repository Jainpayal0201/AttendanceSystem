package com.example.attendancesystem;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FacultyViewAdapter extends RecyclerView.Adapter {
    Context context;
    ArrayList<FacultyModal> arrayList;

    public FacultyViewAdapter(Context context, ArrayList<FacultyModal> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context
                = parent.getContext();
        LayoutInflater inflater
                = LayoutInflater.from(context);

        // Inflate the layout

        View view
                = inflater
                .inflate(R.layout.faculty_info_card,
                        parent, false);
        return new AttendanceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        FacultyViewAdapter.AttendanceViewHolder viewHolder=(FacultyViewAdapter.AttendanceViewHolder) holder;
        viewHolder.userName.setText(arrayList.get(position).getFirst_Name()+" "+arrayList.get(position).getLast_Name());
        viewHolder.emailAddress.setText(arrayList.get(position).getAddress());
        viewHolder.phoneNo.setText(arrayList.get(position).getPhone_No());
        viewHolder.viewDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle=new Bundle();
                bundle.putString("firstName",arrayList.get(position).getFirst_Name());
                bundle.putString("lastName",arrayList.get(position).getLast_Name());
                bundle.putString("id",arrayList.get(position).getId());
                bundle.putString("address",arrayList.get(position).getAddress());
                bundle.putString("email",arrayList.get(position).getEmail());
                bundle.putString("phoneNo",arrayList.get(position).getPhone_No());
                bundle.putString("password",arrayList.get(position).getPassword());
                bundle.putString("qualification",arrayList.get(position).getQualification());
                Navigation.findNavController(view).navigate(R.id.facultyViewDetails,bundle);
                Log.d("On","click");
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class AttendanceViewHolder extends RecyclerView.ViewHolder {
        TextView userName, emailAddress, phoneNo, viewDetails;

        public AttendanceViewHolder(@NonNull View itemView) {
            super(itemView);
            userName = itemView.findViewById(R.id.userName);
            emailAddress = itemView.findViewById(R.id.email);
            phoneNo = itemView.findViewById(R.id.phoneno);
            viewDetails = itemView.findViewById(R.id.viewDetails);
        }
    }
}
