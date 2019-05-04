package com.example.fieldpractice.home.fragment;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.fieldpractice.R;
import com.example.fieldpractice.javabean.TaskTb;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by JG on 2019/5/4.
 */

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<TaskTb> tasksList;
    //需要外部访问，所以需要设置set方法，方便调用
    private OnItemClickListener onItemClickListener;


    public TaskAdapter(Context context,ArrayList<TaskTb> tasksList){
        this.context=context;
        this.tasksList=tasksList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = View.inflate(context, R.layout.task_item_layout, null);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        TaskTb data = tasksList.get(position);


        holder.taskName.setText(data.getTaskName());
        holder.teacherName.setText("v"+data.getInitiator());


    }

    @Override
    public int getItemCount() {
        return tasksList.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {
        private  CircleImageView teacherIcon;
        private TextView taskName;
        private TextView teacherName;



        public MyViewHolder(View itemView)
        {
            super(itemView);
            teacherIcon=(CircleImageView)itemView.findViewById(R.id.user_icon);
            taskName=(TextView)itemView.findViewById(R.id.task_name);
            teacherName=(TextView)itemView.findViewById(R.id.teacher_name);




            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(onItemClickListener!=null){
                        onItemClickListener.OnItemClick(v, tasksList.get(getLayoutPosition()));
                    }


                }
            });
        }

    }
    /**
     * 设置item的监听事件的接口
     */
    public interface OnItemClickListener {
        /**
         * 接口中的点击每一项的实现方法，参数自己定义
         *
         * @param view 点击的item的视图
         *  点击的item的数据
         */
        void OnItemClick(View view, TaskTb taskTb);
    }



    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }




    }
