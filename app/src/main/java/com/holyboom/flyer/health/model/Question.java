package com.holyboom.flyer.health.model;

import java.io.Serializable;

/**
 * Created by flyer on 15/3/20.
 */
public class Question implements Serializable {
    String time;
    String title,content;
    Patient patient;
    public Question(String time,String title,String content,Patient patient){
        this.time = time;
        this.title = title;
        this.content = content;
        this.patient = patient;
    }
    public Question(String time,String title,Patient patient){
        this.time = time;
        this.title = title;
        this.patient = patient;
    }

    public String getTime() {
        return time;
    }

    public Patient getPatient() {
        return patient;
    }


    public String getContent() {
        return content;
    }

    public String getTitle() {
        return title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
