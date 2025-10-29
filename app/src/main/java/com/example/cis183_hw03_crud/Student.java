package com.example.cis183_hw03_crud;

public class Student {

    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private double gpa;
    private String major;

    public Student()
    {

    }
    public Student(String f, String l, String u, String e, double g, String m)
    {
        firstName = f;
        lastName = l;
        username = u;
        email = e;
        gpa = g;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }
}
