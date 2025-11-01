package com.example.cis183_hw03_crud;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class StudentListAdapter extends BaseAdapter
{
    Context context;
    ArrayList<Student> studentList;

    public StudentListAdapter(Context c, ArrayList<Student> sl)
    {
        context = c;
        studentList = sl;

    }

    @Override
    public int getCount() {
        return studentList.size();
    }

    @Override
    public Object getItem(int position) {
        return studentList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent)
    {
        if (view == null)
        {
            LayoutInflater mInflater = (LayoutInflater) context.getSystemService(MainActivity.LAYOUT_INFLATER_SERVICE);
            view = mInflater.inflate(R.layout.student_cell, null);
        }

        TextView fName = view.findViewById(R.id.tv_v_studentCell_fname);
        TextView lName = view.findViewById(R.id.tv_v_studentCell_lname);
        TextView uName = view.findViewById(R.id.tv_v_studentCell_uName);

        Student student = studentList.get(position);

        fName.setText(student.getFirstName());
        lName.setText(student.getLastName());
        uName.setText(student.getUsername());

        return view;
    }


}
