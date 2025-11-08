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

public class ViewUpdateStudent extends AppCompatActivity {

    Intent loadedFrom;
    Intent homeIntent;
    DatabaseHelper dbHelper;
    Button btn_j_home;
    Button btn_j_update;
    EditText et_j_fName;
    EditText et_j_lName;
    EditText et_j_uName;
    EditText et_j_email;
    EditText et_j_age;
    EditText et_j_gpa;
    Spinner sp_j_major;
    ArrayAdapter<String> adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_view_update_student);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btn_j_home = findViewById(R.id.btn_v_details_home);
        btn_j_update = findViewById(R.id.btn_v_details_update);
        et_j_fName = findViewById(R.id.et_v_details_fName);
        et_j_lName = findViewById(R.id.et_v_details_lName);
        et_j_uName = findViewById(R.id.et_v_details_uName);
        et_j_email = findViewById(R.id.et_v_details_email);
        et_j_age = findViewById(R.id.et_v_details_age);
        et_j_gpa = findViewById(R.id.et_v_details_gpa);
        sp_j_major = findViewById(R.id.sp_v_details_major);

        homeIntent = new Intent(this, MainActivity.class);

        setOnClickListeners();

        dbHelper = new DatabaseHelper(this);
        dbHelper.initAllTables();

        loadedFrom = getIntent();
        String studentSelected = loadedFrom.getStringExtra("username");

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, dbHelper.getAllMajorNames());
        sp_j_major.setAdapter(adapter);

        fillStudentDetails(studentSelected);

    }
    // POPULATE FIELDS WITH SELECTED STUDENT DATA
    public void fillStudentDetails(String username)
    {
        Student student = dbHelper.getAllStudentData(username);
        Major major = dbHelper.getAllMajorData(student.getMajor());

        et_j_fName.setText(student.getFirstName());
        et_j_lName.setText(student.getLastName());
        et_j_uName.setText(student.getUsername());
        et_j_email.setText(student.getEmail());
        et_j_age.setText(student.getAge() + "");
        et_j_gpa.setText(student.getGpa() + "");
        sp_j_major.setSelection(adapter.getPosition(major.getMajorName()));
    }
    // BUTTON LISTENERS
    public void setOnClickListeners()
    {
        btn_j_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(homeIntent);
            }
        });
        btn_j_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Student student = new Student();
                student.setFirstName(et_j_fName.getText().toString());
                student.setLastName(et_j_lName.getText().toString());
                student.setUsername(et_j_uName.getText().toString());
                student.setEmail(et_j_email.getText().toString());
                student.setAge(Integer.parseInt(et_j_age.getText().toString()));
                student.setGpa(Double.parseDouble(et_j_gpa.getText().toString()));
                student.setMajor(sp_j_major.getSelectedItem().toString());
                dbHelper.updateStudent(student);

                startActivity(homeIntent);
            }
        });
    }
}