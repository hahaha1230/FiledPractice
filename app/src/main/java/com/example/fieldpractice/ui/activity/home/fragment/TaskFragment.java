package com.example.fieldpractice.ui.activity.home.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.fieldpractice.R;
import com.example.fieldpractice.base.BaseFragment;
import com.example.fieldpractice.javabean.TaskTb;
import com.example.fieldpractice.ui.activity.home.adapter.TaskAdapter;
import com.example.fieldpractice.ui.activity.newtask.NewTaskActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by JG on 2019/4/24.
 */

public class TaskFragment extends BaseFragment{

    private TaskAdapter taskAdapter;
    private ArrayList<TaskTb> tasksList = new ArrayList<TaskTb>();

    @BindView(R.id.task_recyclerView)
    RecyclerView  taskRecyclerView;

    @BindView(R.id.add_floating_action_button)
    FloatingActionButton fab;



    @Override
    public void initRecyclerView() {
        taskAdapter=new TaskAdapter(getActivity(),tasksList);
        taskRecyclerView.setAdapter(taskAdapter);

        taskRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL,false));

        taskAdapter.setOnItemClickListener(new TaskAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, TaskTb taskTb) {
                Toast.makeText(getContext(),"未实现功能",Toast.LENGTH_SHORT).show();
            }
        });

    }


    @Override
    public void initView() {
        Log.d("hhh","initview");
    }

    @Override
    public void initData() {
        for (int i=0;i<10;i++){
            TaskTb taskTb=new TaskTb();
            taskTb.setInitiator(i*8);
            taskTb.setTaskName("任务名称"+i);
            tasksList.add(taskTb);
        }
    }


    @Override
    public void initListener() {
        fab.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.add_floating_action_button:
               Intent intent=new Intent(getContext(), NewTaskActivity.class);
                startActivity(intent);
                break;
        }
    }


    @Override
    protected int getLayoutId() {
        return R.layout.tab_task_layout;
    }

}
