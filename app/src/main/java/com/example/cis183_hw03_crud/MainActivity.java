package com.example.cis183_hw03_crud;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    Button btn_j_addStudent;
    Button btn_j_addMajor;
    Button btn_j_search;
    ListView lv_j_studentList;
    Intent intent_j_addStudent;
    Intent intent_j_addMajor;
    Intent intent_j_search;
    DatabaseHelper dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // GUI
        btn_j_addStudent = findViewById(R.id.btn_v_addStudent);
        btn_j_addMajor = findViewById(R.id.btn_v_addMajor);
        btn_j_search = findViewById(R.id.btn_v_search);

        lv_j_studentList = findViewById(R.id.lv_v_studentList);

        intent_j_addStudent = new Intent(MainActivity.this, AddStudent.class);
        intent_j_addMajor = new Intent(MainActivity.this, AddMajor.class);
        intent_j_search = new Intent(MainActivity.this, Search.class);

        // Initialize Database with dummy data
        dbHelper = new DatabaseHelper(this);
        dbHelper.initAllTables();

        // Set On Click Listeners
        setOnClickListeners();

        // FOR TESTING ONLY
        checkTableRecordCount();


    }
    // FOR TESTING
    private void checkTableRecordCount()
    {
        Log.d("STUDENT COUNT: ", dbHelper.countRecordsFromTable(dbHelper.getStudentTableName()) + "");
        Log.d("MAJOR COUNT: ", dbHelper.countRecordsFromTable(dbHelper.getMajorTableName()) + "");

        Student test = dbHelper.getAllStudentData("addiet");
        Major test2 = dbHelper.getAllMajorData("Business");

        Log.d("TEST STUDENT: ", test.getFirstName() + " " + test.getLastName() + " " + test.getUsername() + " " + test.getEmail() + " " + test.getAge() + " " + test.getGpa() + " " + test.getMajor());
        Log.d("TEST MAJOR: ", test2.getMajorID() + " " + test2.getMajorName() + " " + test2.getMajorPrefix());
    }
    // BUTTON LISTENERS
    public void setOnClickListeners()
    {
        btn_j_addStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d("TEST: ", "Add Student Button Clicked");

                startActivity(intent_j_addStudent);

            }
        });
        btn_j_addMajor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d("TEST: ", "Add Major Button Clicked");

                startActivity(intent_j_addMajor);

            }
        });
        btn_j_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d("TEST: ", "Search Button Clicked");
                startActivity(intent_j_search);

            }
        });
    }
}