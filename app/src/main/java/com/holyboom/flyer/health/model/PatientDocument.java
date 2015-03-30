package com.holyboom.flyer.health.model;

import java.io.Serializable;

/**
 * Created by flyer on 15/3/18.
 */
public class PatientDocument implements Serializable{
    String illnessTime;
    String illnessContent;

    public PatientDocument(String illnessTime,String illnessContent){
        this.illnessTime = illnessTime;
        this.illnessContent = illnessContent;
    }

    public String getIllnessTime() {
        return illnessTime;
    }

    public String getIllnessContent() {
        return illnessContent;
    }

    public void setIllnessTime(String illnessTime) {
        this.illnessTime = illnessTime;
    }

    public void setIllnessContent(String illnessContent) {
        this.illnessContent = illnessContent;
    }
}
