package com.example.fieldpractice.ui.activity.newtask;

import com.example.fieldpractice.base.BasePresenter;

/**
 * Created by JG on 2019/5/6.
 */

public class NewTaskPresenter extends BasePresenter<NewTaskActivity,NewTaskModel,INewTask.P> {

    @Override
    public INewTask.P getContract() {
        return null;
    }

    @Override
    public NewTaskModel getModelInstance() {
        return new NewTaskModel(this);
    }
}
