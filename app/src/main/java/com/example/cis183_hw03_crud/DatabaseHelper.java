package com.example.cis183_hw03_crud;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DatabaseHelper extends SQLiteOpenHelper
{
    private static final String DATABASE_NAME = "school.db";
    private static final String STUDENT_TABLE_NAME = "students";
    private static final String MAJOR_TABLE_NAME = "majors";
    private static final int DATABASE_VERSION = 15;

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
    // INITIALIZE STUDENT TABLE WITH DUMMY DATA
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


            db.close();
        }
    }
    // INITIALIZE MAJOR TABLE WITH DUMMY DATA
    private void initMajorTable()
    {
        if(countRecordsFromTable(MAJOR_TABLE_NAME) == 0)
        {
            SQLiteDatabase db = this.getWritableDatabase();

            db.execSQL("INSERT INTO " + MAJOR_TABLE_NAME + " (majorName, majorPrefix) VALUES ('No Selection', 'NULL');");
            db.execSQL("INSERT INTO " + MAJOR_TABLE_NAME + " (majorName, majorPrefix) VALUES ('Computer Science', 'CIS');");
            db.execSQL("INSERT INTO " + MAJOR_TABLE_NAME + " (majorName, majorPrefix) VALUES ('Business', 'BUS');");
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

    public List<String> getAllMajorNames()
    {
        String sqlQuery = "SELECT majorName FROM " + MAJOR_TABLE_NAME + ";";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sqlQuery, null);

        ArrayList<String> majorList = new ArrayList<>();
        if(cursor.moveToFirst())
        {
            do
            {
                majorList.add(cursor.getString(0));
            }
            while(cursor.moveToNext());
        }
        db.close();
        return majorList;
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

    public void addStudent(Student s)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("INSERT INTO " + STUDENT_TABLE_NAME + " VALUES ('" + s.getUsername() + "', '" + s.getFirstName() + "', '" + s.getLastName() + "', '" + s.getEmail() + "', '" + s.getAge() + "', '" + s.getGpa() + "', '" + s.getMajor() + "');");
        db.close();
    }

    public void updateStudent(Student s)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("UPDATE " + STUDENT_TABLE_NAME + " SET fname = '" + s.getFirstName() + "', lname = '" + s.getLastName() + "', email = '" + s.getEmail() + "', age = '" + s.getAge() + "', gpa = '" + s.getGpa() + "', major = '" + s.getMajor() + "' WHERE username = '" + s.getUsername() + "';");
        db.close();
    }

    public void deleteStudent(String userName)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + STUDENT_TABLE_NAME + " WHERE username = '" + userName + "';");
        db.close();
    }
    // SEARCH DATABASE GIVEN CRITERIA ON SEARCH PAGE
    public ArrayList<Student> getAllStudentsGivenCriteria(String f, String l, String u, String m, double gMin, double gMax)
    {
        ArrayList<Student> listResults = new ArrayList<>();
        String selectStatement = "SELECT * FROM " + STUDENT_TABLE_NAME + " WHERE ";

        if(f.isEmpty())
        {
            selectStatement += "fname is not null and ";
        }
        else
        {
            selectStatement += "fname = '" + f + "' and ";

        }
        if(l.isEmpty())
        {
            selectStatement += "lname is not null and ";
        }
        else
        {
            selectStatement += "lname = '" + l + "' and ";
        }
        if(u.isEmpty())
        {
            selectStatement += "username is not null and ";
        }
        else
        {
            selectStatement += "username = '" + u + "' and ";
        }
        if(Objects.equals(m, "No Selection"))
        {
            selectStatement += "major is not null and ";
        }
        else
        {
            selectStatement += "major = '" + m + "' and ";
        }
        if(gMin == 0)
        {
            selectStatement += "gpa is not null and ";
        }
        else
        {
            selectStatement += "gpa >= " + gMin + " and ";
        }
        if(gMax == 0)
        {
            selectStatement += "gpa is not null and ";
        }
        else
        {
            selectStatement += "gpa <= " + gMax + ";";
        }

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectStatement, null);

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

                listResults.add(student);
            }
            while(cursor.moveToNext());
            db.close();
        }
        return listResults;

    }

}
