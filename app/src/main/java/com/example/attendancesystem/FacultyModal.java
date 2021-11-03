package com.example.attendancesystem;

public class FacultyModal {
    private String id;
    private String first_name;
    private String last_name;
    private String address;
    private String phone_no;
    private String email;
    private String password;
    private String qualification;

    public String getId(){ return id; }
    public String getFirst_Name(){
        return first_name;
    }
    public String getLast_Name(){
        return last_name;
    }
    public String getEmail(){ return email; }
    public String getAddress(){
        return address;
    }
    public String getPhone_No(){
        return phone_no;
    }
    public String getPassword(){
        return password;
    }
    public String getQualification(){
        return qualification;
    }

    public FacultyModal( String id,String first_name,String last_name,String address,String phone_no,String qualification,String email,String password){
        this.id=id;
        this.first_name=first_name;
        this.last_name=last_name;
        this.email=email;
        this.address=address;
        this.phone_no=phone_no;
        this.password=password;
        this.qualification=qualification;
    }

}
