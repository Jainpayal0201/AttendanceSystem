package com.example.attendancesystem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.radiobutton.MaterialRadioButton;

import java.util.ArrayList;

public class AttendanceAdapter extends RecyclerView.Adapter{
    Context context;
    String type;
    ArrayList<AttendanceModal> arrayList;

    public AttendanceAdapter(Context context, ArrayList<AttendanceModal> arrayList,String type){
        this.context=context;
        this.arrayList=arrayList;
        this.type=type;
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
        if(type.equals("attendance")){
            viewHolder.student_info_card.setVisibility(View.GONE);
            viewHolder.student_attendance.setVisibility(View.VISIBLE);
            for(int i = 0; i < viewHolder.radioGroup.getChildCount(); i++){
                ((MaterialRadioButton)viewHolder.radioGroup.getChildAt(i)).setEnabled(false);
            }

            viewHolder.userName.setText(arrayList.get(position).getFirst_Name()+" "+arrayList.get(position).getLast_Name());
            viewHolder.rollNo.setText(String.valueOf(position+1));
            if(arrayList.get(position).getStatus().equals("P")){
                viewHolder.present.setChecked(true);
            }
            else if(arrayList.get(position).getStatus().equals("A")){
                viewHolder.absent.setChecked(true);
            }
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
        if(type.equals("attendance")){
            userName=itemView.findViewById(R.id.student_name);
            rollNo=itemView.findViewById(R.id.student_rollno);
            radioGroup = itemView.findViewById(R.id.radioGroup);
            present=itemView.findViewById(R.id.present);
            absent=itemView.findViewById(R.id.absent);
        }

    }
}
}
