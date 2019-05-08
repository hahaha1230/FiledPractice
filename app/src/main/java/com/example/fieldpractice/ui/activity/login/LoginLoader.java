package com.example.fieldpractice.ui.activity.login;

import com.example.fieldpractice.api.RequestAPI;
import com.example.fieldpractice.base.ObjectLoader;
import com.example.fieldpractice.javabean.VerifyUserLogin;
import com.example.fieldpractice.manager.RetrofitRequestManager;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

/**
 * Created by JG on 2019/5/7.
 */

public class LoginLoader extends ObjectLoader {
    private RequestAPI requestAPI;

    public LoginLoader()
    {
        requestAPI= RetrofitRequestManager.getManager().create(RequestAPI.class);
    }



    public Observable<VerifyUserLogin>getResult(String name, String pwd)
    {
        return observe(requestAPI.verify(name,pwd))
                .map(new Function<VerifyUserLogin, VerifyUserLogin>() {
                    @Override
                    public VerifyUserLogin apply(VerifyUserLogin verifyUserLogin) throws Exception {
                        if (verifyUserLogin.isLoginState())
                        {
                            //登陆成功
                        }
                        else {
                            //登陆失败
                        }
                        return verifyUserLogin;
                    }
                });
    }



}
