package com.example.fieldpractice.javabean;

/**
 * Created by JG on 2019/5/8.
 */

public class VerifyUserLogin {
    private String err;

    private String username;

    private String password;

    public  VerifyUserLogin(String err,String name,String pwd)
    {
        this.err=err;
        this.username=name;
        this.password=pwd;
    }

    public String getErr() {
        return err;
    }

    public void setErr(String err) {
        this.err = err;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "{"+
                "message{ username="+username+
                ",password="+password+
                "},"+"err="+err+
                "}";
    }
}
