package com.example.fieldpractice.ui.activity.home;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.example.fieldpractice.R;
import com.example.fieldpractice.base.BaseActivity;
import com.example.fieldpractice.javabean.UsersInfoTb;
import com.example.fieldpractice.ui.activity.home.fragment.MapFragment;
import com.example.fieldpractice.ui.activity.home.fragment.MeFragment;
import com.example.fieldpractice.ui.activity.home.fragment.MessageFragment;
import com.example.fieldpractice.ui.activity.home.fragment.TaskFragment;
import com.google.gson.Gson;


import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;

import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Request.Builder;
import okhttp3.Response;
import okhttp3.ResponseBody;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


public class HomeActivity extends BaseActivity<HomePresenter,IHome.V>  {

    @BindView(R.id.home_frame_layout)
    FrameLayout home_frame_layout;
    //tab
    @BindView(R.id.id_tab_me)
    LinearLayout tab_me;
    @BindView(R.id.id_tab_message)
    LinearLayout tab_message;
    @BindView(R.id.id_tab_task)
    LinearLayout tab_task;
    @BindView(R.id.id_tab_map)
    LinearLayout tab_map;
    //image button
    @BindView(R.id.id_tab_me_image)
    ImageButton tab_me_image;
    @BindView(R.id.id_tab_message_image)
    ImageButton tab_message_image;
    @BindView(R.id.id_tab_map_image)
    ImageButton tab_test_image;
    @BindView(R.id.id_tab_task_image)
    ImageButton tab_task_image;


    private Unbinder unbinder;
    private Fragment taskFragment;
    private Fragment mapFragment;
    private Fragment messageFragment;
    private Fragment meFragment;
    private static final String TAG="hhh";


    @Override
    public HomePresenter getPresenterInstance() {
        return new HomePresenter();
    }

    @Override
    public void initData() {
        getData();
    }

    @Override
    public void initListener() {
        tab_task.setOnClickListener(this);
        tab_map.setOnClickListener(this);
        tab_message.setOnClickListener(this);
        tab_me.setOnClickListener(this);
    }

    @Override
    public <ERROR> void responseError(ERROR error, Throwable throwable) {
        Log.d("hhh",getClass().getSimpleName()+"异常:"+error);
    }




    @Override
    public void initView() {
        unbinder = ButterKnife.bind(this);
        setSelect(0);
    }


