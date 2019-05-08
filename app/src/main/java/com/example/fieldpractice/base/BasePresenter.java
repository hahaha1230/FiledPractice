package com.example.fieldpractice.base;



import io.reactivex.annotations.Nullable;

/**
 * Created by JG on 2019/4/19.
 */

public abstract class BasePresenter<V extends BaseActivity,M extends BaseModel,CONTRACT> extends SuperBase<CONTRACT> {

   public V mView;


   public M mModel;

   public BasePresenter()
   {
       this.mModel=getModelInstance();
   }

   //将view和presenter建立关系
    public void bindView(V mView)
    {
        this.mView=mView;
    }

    //activity销毁时候要进行解绑，否则会内存溢出
    public void unBindView()
    {
        this.mView= null;
    }


    //让子类实例化model层
    public abstract M getModelInstance();

}
