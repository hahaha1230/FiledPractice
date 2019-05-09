package com.example.fieldpractice.ui.activity.login;

import android.util.Log;

import com.example.fieldpractice.base.BasePresenter;

/**
 * Created by JG on 2019/5/3.
 */

public class LoginPresenter extends BasePresenter<LoginActivity,LoginModel,ILogin.VP> {


    @Override
    public LoginModel getModelInstance() {
        return new LoginModel(this);
    }

    @Override
    public ILogin.VP getContract() {
        return new ILogin.VP() {
            @Override
            public void requestLogin(String name, String pwd) {
                //验证请求信息，进行逻辑处理
                //....
                try {
                    mModel.getContract().requestLogin(name,pwd);
                   // mModel.requestLogin(name,pwd);
                } catch (Exception e) {
                    Log.d("hhh","login presenter"+e);
                    //e.printStackTrace();
                    //异常处理
                }
            }

            @Override
            public void responseLoginResult(boolean loginStatusResult) {
                mView.getContract().responseLoginResult(loginStatusResult);
            }
        };
    }


    /*  @Override
    public void requestLogin(String name, String pwd) {
        //验证请求信息，进行逻辑处理
        //....
        try {
            mModel.requestLogin(name,pwd);
        } catch (Exception e) {
            e.printStackTrace();
            //异常处理
        }

    }

    @Override
    public void responseLoginResult(boolean loginStatusResult) {

        mView.responseLoginResult(loginStatusResult);
    }*/
}
