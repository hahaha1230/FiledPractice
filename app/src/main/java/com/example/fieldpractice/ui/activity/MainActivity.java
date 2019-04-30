package com.example.fieldpractice.ui.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.example.fieldpractice.R;
import com.example.fieldpractice.base.BaseActivity;
import com.example.fieldpractice.contract.MainContract;
import com.example.fieldpractice.presenter.MainPresenter;
import com.example.fieldpractice.ui.fragment.MapFragment;
import com.example.fieldpractice.ui.fragment.MeFragment;
import com.example.fieldpractice.ui.fragment.MessageFragment;
import com.example.fieldpractice.ui.fragment.TaskFragment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.View, View.OnClickListener {



    @BindView(R.id.home_frame_layout)
    FrameLayout home_frame_layout;

    //tab
    @BindView(R.id.id_tab_me)
    LinearLayout tab_me;
    @BindView(R.id.id_tab_message)
    LinearLayout tab_message;
    @BindView(R.id.id_tab_task)
    LinearLayout tab_task;
    @BindView(R.id.id_tab_map)
    LinearLayout tab_map;

    //image button
    @BindView(R.id.id_tab_me_image)
    ImageButton tab_me_image;
    @BindView(R.id.id_tab_message_image)
    ImageButton tab_message_image;
    @BindView(R.id.id_tab_map_image)
    ImageButton tab_test_image;
    @BindView(R.id.id_tab_task_image)
    ImageButton tab_task_image;



    private Unbinder unbinder;
    private Fragment taskFragment;
    private Fragment mapFragment;
    private Fragment messageFragment;
    private Fragment meFragment;


    @Override
    public void testGetMview() {
        Log.d("print", "我是V层的引用");
    }

    @Override
    protected void initPresenter() {
        mPresenter = new MainPresenter();
    }

    @Override
    protected void initView() {
        unbinder = ButterKnife.bind(this);

        initEvent();

        setSelect(0);

       /* new Thread(){
            public  void run(){
                try {
                    Class.forName("com.mysql.jdbc.Driver");//动态加载类
                    String url = "jdbc:mysql://localhost:3306/internship?autoReconnect=true";
                    //上面语句中 mysql://mysql.lianfangti.top为你的mysql服务器地址 3306为端口号   public是你的数据库名 根据你的实际情况更改
                    Connection conn = DriverManager.getConnection(url, "root", "123456");//使用 DriverManger.getConnection链接数据库  第一个参数为连接地址 第二个参数为用户名 第三个参数为连接密码  返回一个Connection对象
                    if(conn!=null){ //判断 如果返回不为空则说明链接成功 如果为null的话则连接失败 请检查你的 mysql服务器地址是否可用 以及数据库名是否正确 并且 用户名跟密码是否正确
                        Log.d("hhh","连接成功");
                        Statement stmt = conn.createStatement(); //根据返回的Connection对象创建 Statement对象
                        String sql = "select * from internship"; //要执行的sql语句
                        ResultSet rs = stmt.executeQuery(sql); //使用executeQury方法执行sql语句 返回ResultSet对象 即查询的结果
                    }else{
                        Log.d("hhh","连接失败");
                    }
                } catch (ClassNotFoundException e) {
                    Log.d("hhh","连接失败2"+e.getMessage());
                    e.printStackTrace();
                } catch (SQLException e) {
                    Log.d("hhh","连接失败3"+e.getMessage());
                    e.printStackTrace();
                }
            }
        }.start();
*/



      /*  try {

           DBService dbService=new DBService();
           dbService.updateUserData("5555555555");
        }
        catch (Exception e)
        {
            Log.d("hhh","出错了"+e.getMessage());
        }*/
    }

    /**
     * 初始化界面的事件
     */
    private void initEvent() {
        tab_task.setOnClickListener(this);
        tab_map.setOnClickListener(this);
        tab_message.setOnClickListener(this);
        tab_me.setOnClickListener(this);
    }


    /**
     * 选择fragment进行显示
     * @param selected
     */
    private void setSelect(int selected)
    {
        FragmentManager fm=getSupportFragmentManager();
        FragmentTransaction transaction=fm.beginTransaction();
        hideFragment(transaction);
        switch (selected)
        {
            case 0:
                tab_task_image.setImageResource(R.mipmap.task_checked);
                if (taskFragment==null)
                {
                    taskFragment=new TaskFragment();
                    transaction.add(R.id.home_frame_layout,taskFragment);
                }
                else {
                    transaction.show(taskFragment);
                }
                break;
            case 1:
                tab_test_image.setImageResource(R.mipmap.map_checked);
                if (mapFragment==null)
                {

                    mapFragment=MapFragment.newInstance();
                    transaction.add(R.id.home_frame_layout,mapFragment);
                }
                else {
                    transaction.show(mapFragment);
                }
                break;
            case 2:
                tab_message_image.setImageResource(R.mipmap.message_checked);
                if (messageFragment==null)
                {
                    messageFragment=new MessageFragment();
                    transaction.add(R.id.home_frame_layout,messageFragment);
                }
                else {
                    transaction.show(messageFragment);
                }
                break;
            case 3:
                tab_me_image.setImageResource(R.mipmap.me_checked);
                if (meFragment==null)
                {
                    meFragment=new MeFragment();
                    transaction.add(R.id.home_frame_layout,meFragment);
                }
                else {
                    transaction.show(meFragment);
                }
                break;
        }
        //提交事务
        transaction.commit();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.id_tab_task:
                resetImg();
                setSelect(0);
                break;
            case R.id.id_tab_map:
                resetImg();
                setSelect(1);
                break;
            case R.id.id_tab_message:
                resetImg();
                setSelect(2);
                break;
            case R.id.id_tab_me:
                resetImg();
                setSelect(3);
                break;
        }
    }

    /**
     * 隐藏所有fragment
     * @param transaction
     */
    private void hideFragment(FragmentTransaction transaction) {
        if (taskFragment!=null)
        {
            transaction.hide(taskFragment);
        }
        if (mapFragment!=null)
        {
            transaction.hide(mapFragment);
        }
        if (messageFragment!=null)
        {
            transaction.hide(messageFragment);
        }
        if (meFragment!=null)
        {
            transaction.hide(meFragment);
        }
    }

    /**
     * 将所有照片初始化
     */
    private void resetImg()
    {
        tab_task_image.setImageResource(R.mipmap.task);
        tab_test_image.setImageResource(R.mipmap.map);
        tab_message_image.setImageResource(R.mipmap.message);
        tab_me_image.setImageResource(R.mipmap.me);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.homelayout;
    }

    @Override
    protected void onDestroy() {
        unbinder.unbind();
        super.onDestroy();
    }
}
