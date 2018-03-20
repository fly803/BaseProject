package com.cg.baseproject.request.retrofit.subscriber;

import android.widget.Toast;

import com.cg.baseproject.BaseApplication;
import com.cg.baseproject.helper.DialogHelper;
import com.cg.baseproject.request.exception.ApiException;

import rx.Subscriber;

/**
 * @author
 * @version 1.0
 * @date 2018/3/14
 */

public class RxSubscriber<T> extends ErrorSubscriber<T> {

    @Override
    public void onStart() {
        super.onStart();
        DialogHelper.showProgressDlg(BaseApplication.getContext(), "正在加载数据");
    }

    @Override
    public void onCompleted() {
        DialogHelper.stopProgressDlg();
    }

    @Override
    protected void onError(ApiException ex) {
        DialogHelper.stopProgressDlg();
        Toast.makeText(BaseApplication.getContext(), ex.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNext(T t) {

    }
}


abstract class ErrorSubscriber<T> extends Subscriber<T> {
    @Override
    public void onError(Throwable e) {
        if(e instanceof ApiException){
            onError((ApiException)e);
        }else{
            onError(new ApiException(e,123));
        }
    }

    /**
     * 错误回调
     */
    protected abstract void onError(ApiException ex);
}
