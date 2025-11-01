package com.example.cis183_hw03_crud;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class SearchAdapter extends BaseAdapter
{
    private Context context;
    private ArrayList<Student> studentList;

    public SearchAdapter(Context c, ArrayList<Student> sl)
    {
        context = c;
        studentList = sl;
    }

    @Override
    public int getCount()
    {
        return studentList.size();
    }

    @Override
    public Object getItem(int position)
    {
        return studentList.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent)
    {
        if (view == null)
        {
            LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Search.LAYOUT_INFLATER_SERVICE);
            view = mInflater.inflate(R.layout.search_cell, null);
        }

        TextView fName = view.findViewById(R.id.tv_v_search_fName);
        TextView lName = view.findViewById(R.id.tv_v_search_lName);
        TextView uName = view.findViewById(R.id.tv_v_search_uName);
        TextView email = view.findViewById(R.id.tv_v_search_email);
        TextView age = view.findViewById(R.id.tv_v_search_age);
        TextView major = view.findViewById(R.id.tv_v_search_major);
        TextView gpa = view.findViewById(R.id.tv_v_search_gpa);

        Student student = studentList.get(position);

        fName.setText(student.getFirstName());
        lName.setText(student.getLastName());
        uName.setText(student.getUsername());
        email.setText(student.getEmail());
        age.setText(student.getAge() + "");
        major.setText(student.getMajor());
        gpa.setText(student.getGpa() + "");

        return view;
    }

}
