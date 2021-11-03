package com.example.attendancesystem;

public class AttendaceArrayList {
    String studentFirstName,studentLastName,rollNo,attendanceStatus,studentId;

    public AttendaceArrayList(String studentFirstName,String studentLastName, String rollNo, String attendanceStatus, String studentId) {
        this.studentLastName = studentLastName;
        this.studentFirstName=studentFirstName;
        this.rollNo = rollNo;
        this.attendanceStatus = attendanceStatus;
        this.studentId = studentId;
    }



    public String getRollNo() {
        return rollNo;
    }

    public String getStudentFirstName() {
        return studentFirstName;
    }

    public void setStudentFirstName(String studentFirstName) {
        this.studentFirstName = studentFirstName;
    }

    public String getStudentLastName() {
        return studentLastName;
    }

    public void setStudentLastName(String studentLastName) {
        this.studentLastName = studentLastName;
    }

    public void setRollNo(String rollNo) {
        this.rollNo = rollNo;
    }

    public String getAttendanceStatus() {
        return attendanceStatus;
    }

    public void setAttendanceStatus(String attendanceStatus) {
        this.attendanceStatus = attendanceStatus;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }
}
