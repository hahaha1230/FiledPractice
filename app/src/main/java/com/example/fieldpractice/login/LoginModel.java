package com.example.fieldpractice.login;

import com.example.fieldpractice.login.ILogin;
import com.example.fieldpractice.base.BaseModel;
import com.example.fieldpractice.login.LoginPresenter;

/**
 * Created by JG on 2019/5/3.
 */

public class LoginModel extends BaseModel<LoginPresenter,ILogin.M> {

    public LoginModel(LoginPresenter loginPresenter)
    {
        super(loginPresenter);
    }

    @Override
    public ILogin.M getContract() {
        return new ILogin.M() {
            @Override
            public void requestLogin(String name, String pwd) throws Exception {

                if ("abc".equals(name) &&"123".equals(pwd))
                {
                    mPresenter.getContract().responseLoginResult(true);
                }
                else {
                    mPresenter.getContract().responseLoginResult(false);
                }
            }
        };
    }

}
