package com.example.fieldpractice.login;

/**
 * Created by JG on 2019/5/3.
 */

public interface ILogin {

    interface M{
        void requestLogin(String name,String pwd) throws Exception;
    }

    interface VP{
        void requestLogin(String name,String pwd);

        void responseLoginResult(boolean loginStatusResult);
    }
}
