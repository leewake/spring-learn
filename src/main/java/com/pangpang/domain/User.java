package com.pangpang.domain;

import java.io.Serializable;

/**
 * Created by leewake on 2017/8/4 0004.
 */
public class User implements Serializable{

    public User() {
    }

    public User(String userName, String eamil, String mobile) {
        this();
        this.userName = userName;
        this.eamil = eamil;
        this.mobile = mobile;
    }

    private String userName;

    private String eamil;

    private String mobile;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEamil() {
        return eamil;
    }

    public void setEamil(String eamil) {
        this.eamil = eamil;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", eamil='" + eamil + '\'' +
                ", mobile='" + mobile + '\'' +
                '}';
    }
}
