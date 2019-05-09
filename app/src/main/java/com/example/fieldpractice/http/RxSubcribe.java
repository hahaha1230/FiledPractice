package com.example.fieldpractice.http;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;

import com.example.fieldpractice.base.BaseActivity;
import com.example.fieldpractice.base.BaseBean;
import com.example.fieldpractice.http.ApiException;

import java.io.IOException;

import io.reactivex.Observer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import retrofit2.HttpException;
import retrofit2.http.HTTP;

/**
 * Created by JG on 2019/5/9.
 *
 * RxJava订阅者封装基类
 */
@Deprecated
public abstract class RxSubcribe<T> implements Observer<BaseBean<T>> {


    private ProgressDialog mProgressDialog;
    private Disposable disposable;
    private BaseActivity context;
    private String errorMsg;


    public RxSubcribe(BaseActivity context)
    {
        this.context=context;
    }


    @Override
    public void onSubscribe(Disposable d) {
        disposable=new CompositeDisposable();
        showLoading();

    }


    @Override
    public void onNext(BaseBean<T> baseBean) {
        if (!baseBean.isSuccess())
        {
            ApiException apiException = new ApiException(baseBean.getCode(),baseBean.getError());
            onError(apiException);
            return;
        }
        onSuccess(baseBean.message);

    }

    @Override
    public void onError(Throwable e) {
        if (e instanceof IOException){
            //没有网络
            errorMsg="请检查你的网络状态";
        }
        else if (e instanceof HttpException)
        {
            /** 网络异常，http 请求失败，即 http 状态码不在 [200, 300) 之间, such as: "server internal error". */
            errorMsg=((HttpException)e).response().message();
        }
        else if (e instanceof ApiException)
        {
            /** 网络正常，http 请求成功，服务器返回逻辑错误 */
            errorMsg = ((ApiException)e).getMsg();
        }
        else {
            /** 其他未知错误 */
            errorMsg = !TextUtils.isEmpty(e.getMessage()) ? e.getMessage() : "unknown error";
        }

        dismissLoading();

       new AlertDialog.Builder(context)
               .setTitle("提示")
               .setMessage(errorMsg)
               .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialog, int which) {
                       dialog.dismiss();
                       if (!disposable.isDisposed()){
                           disposable.dispose();
                       }
                   }
               }).show();

    }

    @Override
    public void onComplete() {
        dismissLoading();

    }

    public abstract void onSuccess(T t);

    private void showLoading() {
        if(mProgressDialog == null){
            mProgressDialog = new ProgressDialog(context);
            mProgressDialog.setMessage("正在加载中...");
            mProgressDialog.show();
        }
    }

    private void dismissLoading(){
        if(mProgressDialog != null){
            mProgressDialog.dismiss();
        }
    }
}
