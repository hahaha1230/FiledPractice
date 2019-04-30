package com.example.fieldpractice.base;

/**
 * Created by JG on 2019/4/19.
 */

public interface IBasePresenter<V extends IBaseView> {
    /**
     * 绑定view
     * @param view
     */
    void attachView(V view);
}
