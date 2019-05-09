package com.example.fieldpractice.base;

import java.io.Serializable;

/**
 * Created by JG on 2019/5/8.
 */

public class BaseBean<T> implements Serializable {

    //code为0代表成功，其余的代表不成功
    public int code;

    //返回具体的错误信息
    public String error="";

    //model
    public T message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public T getMessage() {
        return message;
    }

    public void setMessage(T message) {
        this.message = message;
    }


    public boolean isSuccess(){
        return code==0;
    }
}
