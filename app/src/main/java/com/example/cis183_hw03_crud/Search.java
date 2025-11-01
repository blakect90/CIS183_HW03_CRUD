package com.example.cis183_hw03_crud;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Search extends AppCompatActivity {

    Button btn_j_search_search;
    Button btn_j_search_home;
    EditText et_j_search_fName;
    EditText et_j_search_lName;
    EditText et_j_search_uName;
    Spinner sp_j_major;
    Spinner sp_j_gpaRange;
    ListView lv_j_searchList;
    Intent intent_j_home;
    DatabaseHelper db;
    SearchAdapter searchAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_search);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btn_j_search_search = findViewById(R.id.btn_v_search_search);
        btn_j_search_home = findViewById(R.id.btn_v_search_home);
        et_j_search_fName = findViewById(R.id.et_v_search_fName);
        et_j_search_lName = findViewById(R.id.et_v_search_lName);
        et_j_search_uName = findViewById(R.id.et_v_search_uName);
        sp_j_major = findViewById(R.id.sp_v_major);
        sp_j_gpaRange = findViewById(R.id.sp_v_gpaRange);
        lv_j_searchList = findViewById(R.id.lv_v_searchList);


        intent_j_home = new Intent(Search.this, MainActivity.class);

        db = new DatabaseHelper(this);
        db.initAllTables();

        setOnClickListeners();
    }

    private void setOnClickListeners() {
        btn_j_search_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                //Log.d("TEST: ", "Search Button Clicked!!");
                populateListView();
            }
        });
        btn_j_search_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Log.d("TEST: ", "Home Button Clicked!!");
                startActivity(intent_j_home);
            }
        });
    }

    private void populateListView()
    {
//        searchAdapter = new SearchAdapter(this, db.getAllStudentsGivenFirstName(et_j_search_fName.getText().toString()));
//        lv_j_searchList.setAdapter(searchAdapter);
//
//        Log.d("FOUND: ", db.getAllStudentsGivenFirstName(et_j_search_fName.getText().toString()).size() + "");

//        searchAdapter = new SearchAdapter(this, db.getAllStudentsGivenLastName(et_j_search_lName.getText().toString()));
//        lv_j_searchList.setAdapter(searchAdapter);

        searchAdapter = new SearchAdapter(this, db.getAllStudentsGivenUsername(et_j_search_uName.getText().toString()));
        lv_j_searchList.setAdapter(searchAdapter);
    }

}