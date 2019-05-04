package com.example.fieldpractice.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by JG on 2019/5/3.
 */

public abstract class BaseFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        initView();
        initData();

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    public abstract void initRecyclerView();

    public abstract void initView();


    public abstract void initData();

}
