package com.example.cis183_hw03_crud;

public class Major
{
    private String majorName;
    private String majorPrefix;
    private Integer majorID;  //autoincrement ID?? / Primary Key

    // DEFAULT CONSTRUCTOR
    public Major()
    {

    }
    // OVERLOADED CONSTRUCTOR
    public Major(String n, String p, Integer id)
    {
        majorName = n;
        majorPrefix = p;
        majorID = id;
    }
    // GETTERS AND SETTERS
    public String getMajorName() {
        return majorName;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName;
    }

    public String getMajorPrefix() {
        return majorPrefix;
    }

    public void setMajorPrefix(String majorPrefix) {
        this.majorPrefix = majorPrefix;
    }

    public Integer getMajorID() {
        return majorID;
    }

    public void setMajorID(Integer majorID) {
        this.majorID = majorID;
    }
}
