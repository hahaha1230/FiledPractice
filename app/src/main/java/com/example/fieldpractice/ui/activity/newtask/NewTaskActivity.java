package com.example.fieldpractice.ui.activity.newtask;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.fieldpractice.R;
import com.example.fieldpractice.base.BaseActivity;
import com.example.fieldpractice.base.BasePresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by JG on 2019/5/6.
 */

public class NewTaskActivity extends BaseActivity<BasePresenter,INewTask.V> {

    @BindView(R.id.et_task_name)
    EditText etTaskName;

    @BindView(R.id.et_task_from_time)
    EditText etTaskFromTime;

    @BindView(R.id.et_task_end_time)
    EditText etTaskEndTime;

    @BindView(R.id.et_task_location)
    EditText etTaskLocation;

    @BindView(R.id.et_task_main_mission)
    EditText etTaskMainMission;

    @BindView(R.id.bt_commit)
    Button btCommit;


    @Override
    public INewTask.V getContract() {
        return null;
    }

    @Override
    public BasePresenter getPresenterInstance() {
        return new NewTaskPresenter();
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
    }

    @Override
    public void initData() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.new_task_layout;
    }

    @Override
    public void initListener() {
        etTaskEndTime.setOnClickListener(this);
        etTaskFromTime.setOnClickListener(this);
        btCommit.setOnClickListener(this);
    }

    @Override
    public void responseError(Object o, Throwable throwable) {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.et_task_from_time:
                break;
            case R.id.et_task_end_time:
                break;
            case R.id.bt_commit:
                break;
                default:
                    break;
        }


    }
}
