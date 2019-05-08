package com.example.fieldpractice.ui.activity.login;

import com.example.fieldpractice.base.BaseModel;
import com.example.fieldpractice.javabean.VerifyUserLogin;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

/**
 * Created by JG on 2019/5/3.
 */

public class LoginModel extends BaseModel<LoginPresenter,ILogin.M> {

    public static final String URL="192.168.1.12:3001";
    //public static final String URL="192.168.1.12:3001/Ischecklogin";


    public LoginModel(LoginPresenter loginPresenter)
    {
        super(loginPresenter);
    }


    LoginLoader loginLoader;

    @Override
    public ILogin.M getContract() {
        return new ILogin.M() {
            @Override
            public void requestLogin(String name, String pwd) throws Exception {

            /*    RequestAPI requestAPI= RetrofitRequestManager.getManager().create(RequestAPI.class);

               RequestAPI api= RetrofitRequestManager.getManager().getAPI().verify(name,pwd)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Subscriber<>()
                        )
                }
                Retrofit retrofit=new Retrofit.Builder()
                        .baseUrl(URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .build();*/


               // UsersInfoTb usersInfoTb=retrofit.create(UsersInfoTb.class);
               /* Call<UsersInfoTb> call=verifyAccount.verify(name,pwd);


                //请求网络请求
                call.enqueue(new Callback<UsersInfoTb>() {
                    @Override
                    public void onResponse(Call<UsersInfoTb> call, Response<UsersInfoTb> response) {

                    }

                    @Override
                    public void onFailure(Call<UsersInfoTb> call, Throwable t) {

                    }
                });*/
               /* Subscription subscription =verifyAccount.verify(name,pwd)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Subscriber<UsersInfoTb>() {
                            @Override
                            public void onSubscribe(Subscription s) {

                            }

                            @Override
                            public void onNext(UsersInfoTb usersInfoTb) {

                            }

                            @Override
                            public void onError(Throwable t) {

                            }

                            @Override
                            public void onComplete() {

                            }
                        });*/


             loginLoader=new LoginLoader();
            //  loginLoader.getResult(name,pwd).subscribe(new Subscriber<VerifyUserLogin>());




            }
        };
    }
}
