package com.example.fieldpractice.ui.activity.home;

import com.example.fieldpractice.base.BasePresenter;

/**
 * Created by JG on 2019/5/4.
 */

public class HomePresenter extends BasePresenter <HomeActivity,HomeModel,IHome.P>{

    @Override
    public HomeModel getModelInstance() {
        return new HomeModel(this);
    }

    @Override
    public IHome.P getContract() {
        return new IHome.P() {
            @Override
            public void requestUserInfo(String userID) {
                mModel.getContract().requestUserInfo(userID);
            }
        };
    }
}
