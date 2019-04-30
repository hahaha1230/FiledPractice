package com.example.fieldpractice.base;

import com.example.fieldpractice.model.db.APPDbHelper;
import com.example.fieldpractice.model.db.DataManager;
import com.example.fieldpractice.model.db.DbHelper;
import com.example.fieldpractice.model.http.ApiHelper;
import com.example.fieldpractice.model.http.AppApiHelper;
import com.example.fieldpractice.model.pregerence.AppPreferenceHelper;
import com.example.fieldpractice.model.pregerence.PreferenceHelper;

/**
 * Created by JG on 2019/4/19.
 */

public abstract class BasePresenter<V extends IBaseView> implements IBasePresenter<V> {

    protected DataManager mDataManager;
    protected V mView;

    public BasePresenter(){
        APPDbHelper appDbHelper=new DbHelper();
        AppPreferenceHelper appPreferenceHelper=new PreferenceHelper();
        AppApiHelper appApiHelper=new ApiHelper();
        mDataManager=new DataManager(appDbHelper,appApiHelper,appPreferenceHelper);

    }

    @Override
    public void attachView(V view) {
        this.mView=view;
    }
}
