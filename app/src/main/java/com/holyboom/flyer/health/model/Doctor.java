package com.holyboom.flyer.health.model;

import java.io.Serializable;

/**
 * Created by flyer on 15/3/18.
 */
public class Doctor extends User implements Serializable{
    String [] navigationList = new String[]{"通知","百科","见面会","关于"};
    DoctorInformation doctorInformation;

    public Doctor(){}
    public Doctor(String id){
        this.id = id;
    }
    public Doctor(String id,DoctorInformation doctorInformation){
        this.id = id;
        this.doctorInformation = doctorInformation;
    }

    @Override
    public String getId() {
        return super.getId();
    }


    public void setNavigationList(String[] navigationList) {
        this.navigationList = navigationList;
    }

    public String[] getNavigationList() {
        return navigationList;
    }

    public void setDoctorInformation(DoctorInformation doctorInformation) {
        this.doctorInformation = doctorInformation;
    }

    public DoctorInformation getDoctorInformation() {
        return doctorInformation;
    }
}
