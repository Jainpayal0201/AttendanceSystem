package com.example.attendancesystem;

public class AttendanceModal {
    private String id;
    private String first_name;
    private String last_name;
    private String dept;
    private String year;
    private String subject;
    private String date;
    private String status;
    private String rollno;

    public String getId(){
        return id;
    }
    public String getFirst_Name(){
        return first_name;
    }
    public String getLast_Name(){
        return last_name;
    }
    public String getDept(){
        return dept;
    }
    public String getYear(){
        return year;
    }
    public String getSubject(){
        return subject;
    }
    public String getDate(){
        return date;
    }
    public String getStatus(){
        return status;
    }
    public String getRollno(){
        return rollno;
    }

    public AttendanceModal( String id,String first_name,String last_name,String rollno,String dept,String year,String status,String subject,String date){
        this.id=id;
        this.first_name=first_name;
        this.last_name=last_name;
        this.rollno=rollno;
        this.dept=dept;
        this.year=year;
        this.status=status;
        this.subject=subject;
        this.date=date;
    }
}
