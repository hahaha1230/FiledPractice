package com.example.fieldpractice.http;

import com.example.fieldpractice.base.BaseBean;
import com.example.fieldpractice.javabean.UsersInfoTb;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by JG on 2019/5/7.
 */

public interface RequestAPI {



    //登录
    @FormUrlEncoded
    @POST("Ischecklogin")
    Observable<BaseBean<UsersInfoTb>> verify(@Field("userName") String userName, @Field("pwd") String pwd);
}
