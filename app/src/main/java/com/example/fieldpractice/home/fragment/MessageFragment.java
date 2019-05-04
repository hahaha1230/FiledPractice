package com.example.fieldpractice.home.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fieldpractice.R;
import com.example.fieldpractice.base.BaseFragment;

/**
 * Created by JG on 2019/4/24.
 */

public class MessageFragment extends BaseFragment{


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.tab_message_layout,container,false);
    }



    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void initRecyclerView() {

    }
}
