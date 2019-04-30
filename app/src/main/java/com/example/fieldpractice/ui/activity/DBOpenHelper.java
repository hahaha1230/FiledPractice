package com.example.fieldpractice.ui.activity;

import android.util.Log;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import butterknife.internal.Utils;

/**
 * Created by JG on 2019/4/24.
 */

public class DBOpenHelper {
    private static String driver = "com.mysql.jdbc.Driver";//MySQL 驱动
    //private static String driver = "org.gjt.mm.mysql.Driver";//MySQL 驱动com.mysql.cj.jdbc.Driver
    private static String url = "jdbc:mysql://localhost:3306/internship";//MYSQL数据库连接Url
    private static String user = "root";//用户名
    private static String password = "1q2w3e4r";//密码

    /**
     * 连接数据库
     * */


    public static Connection getConn(){
        Log.d("hhh","1111");
        Connection conn = null;
        try {
            Log.d("hhh","aaaa");
            Class.forName(driver);//获取MYSQL驱动
            Log.d("hhh","bbbb");
            conn = (Connection) DriverManager.getConnection(url, user, password);//获取连接
            Log.d("hhh","cccc");
            if (conn==null)
            {
                Log.d("hhh","conn is null");
            }
        } catch (ClassNotFoundException e) {
            Log.d("hhh","message1:"+e.getMessage());
            e.printStackTrace();
        } catch (SQLException e) {
            Log.d("hhh","message2:"+e.getMessage());
            e.printStackTrace();
        }
        Log.d("hhh","2222w");
        return conn;
    }

    /**
     * 关闭数据库
     * */
    public static void closeAll(Connection conn, PreparedStatement ps){
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * 关闭数据库
     * */
    public static void closeAll(Connection conn, PreparedStatement ps, ResultSet rs){
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
