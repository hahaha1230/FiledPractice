package com.example.fieldpractice.ui.activity.home;

import com.example.fieldpractice.base.BaseModel;

/**
 * Created by JG on 2019/5/4.
 */

public class HomeModel extends BaseModel<HomePresenter,IHome.M>{

    @Override
    public IHome.M getContract() {
        return new IHome.M() {
            @Override
            public void requestUserInfo(String userID) {
                getUserInfo(userID);
                //将服务器返回的结果进行解析并传递给mpresenter，然后传递给homeactivity
              //  mPresenter.getContract().requestUserInfo();
            }
        };
    }


    public HomeModel(HomePresenter homePresenter)
    {
        super(homePresenter);
    }


    private void getUserInfo(String userId)
    {

    }
}
