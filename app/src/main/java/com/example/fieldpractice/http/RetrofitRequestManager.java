package com.example.fieldpractice.http;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by JG on 2019/5/7.
 */

public class RetrofitRequestManager {
   // private static final String BASE_URL = "192.168.1.12:3001/Ischecklogin";"http://192.168.1.9/:3001/Ischecklogin"
    private static final String BASE_URL = "http://192.168.1.105:3001/";
    private static final int DEFAULT_TIME_OUT = 5;//超时时间 5s
    private static final int DEFAULT_READ_TIME_OUT = 10;

    private Retrofit retrofit;

    private RequestAPI requestAPI;

    private RetrofitRequestManager(){
        OkHttpClient.Builder builder=new OkHttpClient.Builder();
        builder.connectTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS);
        builder.writeTimeout(DEFAULT_READ_TIME_OUT,TimeUnit.SECONDS);//写操作 超时时间
        builder.readTimeout(DEFAULT_READ_TIME_OUT,TimeUnit.SECONDS);//读操作超时时间


       /* HttpCommonInterceptor commonInterceptor = new HttpCommonInterceptor.Builder()
                .addHeaderParams("paltform","android")
                .addHeaderParams("userToken","1234343434dfdfd3434")
                .addHeaderParams("userId","123445")
                .build();

        builder.addInterceptor(commonInterceptor);*/

        retrofit=new Retrofit.Builder()
                .client(builder.build())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build();


        requestAPI=create(RequestAPI.class);
    }


    //在访问HttpMethods时创建单例
    private static class SingletonHolder{
        private static final RetrofitRequestManager INSTANCE = new RetrofitRequestManager();
    }

    //获取单例
    public static RetrofitRequestManager getManager(){
        return SingletonHolder.INSTANCE;
    }


    public <T> T create(Class<T>service)
    {
        return retrofit.create(service);
    }


    public RequestAPI getAPI(){
        return requestAPI;
    }
}
