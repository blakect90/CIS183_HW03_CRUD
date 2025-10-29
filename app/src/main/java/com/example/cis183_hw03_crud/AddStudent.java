package com.example.cis183_hw03_crud;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AddStudent extends AppCompatActivity {

    Button btn_j_saveStudent;
    Button btn_j_addStudent_home;
    Intent intent_j_home;


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

        intent_j_home = new Intent(AddStudent.this, MainActivity.class);

        setOnClickListeners();


    }

    public void setOnClickListeners()
    {
        btn_j_saveStudent.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {

                Log.d("TEST: ", "Save Student Button Clicked!!");
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