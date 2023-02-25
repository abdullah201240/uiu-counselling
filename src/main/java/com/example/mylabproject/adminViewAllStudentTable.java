package com.example.mylabproject;

public class adminViewAllStudentTable {

    String id;
    String name;
    String email;
    String dept;
    public adminViewAllStudentTable(String id, String name, String email, String dept) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.dept = dept;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

}
