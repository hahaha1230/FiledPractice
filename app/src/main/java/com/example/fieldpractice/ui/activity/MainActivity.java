package com.example.fieldpractice.ui.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.example.fieldpractice.R;
import com.example.fieldpractice.base.BaseActivity;
import com.example.fieldpractice.contract.MainContract;
import com.example.fieldpractice.model.db.UsersInfo;
import com.example.fieldpractice.presenter.MainPresenter;
import com.example.fieldpractice.ui.fragment.MapFragment;
import com.example.fieldpractice.ui.fragment.MeFragment;
import com.example.fieldpractice.ui.fragment.MessageFragment;
import com.example.fieldpractice.ui.fragment.TaskFragment;
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


public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.View, View.OnClickListener {
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
    public void testGetMview() {
        Log.d("print", "我是V层的引用");
    }

    @Override
    protected void initPresenter() {
        mPresenter = new MainPresenter();
    }

    @Override
    protected void initView() {
        unbinder = ButterKnife.bind(this);

        initEvent();
        setSelect(0);

        getData();

       // ceshi();
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
        }).map(new Function<Response, UsersInfo>() {
            @Override
            public UsersInfo apply(Response response) throws Exception {
                if (response.isSuccessful())
                {
                   ResponseBody body=response.body();
                   if (body!=null)
                   {
                       Log.d(TAG,"转换前："+response.body());
                       return new Gson().fromJson(body.string(), UsersInfo.class);

                   }

                }
                return null;
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .doOnNext(new Consumer<UsersInfo>() {
                    @Override
                    public void accept(UsersInfo usersInfo) throws Exception {
                       Log.d(TAG, "doOnNext 线程:" + Thread.currentThread().getName() + "\n");
                        //mRxOperatorsText.append("\ndoOnNext 线程:" + Thread.currentThread().getName() + "\n");
                        //Log.e(TAG, "doOnNext: 保存成功：" +us.toString() + "\n");
                       // mRxOperatorsText.append("doOnNext: 保存成功：" + s.toString() + "\n");
                    }
                }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<UsersInfo>() {
                    @Override
                    public void accept(UsersInfo usersInfo) throws Exception {
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

    private void requestData() {

        //trim = et_search.getText().toString().trim();
        //String path = "http://93.gov.cn/93app/get_search.do?key=" + trim;
        //String path = "http://93.gov.cn/93app/get_search.do?key="
        String path = "192.168.1.12:3001/Ischecklogin";

        MyAsyncTask task = new MyAsyncTask();
        task.execute(path);

      /*  String result = null;
        HttpClient client = HttpClients.createDefault();
        URIBuilder builder = new URIBuilder();
        URI uri = null;
        try {
            uri = builder.setScheme("http")
                    .setHost("xxx.xxx.xxx.xxx:xxxx")
                    .setPath("/api/authorize/login")
                    .build();

            HttpPost post = new HttpPost(uri);
            //设置请求头
            post.setHeader("Content-Type", "application/json");
            String body = "{\"Key\": \"\",\"Secret\": \"\"}";
            //设置请求体
            post.setEntity(new StringEntity(body));
            //获取返回信息
            HttpResponse response = client.execute(post);
            result = response.toString();
        } catch (Exception e) {
            System.out.println("接口请求失败"+e.getStackTrace());
        }
        System.out.println(result);
*/

    }


    /**
     * 初始化界面的事件
     */
    private void initEvent() {
        tab_task.setOnClickListener(this);
        tab_map.setOnClickListener(this);
        tab_message.setOnClickListener(this);
        tab_me.setOnClickListener(this);
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
    protected void onDestroy() {
        unbinder.unbind();
        super.onDestroy();
    }

}
