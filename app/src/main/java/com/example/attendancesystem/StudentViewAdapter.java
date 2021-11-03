package com.example.attendancesystem;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.radiobutton.MaterialRadioButton;

import java.util.ArrayList;
import java.util.EventListener;

public class StudentViewAdapter extends RecyclerView.Adapter {
    Context context;
    String type;
     ArrayList<StudentModal> arrayList;
     EventListener listener;
   public StudentViewAdapter(Context context, ArrayList<StudentModal> arrayList,String type,EventListener listener){
      this.context=context;
      this.arrayList=arrayList;
      this.type=type;
      this.listener=listener;
    }
    public interface  EventListener{
     void onClick(String studentFirstName,String studentLastName,String studentId,String rollNo,String attendanceStatus,String dept,String year,String subject);
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
                    .inflate(R.layout.student_info_card,
                            parent, false);
            return new AttendanceViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
    AttendanceViewHolder viewHolder=(AttendanceViewHolder) holder;
    if(type.equals("student")){
        viewHolder.deptName.setText(arrayList.get(position).getDepartment());
        viewHolder.userName.setText(arrayList.get(position).getFirst_Name()+" "+arrayList.get(position).getLast_Name());
        viewHolder.rollNo.setText(String.valueOf(position+1));
        viewHolder.viewDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle=new Bundle();
                bundle.putString("firstName",arrayList.get(position).getFirst_Name());
                bundle.putString("lastName",arrayList.get(position).getLast_Name());
                bundle.putString("id",arrayList.get(position).getId());
                bundle.putString("rollNo",String.valueOf(position+1));
                bundle.putString("department",arrayList.get(position).getDepartment());
                bundle.putString("address",arrayList.get(position).getAddress());
                bundle.putString("dateOfBirth",arrayList.get(position).getDate_of_Birth());
                bundle.putString("email",arrayList.get(position).getEmail());
                bundle.putString("phoneNo",arrayList.get(position).getPhone_No());
                bundle.putString("year",arrayList.get(position).getS_Year());
                Navigation.findNavController(view).navigate(R.id.studentViewDetails,bundle);
                Log.d("On","click");
            }
        });
    }
    else if(type.equals("attendance")){
        viewHolder.student_info_card.setVisibility(View.GONE);
        viewHolder.student_attendance.setVisibility(View.VISIBLE);
        viewHolder.userName.setText(arrayList.get(position).getFirst_Name()+" "+arrayList.get(position).getLast_Name());
        viewHolder.rollNo.setText(String.valueOf(position+1));
        viewHolder.present.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selectedRadioButtonId = viewHolder.radioGroup.getCheckedRadioButtonId();
                if (selectedRadioButtonId != -1) {
                    viewHolder.present = viewHolder.radioGroup.findViewById(selectedRadioButtonId);
                    String selectedRbText = viewHolder.present.getText().toString();
                    listener.onClick(arrayList.get(position).getFirst_Name(),arrayList.get(position).getLast_Name(),arrayList.get(position).getId(),String.valueOf(position+1),selectedRbText,arrayList.get(position).getDepartment(),arrayList.get(position).getS_Year(),"");

                }
            }
        });
        viewHolder.absent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selectedRadioButtonId = viewHolder.radioGroup.getCheckedRadioButtonId();
                if (selectedRadioButtonId != -1) {
                    viewHolder.absent = viewHolder.radioGroup.findViewById(selectedRadioButtonId);
                    String selectedRbText = viewHolder.absent.getText().toString();
                    listener.onClick(arrayList.get(position).getFirst_Name(),arrayList.get(position).getLast_Name(),arrayList.get(position).getId(),String.valueOf(position+1),selectedRbText,arrayList.get(position).getDepartment(),arrayList.get(position).getS_Year(),"");

                }
            }
        });
    }
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class AttendanceViewHolder extends RecyclerView.ViewHolder{
        TextView userName,deptName,rollNo,viewDetails;
        LinearLayout student_info_card,student_attendance;
        RadioGroup radioGroup;
        MaterialRadioButton present,absent;

        public AttendanceViewHolder(@NonNull View itemView) {
            super(itemView);
            student_info_card=itemView.findViewById(R.id.student_info_card);
            student_attendance=itemView.findViewById(R.id.student_attendance);

            if(type.equals("student")){
                userName =itemView.findViewById(R.id.userName);
                deptName =itemView.findViewById(R.id.deptName);
                rollNo =itemView.findViewById(R.id.rollNo);
                viewDetails =itemView.findViewById(R.id.viewDetails);
            }
            else if(type.equals("attendance")){
                userName=itemView.findViewById(R.id.student_name);
                rollNo=itemView.findViewById(R.id.student_rollno);
                radioGroup = itemView.findViewById(R.id.radioGroup);
                present=itemView.findViewById(R.id.present);
                absent=itemView.findViewById(R.id.absent);
            }

        }
    }
}
