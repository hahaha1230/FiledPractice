package com.example.fieldpractice.ui.activity.login;

import android.util.Log;

import com.example.fieldpractice.http.RequestAPI;
import com.example.fieldpractice.application.MyApplication;
import com.example.fieldpractice.base.BaseBean;
import com.example.fieldpractice.base.BaseModel;
import com.example.fieldpractice.javabean.UsersInfoTb;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import com.example.fieldpractice.http.RetrofitRequestManager;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by JG on 2019/5/3.
 */

public class LoginModel extends BaseModel<LoginPresenter,ILogin.M> {

    //public static final String URL="192.168.1.12:3001";
    //public static final String URL="192.168.1.12:3001/Ischecklogin";
    private Gson gson;
    private GsonBuilder gsonBuilder;
    LoginLoader loginLoader;
    private MyApplication mApplication;

    public LoginModel(LoginPresenter loginPresenter) {
        super(loginPresenter);
        //mApplication=(MyApplication)getApplication();
        gsonBuilder = new GsonBuilder();
        gson = gsonBuilder.create();
    }


    @Override
    public ILogin.M getContract() {
        return new ILogin.M() {
            @Override
            public void requestLogin(String name, String pwd) {//throws Exception
                RequestAPI requestAPI= RetrofitRequestManager.getManager().create(RequestAPI.class);
                Observable<BaseBean<UsersInfoTb>>observable=requestAPI.verify(name,pwd);
                observable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<BaseBean<UsersInfoTb>>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                Log.d("hhh","请求onSubscribe");
                            }

                            @Override
                            public void onNext(BaseBean<UsersInfoTb> usersInfoTbBaseBean) {
                                Log.d("hhh","请求on" +
                                        " text");
                                mPresenter.getModelInstance().mApplication.userLogin(usersInfoTbBaseBean.getMessage());
                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.d("hhh","请求异常");
                            }

                            @Override
                            public void onComplete() {

                                Log.d("hhh","请求完成");
                            }
                        });

                loginLoader=new LoginLoader();

               /* loginLoader.getResult(name, pwd)
                            .subscribe(new Consumer<BaseBean<UsersInfoTb>>() {
                                @Override
                                public void accept(BaseBean<UsersInfoTb> usersInfoTbBaseBean) throws Exception {
                                    Log.d("hhh","进入accept方法里面");
                                    if (usersInfoTbBaseBean.message != null) {
                                        //登陆成功
                                        mPresenter.getContract().responseLoginResult(true);
                                    } else {
                                        //登陆失败
                                        mPresenter.getContract().responseLoginResult(false);
                                    }
                                }
                            });*/
                loginLoader.getResult(name, pwd)
                        .subscribe(new Observer<BaseBean<UsersInfoTb>>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(BaseBean<UsersInfoTb> usersInfoTbBaseBean) {

                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onComplete() {

                            }
                        });

                jiandanceshi(name,pwd);

            }

        };
    }
  private  Subscription subscription;





    private void jiandanceshi(String name,String pwd)
    {
        String url="";
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        RequestAPI requestAPI=retrofit.create(RequestAPI.class);
        Subscriber <BaseBean<RequestAPI>> aa=new Subscriber<BaseBean<RequestAPI>>()
    {
        @Override
        public void onSubscribe(Subscription s) {
            Log.d("hhh","你1");
        }

        @Override
        public void onNext(BaseBean<RequestAPI> requestAPIBaseBean) {
            Log.d("hhh","你1");
        }

        @Override
        public void onError(Throwable t) {
            Log.d("hhh","你1");
        }

        @Override
        public void onComplete() {
            Log.d("hhh","你1");
        }

    };
     /*  subscription=requestAPI.verify(name,pwd)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aa);*/



    }


}
