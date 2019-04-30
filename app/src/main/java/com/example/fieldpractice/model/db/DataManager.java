package com.example.fieldpractice.model.db;

import com.example.fieldpractice.model.http.AppApiHelper;
import com.example.fieldpractice.model.pregerence.AppPreferenceHelper;

/**
 * Created by JG on 2019/4/19.
 */

public class DataManager implements APPDbHelper,AppApiHelper,AppPreferenceHelper {
    private APPDbHelper mAppDbHelper;
    private AppApiHelper mAppApiHelper;
    private AppPreferenceHelper mAppPreferenceHelper;

    public DataManager(APPDbHelper mAppDbHelper, AppApiHelper appApiHelper, AppPreferenceHelper appPreferenceHelper) {
        this.mAppDbHelper = mAppDbHelper;
        this.mAppApiHelper = appApiHelper;
        this.mAppPreferenceHelper = appPreferenceHelper;
    }

    @Override
    public void testDb() {
        mAppDbHelper.testDb();
    }

    @Override
    public void testRequestNetwork() {
        mAppApiHelper.testRequestNetwork();
    }

    @Override
    public void testPreference() {
        mAppPreferenceHelper.testPreference();
    }
}
