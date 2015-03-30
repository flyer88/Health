package com.holyboom.flyer.health.model;

import java.io.Serializable;

/**
 * Created by flyer on 15/3/17.
 */
public abstract class User implements Serializable{

    String id;
    String pwd;
    String userType;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getPwd() {
        return pwd;
    }

    public void setUserType(String userType){
        this.userType = userType;
    }
    public String getUserType(){
        return this.userType;
    }

    public abstract  String[] getNavigationList();

}
