package com.example.fieldpractice.home.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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

import java.util.ArrayList;

/**
 * Created by JG on 2019/4/24.
 */

public class TaskFragment extends BaseFragment{


    private View view;//定义view用来设置fragment的layou
    private RecyclerView taskRecyclerView;
    private TaskAdapter taskAdapter;
    private ArrayList<TaskTb> tasksList = new ArrayList<TaskTb>();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.tab_task_layout,container,false);

        initRecyclerView();
        initData();
        return view;
    }

    @Override
    public void initRecyclerView() {
        taskRecyclerView=(RecyclerView)view.findViewById(R.id.task_recyclerView);
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
}
