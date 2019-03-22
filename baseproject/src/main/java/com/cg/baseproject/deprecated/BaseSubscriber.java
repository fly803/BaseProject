package com.cg.baseproject.deprecated;

import android.content.Context;
import android.util.Log;

import com.cg.baseproject.BaseApplication;
import com.cg.baseproject.request.exception.ERROR;
import com.cg.baseproject.deprecated.DeprecatedExceptionHandle;
import com.cg.baseproject.utils.android.NetworkUtils;
import com.cg.baseproject.utils.android.ToastUtils;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;


/**
 * @author sam
 * @explain 封装基础Subscriber
 * @time 2018/3/3 18:05
 */

public abstract class BaseSubscriber<T> implements Subscriber<T> {
    private static final String TAG = "BaseSubscriber";
    protected Context mContext;

    public BaseSubscriber(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public void onSubscribe(Subscription s) {
        //请求开始之前，检查是否有网络.
        if (!NetworkUtils.isConnected(BaseApplication.getContext())) {
            ToastUtils.showShort("当前网络不可用，请检查网络情况");
            // 一定好主动调用下面这一句
            onComplete();
            return;
        }
        // 显示进度条
        //        showLoadingProgress();
    }
    
    @Override
    public void onNext(T t) {
        try {
            
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }

    @Override
    public void onError(Throwable e) {
        Log.e(TAG, e.getMessage());
        // todo error somthing
        if(e instanceof DeprecatedExceptionHandle.ResponeThrowable){
            onError((DeprecatedExceptionHandle.ResponeThrowable)e);
        } else {
            onError(new DeprecatedExceptionHandle.ResponeThrowable(e, ERROR.UNKNOWN));
        }
        onComplete();
    }

    @Override
    public void onComplete() {
        //关闭等待进度条
//        closeLoadingProgress();
    }
    


//    public static void onFail(int state, String message) {
//        if (!NetworkUtils.isNetworkAvailable(BaseApplication.getContext())) {
//            ToastUtils.showShort("网络错误，请检查网络后重试");
//        } else if (!TextUtils.isEmpty(message)) {
//            if (message.contains("timed out")) {
//                ToastUtils.showShort("请求超时");
//            } else if (state == 500 || state == 502) {
//                ToastUtils.showShort("服务异常,请稍后重试");
//            } else if (message.contains("refused")) {
//                ToastUtils.showShort("服务请求超时，请稍后重试");
//            } else if (state == 0) {
//                ToastUtils.showShort("数据异常,请稍后重试");
//            } else if (state == 404) {
//                ToastUtils.showShort("服务不存在,请稍后重试");
//            } else if (state == 403) {
//                ToastUtils.showShort("服务资源不可用,请稍后重试");
//            } else
//                ToastUtils.showShort(message);
//        }
//    }

}

