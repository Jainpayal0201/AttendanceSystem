package com.example.attendancesystem;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class DBHandler extends SQLiteOpenHelper {

    private static final String DB_NAME = "AttendanceSystem";
    private static final int DB_VERSION = 5;

    private static final String IT_TABLE_NAME = "IT_student";
    private static final String CO_TABLE_NAME = "CO_student";
    private static final String ME_TABLE_NAME = "ME_student";
    private static final String CE_TABLE_NAME = "CE_student";
    //Student columnns
    private static final String ID_COL = "id";
    private static final String STUDENT_FIRST_NAME = "first_name";
    private static final String STUDENT_LAST_NAME = "last_name";
    private static final String STUDENT_EMAIL="email";
    private static final String STUDENT_ADDRESS = "address";
    private static final String STUDENT_DOB = "dob";
    private static final String YEAR = "year";
    private static final String DEPARTMENT = "department";
    private static final String STUDENT_PHONENO = "phone_no";

    //Faculty Columns
    private static final String FACULTY_TABLE_NAME = "faculty";
    private static final String FACULTY_ID = "faculty_id";
    private static final String FACULTY_FIRST_NAME = "first_name";
    private static final String FACULTY_LAST_NAME = "last_name";
    private static final String FACULTY_ADDRESS = "address";
    private static final String FACULTY_PHONENO = "phone_no";
    private static final String FACULTY_EMAIL = "email";
    private static final String PASSWORD = "password";
    private static final String FACULTY_QUALIFICATION = "qualification";

    //Attendance table
    private static final String ATTENDANCE_TABLE_NMAE="attendance";
    private static final String STUDENT_ID="student_id";
    private static final String ROLLNO="rollno";
    private static final String FIRST_NAME="first_name";
    private static final String LAST_NAME="last_name";
    private static final String DEPT="dept";
    private static final String YEARS="year";
    private static final String STATUS="status";
    private static final String SUBJECT="subject";
    private static final String DATE="date";


    // creating a constructor for our database handler.
    public DBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    // below method is for creating a database by running a sqlite query
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create IT student table
        String queryITStudent = "CREATE TABLE " + IT_TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + STUDENT_FIRST_NAME + " TEXT,"
                + STUDENT_LAST_NAME + " TEXT,"
                + STUDENT_EMAIL + " TEXT,"
                + STUDENT_ADDRESS + " TEXT,"
                + STUDENT_DOB + " TEXT,"
                + YEAR + " TEXT,"
                + DEPARTMENT + " TEXT,"
                + STUDENT_PHONENO + " TEXT)";
        db.execSQL(queryITStudent);
        Log.d("queryITStudent",queryITStudent);

        // Create Attendance Tbale
        String queryAttendance = "CREATE TABLE " + ATTENDANCE_TABLE_NMAE + " ("
                + STUDENT_ID + " INTEGER, "
                + FIRST_NAME + " TEXT,"
                + LAST_NAME + " TEXT,"
                + ROLLNO + " TEXT,"
                + DEPT + " TEXT,"
                + YEARS + " TEXT,"
                + STATUS + " TEXT,"
                + SUBJECT + " TEXT,"
                + DATE + " TEXT)";
        db.execSQL(queryAttendance);
        Log.d("queryAttendance",queryAttendance);

        // Create CO student table
        String queryCOStudent = "CREATE TABLE " + CO_TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + STUDENT_FIRST_NAME + " TEXT,"
                + STUDENT_LAST_NAME + " TEXT,"
                + STUDENT_EMAIL + " TEXT,"
                + STUDENT_ADDRESS + " TEXT,"
                + STUDENT_DOB + " TEXT,"
                + YEAR + " TEXT,"
                + DEPARTMENT + " TEXT,"
                + STUDENT_PHONENO + " TEXT)";
        db.execSQL(queryCOStudent);
        Log.d("queryCOStudent",queryCOStudent);

        // Create ME student table
        String queryMEStudent = "CREATE TABLE " + ME_TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + STUDENT_FIRST_NAME + " TEXT,"
                + STUDENT_LAST_NAME + " TEXT,"
                + STUDENT_EMAIL + " TEXT,"
                + STUDENT_ADDRESS + " TEXT,"
                + STUDENT_DOB + " TEXT,"
                + YEAR + " TEXT,"
                + DEPARTMENT + " TEXT,"
                + STUDENT_PHONENO + " TEXT)";
        db.execSQL(queryMEStudent);
        Log.d("queryMEStudent",queryMEStudent);

        // Create CE student table
        String queryCEStudent = "CREATE TABLE " + CE_TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + STUDENT_FIRST_NAME + " TEXT,"
                + STUDENT_LAST_NAME + " TEXT,"
                + STUDENT_EMAIL + " TEXT,"
                + STUDENT_ADDRESS + " TEXT,"
                + STUDENT_DOB + " TEXT,"
                + YEAR + " TEXT,"
                + DEPARTMENT + " TEXT,"
                + STUDENT_PHONENO + " TEXT)";
        db.execSQL(queryCEStudent);
        Log.d("queryCEStudent",queryCEStudent);

        //Create faculty table
        String queryFaculty = "CREATE TABLE " + FACULTY_TABLE_NAME + "("
                + FACULTY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + FACULTY_FIRST_NAME + " TEXT,"
                + FACULTY_LAST_NAME + " TEXT,"
                + FACULTY_ADDRESS + " TEXT,"
                + FACULTY_PHONENO + " TEXT,"
                + FACULTY_QUALIFICATION + " TEXT,"
                + FACULTY_EMAIL + " TEXT,"
                + PASSWORD +" TEXT)";
        db.execSQL(queryFaculty);

    }


    //Add attendance
    public void addAttendance(String studentFirstName, String studentLastName, String studentId, String rollNo, String attendanceStatus, String dept, String year, String subject){
        SQLiteDatabase db=this.getWritableDatabase();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String currentDateandTime = sdf.format(new Date());
        ContentValues values = new ContentValues();
        values.put(FIRST_NAME,studentFirstName);
        values.put(LAST_NAME,studentLastName);
        values.put(STUDENT_ID,studentId);
        values.put(ROLLNO,rollNo);
        values.put(STATUS,attendanceStatus);
        values.put(DEPT,dept);
        values.put(YEARS,year);
        values.put(SUBJECT,subject);
        values.put(DATE,currentDateandTime);
        db.insert(ATTENDANCE_TABLE_NMAE,null,values);
    db.close();

    }

    public ArrayList<AttendanceModal> readStudentAttendance(String student_dept,String student_year,String subject1,String date){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=null;
        cursor = db.rawQuery("SELECT * FROM " + ATTENDANCE_TABLE_NMAE +" WHERE dept='" + student_dept + "' and year='" +student_year+ "' and subject='"+subject1+"' and date='"+date+"'", null);
        ArrayList<AttendanceModal> studentModalArrayList = new ArrayList<>();
        // moving our cursor to first position.
        if(cursor!=null) {
            if (cursor.moveToFirst()) {
                do {
                    // on below line we are adding the data from cursor to our array list.
                    studentModalArrayList.add(new AttendanceModal(
                            cursor.getString(0),
                            cursor.getString(1),
                            cursor.getString(2),
                            cursor.getString(3),
                            cursor.getString(4),
                            cursor.getString(5),
                            cursor.getString(6),
                            cursor.getString(7),
                            cursor.getString(8) ));
                } while (cursor.moveToNext());
                // moving our cursor to next.
            }
        }
        cursor.close();
        return studentModalArrayList;
    }
    // Add Student Info in Table
    public void addStudent(String first_name,String last_name,String email,String address,String date_of_birth,String phone_no,String department,String s_year){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(STUDENT_FIRST_NAME,first_name);
        values.put(STUDENT_LAST_NAME,last_name);
        values.put(STUDENT_EMAIL,email);
        values.put(STUDENT_ADDRESS,address);
        values.put(STUDENT_DOB,date_of_birth);
        values.put(STUDENT_PHONENO,phone_no);
        values.put(DEPARTMENT,department);
        values.put(YEAR,s_year);
        if(department.equals("IT")){
            db.insert(IT_TABLE_NAME,null,values);
        }
        else if(department.equals("CO")){
            db.insert(CO_TABLE_NAME,null,values);
        }else if(department.equals("ME")){
            db.insert(ME_TABLE_NAME,null,values);
        }
        else if(department.equals("CE")){
            db.insert(CE_TABLE_NAME,null,values);
        }
        db.close();
    }
    //Add Faculty Info in table
    public void addFaculty(String first_name,String last_name,String address,String phone_no,String email,String password,String qualification){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(FACULTY_FIRST_NAME,first_name);
        values.put(FACULTY_LAST_NAME,last_name);
        values.put(FACULTY_ADDRESS,address);
        values.put(FACULTY_PHONENO,phone_no);
        values.put(FACULTY_EMAIL,email);
        values.put(PASSWORD,password);
        values.put(FACULTY_QUALIFICATION,qualification);
        db.insert(FACULTY_TABLE_NAME,null,values);
        db.close();
    }
    //updateStudentDetails in table
    public void updateStudentDetails(String idVal,String first_name,String last_name,String email,String address,String date_of_birth,String phone_no,String department,String s_year){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(STUDENT_FIRST_NAME,first_name);
        values.put(STUDENT_LAST_NAME,last_name);
        values.put(STUDENT_EMAIL,email);
        values.put(STUDENT_ADDRESS,address);
        values.put(STUDENT_DOB,date_of_birth);
        values.put(STUDENT_PHONENO,phone_no);
        values.put(DEPARTMENT,department);
        values.put(YEAR,s_year);
        if(department.equals("IT")){
            db.update(IT_TABLE_NAME,values,"id=?",new String[]{idVal});
        }
        else if(department.equals("CO")){
            db.update(CO_TABLE_NAME,values,"id=?",new String[]{idVal});
        }else if(department.equals("ME")){
            db.update(ME_TABLE_NAME,values,"id=?",new String[]{idVal});
        }
        else if(department.equals("CE")){
            db.update(CE_TABLE_NAME,values,"id=?",new String[]{idVal});
        }
        db.close();
    }
    //UpdateFacultyDetails in table
    public void updateFacultyDetails(String idVal,String first_name,String last_name,String address,String phone_no,String email,String password,String qualification){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(FACULTY_FIRST_NAME,first_name);
        values.put(FACULTY_LAST_NAME,last_name);
        values.put(FACULTY_ADDRESS,address);
        values.put(FACULTY_PHONENO,phone_no);
        values.put(FACULTY_EMAIL,email);
        values.put(PASSWORD,password);
        values.put(FACULTY_QUALIFICATION,qualification);
        db.update(FACULTY_TABLE_NAME,values,"faculty_id=?",new String[]{idVal});
    }
    //Delete Student from table
    public void deleteStudent(String idVal,String department){
        SQLiteDatabase db=this.getWritableDatabase();
        if(department.equals("IT")){
            db.delete(IT_TABLE_NAME,"id=?",new String[]{idVal});
        }
        else if(department.equals("CO")){
            db.delete(CO_TABLE_NAME,"id=?",new String[]{idVal});
        }else if(department.equals("ME")){
            db.delete(ME_TABLE_NAME,"id=?",new String[]{idVal});
        }
        else if(department.equals("CE")){
            db.delete(CE_TABLE_NAME,"id=?",new String[]{idVal});
        }
        db.close();

    }

    //Delete Faculty from table
    public void deleteFaculty(String idVal){
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete(FACULTY_TABLE_NAME,"faculty_id=?",new String[]{idVal});
    }

    //Validate Faculty from table
    public ArrayList<FacultyModal> validateFaculty(String username,String password){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor=null;
        cursor=db.rawQuery("SELECT * FROM " + FACULTY_TABLE_NAME + " WHERE email='" + username + "' and password='"+password+"' LIMIT 1",null);
        ArrayList<FacultyModal> facultyModalArrayList=new ArrayList<>();
        if(cursor!=null){
            if(cursor.moveToFirst()){
                do{
                    facultyModalArrayList.add(new FacultyModal(
                            cursor.getString(0),
                            cursor.getString(1),
                            cursor.getString(2),
                            cursor.getString(3),
                            cursor.getString(4),
                            cursor.getString(5),
                            cursor.getString(6),
                            cursor.getString(7)));
                } while (cursor.moveToNext());
            }
        }cursor.close();
        return facultyModalArrayList;
    }

    public ArrayList<FacultyModal> facultyId(String idVal){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=null;
        cursor=db.rawQuery("SELECT * FROM " + FACULTY_TABLE_NAME,null);
        ArrayList<FacultyModal> facultyModalArrayList = new ArrayList<>();
        if(cursor!=null){
            if(cursor.moveToFirst()){
                do{
                    facultyModalArrayList.add(new FacultyModal(
                            cursor.getString(0),
                            cursor.getString(1),
                            cursor.getString(2),
                            cursor.getString(3),
                            cursor.getString(4),
                            cursor.getString(5),
                            cursor.getString(6),
                            cursor.getString(7)));
                } while (cursor.moveToNext());
            }
        }
        cursor.close();
        return facultyModalArrayList;
    }

    public ArrayList<StudentModal> studentId(String idVal,String dept){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=null;
        if(dept.equals("IT")){
            // on below line we are creating a cursor with query to read data from database.
            cursor = db.rawQuery("SELECT * FROM " + IT_TABLE_NAME +" WHERE id='" + idVal + "'", null);
        }else if(dept.equals("CO")){
            cursor = db.rawQuery("SELECT * FROM " + CO_TABLE_NAME +" WHERE id='" + idVal + "'", null);
        }else if(dept.equals("ME")){
            cursor = db.rawQuery("SELECT * FROM " + ME_TABLE_NAME +" WHERE id='" + idVal + "'", null);
        }else if(dept.equals("CE")){
            cursor = db.rawQuery("SELECT * FROM " + CE_TABLE_NAME +" WHERE id='" + idVal + "'", null);
        }
        ArrayList<StudentModal> studentModalArrayList = new ArrayList<>();
        // moving our cursor to first position.
        if(cursor!=null) {
            if (cursor.moveToFirst()) {
                do {
                    // on below line we are adding the data from cursor to our array list.
                    studentModalArrayList.add(new StudentModal(
                            cursor.getString(0),
                            cursor.getString(1),
                            cursor.getString(2),
                            cursor.getString(3),
                            cursor.getString(4),
                            cursor.getString(5),
                            cursor.getString(6),
                            cursor.getString(7),
                            cursor.getString(8) ));
                } while (cursor.moveToNext());
                // moving our cursor to next.
            }
        }
        cursor.close();
        return studentModalArrayList;
    }

    public ArrayList<FacultyModal> readFaculty(){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=null;
        cursor=db.rawQuery("SELECT * FROM " + FACULTY_TABLE_NAME,null);
        ArrayList<FacultyModal> facultyModalArrayList = new ArrayList<>();
        if(cursor!=null){
            if(cursor.moveToFirst()){
                do{
                    facultyModalArrayList.add(new FacultyModal(
                            cursor.getString(0),
                            cursor.getString(1),
                            cursor.getString(2),
                            cursor.getString(3),
                            cursor.getString(4),
                            cursor.getString(5),
                            cursor.getString(6),
                            cursor.getString(7)));
                } while (cursor.moveToNext());
            }
        }
        cursor.close();
        return facultyModalArrayList;
    }
    //Read Student Info from table
    public ArrayList<StudentModal> readStudent(String dept,String syear){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursorCourses = null;
        if(dept.equals("IT")){
            // on below line we are creating a cursor with query to read data from database.
            cursorCourses = db.rawQuery("SELECT * FROM " + IT_TABLE_NAME +" WHERE year='" + syear + "'", null);
        }else if(dept.equals("CO")){
            cursorCourses = db.rawQuery("SELECT * FROM " + CO_TABLE_NAME +" WHERE year='" + syear + "'", null);
        }else if(dept.equals("ME")){
            cursorCourses = db.rawQuery("SELECT * FROM " + ME_TABLE_NAME +" WHERE year='" + syear + "'", null);
        }else if(dept.equals("CE")){
            cursorCourses = db.rawQuery("SELECT * FROM " + CE_TABLE_NAME +" WHERE year='" + syear + "'", null);
        }else{

        }
        // on below line we are creating a new array list.
        ArrayList<StudentModal> courseModalArrayList = new ArrayList<>();
        // moving our cursor to first position.
        if(cursorCourses!=null) {
            if (cursorCourses.moveToFirst()) {
                do {
                    // on below line we are adding the data from cursor to our array list.
                    courseModalArrayList.add(new StudentModal(
                            cursorCourses.getString(0),
                            cursorCourses.getString(1),
                            cursorCourses.getString(2),
                            cursorCourses.getString(3),
                            cursorCourses.getString(4),
                            cursorCourses.getString(5),
                            cursorCourses.getString(6),
                            cursorCourses.getString(7),
                            cursorCourses.getString(8) ));
                } while (cursorCourses.moveToNext());
                // moving our cursor to next.
            }
        }
        // at last closing our cursor
        // and returning our array list.
        cursorCourses.close();
        return courseModalArrayList;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        if(oldVersion<newVersion){
            db.execSQL("DROP TABLE IF EXISTS " + IT_TABLE_NAME);
            db.execSQL("DROP TABLE IF EXISTS " + CO_TABLE_NAME);
            db.execSQL("DROP TABLE IF EXISTS " + ME_TABLE_NAME);
            db.execSQL("DROP TABLE IF EXISTS " + CE_TABLE_NAME);
            db.execSQL("DROP TABLE IF EXISTS " + FACULTY_TABLE_NAME);
            db.execSQL("DROP TABLE IF EXISTS " + ATTENDANCE_TABLE_NMAE);
            onCreate(db);
        }
        }
        // this method is called to check if the table exists already.

    }
