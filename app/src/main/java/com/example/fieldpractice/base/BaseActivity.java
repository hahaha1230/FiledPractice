package com.example.fieldpractice.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by JG on 2019/4/19.
 */

public abstract class BaseActivity<P extends BasePresenter,CONTRACT> extends  AppCompatActivity
        implements View.OnClickListener {

    protected P mPresenter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());

        //初始化
        initView();
        initData();
        initListener();

        //初始化presenter
        mPresenter=getPresenterInstance();
        mPresenter.bindView(this);
    }

    /**
     * 接口的契约
     * @return
     */
    public abstract CONTRACT getContract();


    /**
     * 让子类初始化Presenter
     */
    public abstract P getPresenterInstance();

    /**
     * 初始化界面
     */
    public abstract void initView();


    /**
     * 初始化数据
     */
    public abstract void initData();

    /**
     * 获取布局id
     * @return
     */
    protected abstract int getLayoutId();


    /**
     * 初始化监听
     */
    public abstract void initListener();



    /**
     * 处理响应实物异常
     */
    public abstract <ERROR extends Object> void responseError(ERROR error,Throwable throwable);


    @Override
    protected void onDestroy() {
        super.onDestroy();
        destroy();
    }

    public abstract void destroy();
}
