package com.example.cis183_hw03_crud;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AddStudent extends AppCompatActivity {

    Button btn_j_saveStudent;
    Button btn_j_addStudent_home;
    EditText et_j_username;
    EditText et_j_fName;
    EditText et_j_lName;
    EditText et_j_email;
    EditText et_j_gpa;
    Spinner sp_j_major;
    Intent intent_j_home;
    DatabaseHelper dbHelper;
    ArrayAdapter<String> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_student);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btn_j_saveStudent = findViewById(R.id.btn_v_saveStudent);
        btn_j_addStudent_home = findViewById(R.id.btn_v_addStudent_home);
        sp_j_major = findViewById(R.id.sp_v_addStudent_major);


        intent_j_home = new Intent(AddStudent.this, MainActivity.class);

        dbHelper = new DatabaseHelper(this);
//        dbHelper.initAllTables();
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, dbHelper.getAllMajorNames());
        sp_j_major.setAdapter(adapter);


        setOnClickListeners();

    }

    public void setOnClickListeners()
    {
        btn_j_saveStudent.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {

                Log.d("TEST: ", "Save Student Button Clicked!!");

                if(dbHelper.userNameExists(et_j_username.getText().toString()))
                {
                    // ERROR MESSAGE - USERNAME ALREADY EXISTS
                    Log.d("TEST: ", "Username already exists!");

                    String uname = et_j_username.getText().toString();
                    String fname = et_j_fName.getText().toString();
                    String lname = et_j_lName.getText().toString();
                    String email = et_j_email.getText().toString();
                    String major = sp_j_major.getSelectedItem().toString();
                    double gpa = Double.parseDouble(et_j_gpa.getText().toString());

                    //Student student = new Student(uname, fname, lname, email, major, gpa);

                    //dbHelper.addStudent(student);
                }
                else
                {
                    // SAVE STUDENT

                }
            }
        });
        btn_j_addStudent_home.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Log.d("TEST: ", "Home Button Clicked!!");

                startActivity(intent_j_home);
            }
        });
    }
}