    @Override
    public IHome.V getContract() {
        return new IHome.V() {
            @Override
            public void requestUserInfo(String userID) {
                mPresenter.getContract().requestUserInfo(userID);
            }
        };
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.id_tab_task:
                resetImg();
                setSelect(0);
                break;
            case R.id.id_tab_map:
                resetImg();
                setSelect(1);
                break;
            case R.id.id_tab_message:
                resetImg();
                setSelect(2);
                break;
            case R.id.id_tab_me:
                resetImg();
                setSelect(3);
                break;
        }
    }

    private void getData()
    {
        Observable.create(new ObservableOnSubscribe<Response>() {
            @Override
            public void subscribe(ObservableEmitter<Response> e) throws Exception {
                Builder builder=new Builder()
                        .url("192.168.1.12:3001/Ischecklogin")
                        .get();
                Request request=builder.build();
                Call call=new OkHttpClient().newCall(request);
                Response response=call.execute();
                e.onNext(response);
            }
        }).map(new Function<Response, UsersInfoTb>() {
            @Override
            public UsersInfoTb apply(Response response) throws Exception {
                if (response.isSuccessful())
                {
                   ResponseBody body=response.body();
                   if (body!=null)
                   {
                       Log.d(TAG,"转换前："+response.body());
                       return new Gson().fromJson(body.string(), UsersInfoTb.class);

                   }

                }
                return null;
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .doOnNext(new Consumer<UsersInfoTb>() {
                    @Override
                    public void accept(UsersInfoTb usersInfo) throws Exception {
                       Log.d(TAG, "doOnNext 线程:" + Thread.currentThread().getName() + "\n");
                        //mRxOperatorsText.append("\ndoOnNext 线程:" + Thread.currentThread().getName() + "\n");
                        //Log.e(TAG, "doOnNext: 保存成功：" +us.toString() + "\n");
                       // mRxOperatorsText.append("doOnNext: 保存成功：" + s.toString() + "\n");
                    }
                }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<UsersInfoTb>() {
                    @Override
                    public void accept(UsersInfoTb usersInfo) throws Exception {
                        Log.d(TAG, "subscribe 线程:" + Thread.currentThread().getName() + "\n");
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@io.reactivex.annotations.NonNull Throwable throwable) throws Exception {
                        Log.e(TAG, "subscribe 线程:" + Thread.currentThread().getName() + "\n");
                        Log.e(TAG, "失败：" + throwable.getMessage() + "\n");
                    }
                });

    }

    private void ceshi() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                Log.d(TAG,"observable emit 1"+"\n");
                e.onNext(1);
                Log.d(TAG,"observable emit 2"+"\n");
                e.onNext(2);
                Log.d(TAG,"observable emit 3"+"\n");
                e.onNext(3);
                Log.d(TAG,"observable emit 4"+"\n");
                e.onNext(4);
                Log.d(TAG,"observable emit 5"+"\n");
                e.onNext(5);
                Log.d(TAG,"observable emit 6"+"\n");
                e.onNext(6);
            }
        }).subscribe(new Observer<Integer>() {
            private int i;
            private Disposable mDisposable;

            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG,"onSubscribe : " + d.isDisposed() + "\n");
                mDisposable = d;
            }

            @Override
            public void onNext(Integer integer) {
                Log.d(TAG,"onNext : value : " + integer + "\n");
                i++;
                if (i == 2) {
                    // 在RxJava 2.x 中，新增的Disposable可以做到切断的操作，让Observer观察者不再接收上游事件
                    mDisposable.dispose();
                    Log.d(TAG,"onNext : isDisposable : " + mDisposable.isDisposed() + "\n");
                    Log.d(TAG, "onNext : isDisposable : " + mDisposable.isDisposed() + "\n");
                }

            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG,"onError : value : " + e.getMessage() + "\n");
            }

            @Override
            public void onComplete() {
                Log.d(TAG,"onComplete" + "\n");

            }
        });
    }





    /**
     * 选择fragment进行显示
     * @param selected
     */
    private void setSelect(int selected)
    {
        FragmentManager fm=getSupportFragmentManager();
        FragmentTransaction transaction=fm.beginTransaction();
        hideFragment(transaction);
        switch (selected)
        {
            case 0:
                tab_task_image.setImageResource(R.mipmap.task_checked);
                if (taskFragment==null)
                {
                    taskFragment=new TaskFragment();
                    transaction.add(R.id.home_frame_layout,taskFragment);
                }
                else {
                    transaction.show(taskFragment);
                }
                break;
            case 1:
                tab_test_image.setImageResource(R.mipmap.map_checked);
                if (mapFragment==null)
                {
                    mapFragment=new MapFragment();
                    transaction.add(R.id.home_frame_layout,mapFragment);
                }
                else {
                    transaction.show(mapFragment);
                }
                break;
            case 2:
                tab_message_image.setImageResource(R.mipmap.message_checked);
                if (messageFragment==null)
                {
                    messageFragment=new MessageFragment();
                    transaction.add(R.id.home_frame_layout,messageFragment);
                }
                else {
                    transaction.show(messageFragment);
                }
                break;
            case 3:
                tab_me_image.setImageResource(R.mipmap.me_checked);
                if (meFragment==null)
                {
                    meFragment=new MeFragment();
                    transaction.add(R.id.home_frame_layout,meFragment);
                }
                else {
                    transaction.show(meFragment);
                }
                break;
        }
        //提交事务
        transaction.commit();

    }



    /**
     * 隐藏所有fragment
     * @param transaction
     */
    private void hideFragment(FragmentTransaction transaction) {
        if (taskFragment!=null)
        {
            transaction.hide(taskFragment);
        }
        if (mapFragment!=null)
        {
            transaction.hide(mapFragment);
        }
        if (messageFragment!=null)
        {
            transaction.hide(messageFragment);
        }
        if (meFragment!=null)
        {
            transaction.hide(meFragment);
        }
    }

    /**
     * 将所有照片初始化
     */
    private void resetImg()
    {
        tab_task_image.setImageResource(R.mipmap.task);
        tab_test_image.setImageResource(R.mipmap.map);
        tab_message_image.setImageResource(R.mipmap.message);
        tab_me_image.setImageResource(R.mipmap.me);
    }


    @Override
    protected int getLayoutId() {
        return R.layout.homelayout;
    }

    @Override
    public void destroy() {
        unbinder.unbind();
        super.onDestroy();
    }


}
