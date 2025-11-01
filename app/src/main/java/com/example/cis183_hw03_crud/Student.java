package com.example.cis183_hw03_crud;

public class Student {

    private String firstName;
    private String lastName;
    private String username; //primary key
    private String email;
    private int age;
    private double gpa;
    private String major;

    //DEFAULT CONSTRUCTOR
    public Student()
    {

    }
    //OVERLOADED CONSTRUCTOR
    public Student(String f, String l, String u, String e, int a, double g, String m)
    {
        firstName = f;
        lastName = l;
        username = u;
        email = e;
        age = a;
        gpa = g;
        major = m;
    }

    // GETTERS AND SETTERS
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
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
