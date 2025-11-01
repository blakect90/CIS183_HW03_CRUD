package com.example.cis183_hw03_crud;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper
{
    private static final String DATABASE_NAME = "school.db";
    private static final String STUDENT_TABLE_NAME = "students";
    private static final String MAJOR_TABLE_NAME = "majors";
    private static final int DATABASE_VERSION = 4;

    public DatabaseHelper(Context c)
    {
        super(c, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("CREATE TABLE " + STUDENT_TABLE_NAME + " (username VARCHAR(20) PRIMARY KEY, fname VARCHAR(20), lname VARCHAR(20), email VARCHAR(30), age INT, gpa DOUBLE, major VARCHAR(20));");
        db.execSQL("CREATE TABLE " + MAJOR_TABLE_NAME + " (majorID INTEGER PRIMARY KEY AUTOINCREMENT, majorName VARCHAR(20), majorPrefix VARCHAR(5));");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS " + STUDENT_TABLE_NAME + ";");
        db.execSQL("DROP TABLE IF EXISTS " + MAJOR_TABLE_NAME + ";");

        onCreate(db);
    }

    public String getStudentTableName()
    {
        return STUDENT_TABLE_NAME;
    }
    public String getMajorTableName()
    {
        return MAJOR_TABLE_NAME;
    }

    public void initAllTables()
    {
        initStudentTable();
        initMajorTable();
    }

    private void initStudentTable()
    {
        if(countRecordsFromTable(STUDENT_TABLE_NAME) == 0)
        {
            SQLiteDatabase db = this.getWritableDatabase();

            db.execSQL("INSERT INTO " + STUDENT_TABLE_NAME + " VALUES ('blaket', 'Blake', 'Taylor', 'blaket@school.edu', 36, 4.0, 'Computer Science');");
            db.execSQL("INSERT INTO " + STUDENT_TABLE_NAME + " VALUES ('ryanl', 'Ryan', 'Lambert', 'ryanl@school.edu', 31, 4.0, 'Business');");
            db.execSQL("INSERT INTO " + STUDENT_TABLE_NAME + " VALUES ('addiet', 'Addie', 'Taylor', 'addiet@school.edu', 6, 3.5, 'Law');");
            db.execSQL("INSERT INTO " + STUDENT_TABLE_NAME + " VALUES ('janed', 'Jane', 'Doe', 'janed@school.edu', 21, 2.0, 'English');");
            db.execSQL("INSERT INTO " + STUDENT_TABLE_NAME + " VALUES ('johnd', 'John', 'Doe', 'johnd@school.edu', 21, 3.0, 'History');");
            db.execSQL("INSERT INTO " + STUDENT_TABLE_NAME + " VALUES ('spencera', 'Spencer', 'Adams', 'spencera@school.edu', 25, 3.8, 'Computer Science');");


            db.close();
        }
    }
    private void initMajorTable()
    {
        if(countRecordsFromTable(MAJOR_TABLE_NAME) == 0)
        {
            SQLiteDatabase db = this.getWritableDatabase();

            db.execSQL("INSERT INTO " + MAJOR_TABLE_NAME + " (majorName, majorPrefix) VALUES ('Computer Science', 'CIS');");
            db.execSQL("INSERT INTO " + MAJOR_TABLE_NAME + " (majorName, majorPrefix) VALUES ('Business', 'BUS');");
            db.execSQL("INSERT INTO " + MAJOR_TABLE_NAME + " (majorName, majorPrefix) VALUES ('Law', 'LAW');");
            db.execSQL("INSERT INTO " + MAJOR_TABLE_NAME + " (majorName, majorPrefix) VALUES ('English', 'ENG');");
            db.execSQL("INSERT INTO " + MAJOR_TABLE_NAME + " (majorName, majorPrefix) VALUES ('History', 'HIS');");


            db.close();
        }
    }

    public int countRecordsFromTable(String tableName)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, tableName);
        db.close();
        return numRows;
    }

    public Student getAllStudentData(String userName)
    {
        if(userNameExists(userName))
        {
            Student student = new Student();
            String selectQuery = "SELECT * FROM " + STUDENT_TABLE_NAME + " WHERE username = '" + userName + "';";
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(selectQuery, null);

            cursor.moveToFirst();

            student.setUsername(cursor.getString(0));
            student.setFirstName(cursor.getString(1));
            student.setLastName(cursor.getString(2));
            student.setEmail(cursor.getString(3));
            student.setAge(cursor.getInt(4));
            student.setGpa(cursor.getDouble(5));
            student.setMajor(cursor.getString(6));

            cursor.close();
            db.close();

            return student;
        }
        else
        {
            return null;
        }

    }
    public Major getAllMajorData(String majorName)
    {
        if(majorExists(majorName)) {
            Major major = new Major();
            String selectQuery = "SELECT * FROM " + MAJOR_TABLE_NAME + " WHERE majorName = '" + majorName + "';";
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(selectQuery, null);

            cursor.moveToFirst();

            major.setMajorID(cursor.getInt(0));
            major.setMajorName(cursor.getString(1));
            major.setMajorPrefix(cursor.getString(2));

            cursor.close();
            db.close();

            return major;
        }
        else
        {
            return null;

        }
    }

    public boolean userNameExists(String userName) {
        SQLiteDatabase db = this.getReadableDatabase();
        String checkUserName = "SELECT count(userName) FROM " + STUDENT_TABLE_NAME + " WHERE userName = '" + userName + "';";
        Cursor cursor = db.rawQuery(checkUserName, null);
        cursor.moveToFirst();
        int count = cursor.getInt(0);
        db.close();

        return count != 0;
    }

    public boolean majorExists(String majorName)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String checkMajorName = "SELECT count(majorName) FROM " + MAJOR_TABLE_NAME + " WHERE majorName = '" + majorName + "';";
        Cursor cursor = db.rawQuery(checkMajorName, null);
        cursor.moveToFirst();
        int count = cursor.getInt(0);
        db.close();

        return count != 0;
    }

    public ArrayList<Student> getAllStudentData()
    {
        String sqlQuery = "SELECT * FROM " + STUDENT_TABLE_NAME + ";";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sqlQuery, null);

        ArrayList<Student> studentList = new ArrayList<>();

        if(cursor.moveToFirst())
        {
            do
            {
                Student student = new Student();
                student.setUsername(cursor.getString(0));
                student.setFirstName(cursor.getString(1));
                student.setLastName(cursor.getString(2));
                student.setEmail(cursor.getString(3));
                student.setAge(cursor.getInt(4));
                student.setGpa(cursor.getDouble(5));
                student.setMajor(cursor.getString(6));

                studentList.add(student);

            }
            while(cursor.moveToNext());
        }

        db.close();
        return studentList;
    }

    public ArrayList<Student> getAllStudentsGivenFirstName(String f)
    {
        String fName = f;
        String sqlQuery = "SELECT * FROM " + STUDENT_TABLE_NAME + " WHERE fname = '" + fName + "';";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sqlQuery, null);

        ArrayList<Student> studentList = new ArrayList<>();
        if(cursor.moveToFirst())
        {
            do
            {
                Student student = new Student();
            }
            while(cursor.moveToNext());
        }
        db.close();
        return studentList;
    }
}
