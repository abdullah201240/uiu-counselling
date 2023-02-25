package com.example.mylabproject;

public class fbookingtable {
    String sid, sname, stime, etime, status, date;
    int idbook;

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public int getIdbook() {
        return idbook;
    }

    public void setIdbook(int idbook) {
        this.idbook = idbook;
    }

    public String getStime() {
        return stime;
    }

    public void setStime(String stime) {
        this.stime = stime;
    }

    public String getEtime() {
        return etime;
    }

    public void setEtime(String etime) {
        this.etime = etime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public fbookingtable(String sid, String sname, String stime, String etime, String status, int idbook, String date) {
        this.sid = sid;
        this.sname = sname;
        this.stime = stime;
        this.etime = etime;
        this.status = status;
        this.idbook = idbook;
        this.date = date;
    }
}
