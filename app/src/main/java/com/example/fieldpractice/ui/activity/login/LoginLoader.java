package com.example.fieldpractice.ui.activity.login;

import com.example.fieldpractice.http.RequestAPI;
import com.example.fieldpractice.base.BaseBean;
import com.example.fieldpractice.base.ObjectLoader;
import com.example.fieldpractice.javabean.UsersInfoTb;
import com.example.fieldpractice.http.RetrofitRequestManager;

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

    public Observable<BaseBean<UsersInfoTb>>getResult(String name, String pwd)
    {
        return observe(requestAPI.verify(name,pwd))
                .map(new Function<BaseBean<UsersInfoTb>, BaseBean<UsersInfoTb>>() {
                    @Override
                    public BaseBean<UsersInfoTb> apply(BaseBean<UsersInfoTb> usersInfoTbBaseBean) throws Exception {
                        return usersInfoTbBaseBean;
                    }
                });
    }





}
