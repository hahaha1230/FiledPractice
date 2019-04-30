package com.example.fieldpractice.presenter;

import android.util.Log;

import com.example.fieldpractice.base.BasePresenter;
import com.example.fieldpractice.contract.MainContract;

/**
 * Created by JG on 2019/4/19.
 */

public class MainPresenter extends BasePresenter<MainContract.View>implements MainContract.Presenter{
    @Override
    public void testGetMpresenter() {
        Log.d("hhh", "我是P层的引用");
        mView.testGetMview();
    }

    @Override
    public void testDb() {
        mDataManager.testDb();
    }

    @Override
    public void testRequestNetwork() {
        mDataManager.testRequestNetwork();
    }

    @Override
    public void testPreference() {
        mDataManager.testPreference();
    }
}
