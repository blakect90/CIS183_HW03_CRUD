package com.example.cis183_hw03_crud;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AddMajor extends AppCompatActivity {

    Button btn_j_saveMajor;
    Button btn_j_addMajor_home;
    EditText et_j_majorName;
    EditText et_j_majorPrefix;
    TextView tv_j_addMajor_Error;
    Intent intent_j_home;
    DatabaseHelper dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_major);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btn_j_saveMajor = findViewById(R.id.btn_v_saveMajor);
        btn_j_addMajor_home = findViewById(R.id.btn_v_addMajor_home);
        et_j_majorName = findViewById(R.id.et_v_majorName);
        et_j_majorPrefix = findViewById(R.id.et_v_majorPrefix);
        tv_j_addMajor_Error = findViewById(R.id.tv_v_addMajor_Error);

        intent_j_home = new Intent(AddMajor.this, MainActivity.class);

        setOnClickListeners();

        dbHelper = new DatabaseHelper(this);
        dbHelper.initAllTables();

    }

    public void setOnClickListeners()
    {
        btn_j_saveMajor.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
//                Log.d("TEST: ", "Save Major Button Clicked!!");

                // RESET ERROR MSG
                tv_j_addMajor_Error.setVisibility(View.INVISIBLE);

                // CHECK WHETHER MAJOR ALREADY EXISTS
                if(dbHelper.majorExists(et_j_majorName.getText().toString()))
                {
                    //Log.d("TEST: ", "Major already exists!");
                    // ERROR MESSAGE
                    tv_j_addMajor_Error.setVisibility(View.VISIBLE);
                    tv_j_addMajor_Error.setText("Major already exists!");
                }
                else
                {
                   // ADD NEW MAJOR HERE
//                    Log.d("TEST: ", "New Major Added!!");
                    String majorName = et_j_majorName.getText().toString();
                    String majorPrefix = et_j_majorPrefix.getText().toString();

                    SQLiteDatabase db = dbHelper.getWritableDatabase();
                    db.execSQL("INSERT INTO " + dbHelper.getMajorTableName() + " (majorName, majorPrefix) VALUES ('" + majorName + "', '" + majorPrefix + "');");
                    db.close();
                }
            }
        });
        btn_j_addMajor_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("TEST: ", "Home Button Clicked!!");
                startActivity(intent_j_home);
            }
        });
    }

}