package com.example.mylabproject;

public class facultysoltshow {
    String day,stime,etime;

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
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

    public facultysoltshow(String day, String stime, String etime) {
        this.day = day;
        this.stime = stime;
        this.etime = etime;
    }
}
