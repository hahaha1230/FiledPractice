package com.example.fieldpractice.base;

/**
 * Created by JG on 2019/5/3.
 */

public abstract class BaseModel<P extends BasePresenter,CONTRACT> extends SuperBase<CONTRACT> {


    public P mPresenter;

    public BaseModel(P mPresenter)
    {
        this.mPresenter=mPresenter;
    }
}
