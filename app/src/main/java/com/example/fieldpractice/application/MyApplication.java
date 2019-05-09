package com.example.fieldpractice.application;

import android.app.Application;

import com.example.fieldpractice.javabean.UsersInfoTb;

/**
 * Created by JG on 2019/5/8.
 *
 *
 * 存储版本号，当前登录信息
 */

public class MyApplication extends Application {

    public String appVersion="v1.0";


    //当前登录用户
    private UsersInfoTb loginUser=new UsersInfoTb();


    public UsersInfoTb getLoginUser(){
        return loginUser;
    }

    public void userLogin(UsersInfoTb usersInfoTb)
    {
        loginUser.setUserId(usersInfoTb.getUserId());
        loginUser.setUserName(usersInfoTb.getUserName());
    }

    //退出登录
    public void userLagout(){
        loginUser=null;
    }
}
