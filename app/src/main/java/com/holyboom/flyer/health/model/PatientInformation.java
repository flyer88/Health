package com.holyboom.flyer.health.model;

import java.io.Serializable;

/**
 * Created by flyer on 15/3/18.
 */
public class PatientInformation implements Serializable{
    String name;
    String sex;
    String year;

    public PatientInformation(String name,String year,String sex){
        this.name = name;
        this.sex = sex;
        this.year = year;
    }

    public String getName() {
        return name;
    }

    public String getYear() {
        return year;
    }

    public String getSex() {
        return sex;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
