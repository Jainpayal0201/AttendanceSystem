package com.example.attendancesystem;

public class StudentModal {
    private String id;
    private String first_name;
    private String last_name;
    private String email;
    private String address;
    private String date_of_birth;
    private String phone_no;
    private String department;
    private String s_year;

    public String getId(){
        return id;
    }
    public String getFirst_Name(){
        return first_name;
    }
    public String getLast_Name(){
        return last_name;
    }
    public String getEmail(){
        return email;
    }
    public String getAddress(){
        return address;
    }
    public String getDate_of_Birth(){
        return date_of_birth;
    }
    public String getPhone_No(){
        return phone_no;
    }
    public String getDepartment(){
        return department;
    }
    public String getS_Year(){
        return s_year;
    }

    public StudentModal( String id,String first_name,String last_name,String email,String address,String date_of_birth,String s_year,String department,String phone_no){
        this.id=id;
        this.first_name=first_name;
        this.last_name=last_name;
        this.email=email;
        this.address=address;
        this.date_of_birth=date_of_birth;
        this.s_year=s_year;
        this.department=department;
        this.phone_no=phone_no;
    }

}
