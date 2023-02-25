package com.example.mylabproject;

public class homepinfaculty {
    String tname,coursename,section;

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public String getCoursename() {
        return coursename;
    }

    public void setCoursename(String coursename) {
        this.coursename = coursename;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public homepinfaculty(String tname, String coursename, String section) {
        this.tname = tname;
        this.coursename = coursename;
        this.section = section;
    }
}
