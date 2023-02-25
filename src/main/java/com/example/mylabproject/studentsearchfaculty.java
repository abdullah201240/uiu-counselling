package com.example.mylabproject;

public class studentsearchfaculty {
    String fname,email,dept,id;

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public studentsearchfaculty(String fname, String email, String dept, String id) {
        this.fname = fname;
        this.email = email;
        this.dept = dept;
        this.id = id;
    }
}
