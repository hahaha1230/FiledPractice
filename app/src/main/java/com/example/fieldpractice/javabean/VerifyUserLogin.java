package com.example.fieldpractice.javabean;

/**
 * Created by JG on 2019/5/8.
 */

public class VerifyUserLogin {
    private boolean loginState;

    private UsersInfoTb usersInfoTb;

    public boolean isLoginState() {
        return loginState;
    }

    public void setLoginState(boolean loginState) {
        this.loginState = loginState;
    }

    public UsersInfoTb getUsersInfoTb() {
        return usersInfoTb;
    }

    public void setUsersInfoTb(UsersInfoTb usersInfoTb) {
        this.usersInfoTb = usersInfoTb;
    }
}
