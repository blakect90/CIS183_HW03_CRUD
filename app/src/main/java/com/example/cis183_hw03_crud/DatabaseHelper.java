package com.example.cis183_hw03_crud;

import android.content.Context;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper
{
    private static final String DATABASE_NAME = "school.db";
    private static final String STUDENT_TABLE_NAME = "students";
    private static final String MAJOR_TABLE_NAME = "majors";
    private static final int DATABASE_VERSION = 2;

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


}
