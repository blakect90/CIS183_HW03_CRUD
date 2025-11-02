package com.example.cis183_hw03_crud;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

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
    EditText et_j_age;
    TextView tv_j_addStudent_error;
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
        et_j_username = findViewById(R.id.et_v_addStudent_uName);
        et_j_fName = findViewById(R.id.et_v_addStudent_fName);
        et_j_lName = findViewById(R.id.et_v_addStudent_lName);
        et_j_email = findViewById(R.id.et_v_addStudent_email);
        et_j_gpa = findViewById(R.id.et_v_addStudent_gpa);
        et_j_age = findViewById(R.id.et_num_v_addSudent_age);
        tv_j_addStudent_error = findViewById(R.id.tv_v_addStudent_error);


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
                    // ERROR MESSAGE
                    tv_j_addStudent_error.setVisibility(View.VISIBLE);
                    tv_j_addStudent_error.setText("Username already exists!");

                }
                else
                {
                    // SAVE STUDENT
                    String uname = et_j_username.getText().toString();
                    String fname = et_j_fName.getText().toString();
                    String lname = et_j_lName.getText().toString();
                    String email = et_j_email.getText().toString();
                    String major = sp_j_major.getSelectedItem().toString();
                    int age = Integer.parseInt(et_j_age.getText().toString());
                    double gpa = Double.parseDouble(et_j_gpa.getText().toString());

                    Student student = new Student(fname, lname, uname, email, age, gpa, major);

                    dbHelper.addStudent(student);
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