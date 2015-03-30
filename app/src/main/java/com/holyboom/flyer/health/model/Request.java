package com.holyboom.flyer.health.model;

import java.io.Serializable;

/**
 * Created by flyer on 15/3/20.
 */
public class Request implements Serializable {
    String time;
    String title,content;
    Patient patient;
    public Request(String time,String title,String content,Patient patient){
        this.title = title;
        this.time = time;
        this.content = content;
        this.patient = patient;
    }
    public Request(String time,String content,Patient patient){
        this.time = time;
        this.content = content;
        this.patient = patient;
    }

    public String getTitle() {
        return title;
    }

    public Patient getPatient() {
        return patient;
    }

    public String getTime() {
        return time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
