package com.example.fieldpractice.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Created by JG on 2019/5/3.
 */

public abstract class BaseFragment extends Fragment implements View.OnClickListener {

    protected View view;


    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        view=inflater.inflate(getLayoutId(),container,false);
        ButterKnife.bind(this,view);

        initView();

        initRecyclerView();

        initListener();

        initData();

        return view;
    }

    /**
     * 获取布局id
     * @return
     */
    protected abstract int getLayoutId();


    public abstract void initRecyclerView();

    public abstract void initView();

    public abstract void initData();


    /**
     * 初始化监听
     */
    public abstract void initListener();


}
