package com.holyboom.flyer.health.model;

import java.io.Serializable;

/**
 * Created by flyer on 15/3/18.
 */
public class Patient extends User implements Serializable{
    String [] navigationList = new String[]{"医疗档案","求医","关于"};
    PatientDocument patientDocument;

    public Patient(String id){
        this.id = id;
    }

    public Patient(){

    }
    public String[] getNavigationList() {
        return navigationList;
    }
}
