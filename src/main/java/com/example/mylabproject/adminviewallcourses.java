package com.example.mylabproject;

public class adminviewallcourses {
String cid,cname,fname,dept,section;

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public adminviewallcourses(String cid, String cname, String fname, String dept, String section) {
        this.cid = cid;
        this.cname = cname;
        this.fname = fname;
        this.dept = dept;
        this.section = section;
    }
}
