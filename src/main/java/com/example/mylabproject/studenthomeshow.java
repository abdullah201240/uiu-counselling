package com.example.mylabproject;

public class studenthomeshow {
    String fname,start,end,status, date;

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public studenthomeshow(String fname, String start, String end, String status, String date) {
        this.fname = fname;
        this.start = start;
        this.end = end;
        this.status = status;
        this.date = date;
    }
}
