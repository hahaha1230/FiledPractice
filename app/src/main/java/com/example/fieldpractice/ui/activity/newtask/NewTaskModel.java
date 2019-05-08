package com.example.fieldpractice.ui.activity.newtask;

import com.example.fieldpractice.base.BaseModel;

/**
 * Created by JG on 2019/5/6.
 */

public class NewTaskModel extends BaseModel<NewTaskPresenter,INewTask.M> {


    public NewTaskModel(NewTaskPresenter newTaskPresenter)
    {
        super(newTaskPresenter);
    }


    @Override
    public INewTask.M getContract() {
        return null;
    }
}
