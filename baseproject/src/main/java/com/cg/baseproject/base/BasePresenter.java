package com.cg.baseproject.base;

import android.content.Context;

/**
 * Author: 姚智胜
 * Version: V1.0版本
 * Description:基类presenter
 * Date: 2017/6/13
 * Email: 541567595@qq.com
 */
public abstract class BasePresenter<T extends BaseUiView, E extends BaseModel> {
    public Context mContext;
    public E mModel;
    public T mView;

    public void setVM(T v, E m) {
        this.mView = v;
        this.mModel = m;
        this.onStart();
    }

    public void onStart() {
    }


    public void onDestroy() {
        mModel.onDestroy();
    }

    public void okRefresh(){

    }

    public void okLoadMore(){

    }
}
