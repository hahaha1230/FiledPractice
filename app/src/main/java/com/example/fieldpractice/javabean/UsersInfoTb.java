package com.example.fieldpractice.javabean;

/**
 * Created by JG on 2019/5/1.
 */

public class UsersInfoTb {
    private String userName;
    private String userIcon;
    private int userId;
    private String nickName;
    private String classGrade;


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserIcon() {
        return userIcon;
    }

    public void setUserIcon(String userIcon) {
        this.userIcon = userIcon;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getClassGrade() {
        return classGrade;
    }

    public void setClassGrade(String classGrade) {
        this.classGrade = classGrade;
    }


    @Override
    public String toString() {
        return "UsersInfoTb{" +
                "userName=" + userName +
                ", userIcon='" + userIcon  +
                ", userId=" + userId +
                ", nickName=" +nickName +
                ", classGrade=" +classGrade +
                '}';
    }
}
