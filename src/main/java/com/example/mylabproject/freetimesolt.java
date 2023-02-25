package com.example.mylabproject;

public class freetimesolt {
  String fday,fstart,fend;
  int id;

    public String getFday() {
        return fday;
    }

    public void setFday(String fday) {
        this.fday = fday;
    }

    public String getFstart() {
        return fstart;
    }

    public void setFstart(String fstart) {
        this.fstart = fstart;
    }

    public String getFend() {
        return fend;
    }

    public void setFend(String fend) {
        this.fend = fend;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public freetimesolt(String fday, String fstart, String fend, int id) {
        this.fday = fday;
        this.fstart = fstart;
        this.fend = fend;
        this.id = id;
    }
}
