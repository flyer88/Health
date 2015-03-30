package com.holyboom.flyer.health.model;

import java.io.Serializable;

/**
 * Created by flyer on 15/3/20.
 */
public class EncylopediaInformation implements Serializable{
    String title;
    String content;
    String time;
    public EncylopediaInformation(){}

    public EncylopediaInformation(String title,String content){
        this.title = title;
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public String getContent() {
        return content;
    }

    public String getTitle() {
        return title;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
