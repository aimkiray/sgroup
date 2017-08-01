package com.shengdiyage.entity;

import java.util.Date;

public class User {
    private String userId;
    private String userName;
    private String userPassword;
    private String realName;
    private String email;
    private int gender;
    private Date signDate;

    public User() {
    }

    public User(String userId, String userName, String userPassword, String realName, String email, int gender, Date signDate) {
        this.userId = userId;
        this.userName = userName;
        this.userPassword = userPassword;
        this.realName = realName;
        this.email = email;
        this.gender = gender;
        this.signDate = signDate;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public Date getSignDate() {
        return signDate;
    }

    public void setSignDate(Date signDate) {
        this.signDate = signDate;
    }
}
