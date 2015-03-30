package com.holyboom.flyer.health.model;

import java.io.Serializable;

/**
 * Created by flyer on 15/3/18.
 */
public class DoctorInformation implements Serializable{
    String name;
    String year;
    String filed;
    String hospital;
    String identification;


    public DoctorInformation(String name,String year,String filed,String hospital,String identification){
        this.name = name;
        this.year = year;
        this.filed = filed;
        this.hospital = hospital;
        this.identification = identification;

    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFiled(String filed) {
        this.filed = filed;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getName() {
        return name;
    }

    public String getYear() {
        return year;
    }

    public String getFiled() {
        return filed;
    }
    public String getIdentification() {
        return identification;
    }
    public String getHospital() {
        return hospital;
    }
}
