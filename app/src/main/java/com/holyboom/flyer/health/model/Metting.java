package com.holyboom.flyer.health.model;

import java.io.Serializable;

/**
 * Created by flyer on 15/3/20.
 */
public class Metting implements Serializable{
    String place;
    String time;
    Patient patient;
    public Metting(){

    }
    public Metting(String place,String time){
        this.place = place;
        this.time = time;
    }
    public Metting(String place,String time,Patient patient){
        this.place = place;
        this.time = time;
        this.patient = patient;
    }

    public Patient getPatient() {
        return patient;
    }

    public String getTime() {
        return time;
    }

    public String getPlace() {
        return place;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}
