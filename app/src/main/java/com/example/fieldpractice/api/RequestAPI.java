package com.example.fieldpractice.api;

import com.example.fieldpractice.javabean.UsersInfoTb;
import com.example.fieldpractice.javabean.VerifyUserLogin;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by JG on 2019/5/7.
 */

public interface RequestAPI {


    @FormUrlEncoded
    @POST("Ischecklogin")
    Observable<VerifyUserLogin> verify(@Field("userName") String userName, @Field("pwd") String pwd);


}
