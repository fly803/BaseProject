package com.cg.baseproject.base;

/**
 * @author
 * @version 1.0
 * @date 2018/3/18
 */

import com.cg.baseproject.BaseApplication;
import com.cg.baseproject.request.exception.ERROR;
import com.cg.baseproject.deprecated.DeprecatedExceptionHandle;
import com.cg.baseproject.utils.android.NetworkUtils;
import com.cg.baseproject.utils.android.ToastUtils;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public abstract class BaseObserver<T> implements Observer<BaseObserver<T>> {
//    protected Context mContext;
//
//    public BaseObserver(Context cxt) {
//        this.mContext = cxt;
//    }

    @Override
    public void onSubscribe(Disposable d) {
        onRequestStart();

    }

    @Override
    public void onNext(BaseObserver<T> baseObserver) {
        onRequestEnd();
        if (baseObserver!=null) {
            try {
                onSuccees(baseObserver);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                onCodeError(baseObserver);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onError(Throwable e) {
        //        Log.w(TAG, "onError: ", );这里可以打印错误信息  
        onRequestEnd();
        try {
            if(e instanceof DeprecatedExceptionHandle.ResponeThrowable){
                onError((DeprecatedExceptionHandle.ResponeThrowable)e);
            } else {
                onError(new DeprecatedExceptionHandle.ResponeThrowable(e, ERROR.UNKNOWN));
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    @Override
    public void onComplete() {
    }

    /**
     * 返回成功
     *
     * @param t
     * @throws Exception
     */
    protected abstract void onSuccees(BaseObserver<T> t) throws Exception;

    /**
     * 返回成功了,但是code错误
     *
     * @param t
     * @throws Exception
     */
    protected void onCodeError(BaseObserver<T> t) throws Exception {
    }

    /**
     * 返回失败
     *
     * @param e
     * @param isNetWorkError 是否是网络错误
     * @throws Exception
     */
    protected abstract void onFailure(Throwable e, boolean isNetWorkError) throws Exception;

    protected void onRequestStart() {
        //请求开始之前，检查是否有网络.
        if (!NetworkUtils.isConnected(BaseApplication.getContext())) {
            ToastUtils.showShort("当前网络不可用，请检查网络情况");
            // 一定好主动调用下面这一句
            onRequestEnd();
            return;
        }
        // 显示进度条
        showProgressDialog();
    }

    protected void onRequestEnd() {
        closeProgressDialog();
    }

    public void showProgressDialog() {
//        ProgressDialog.show(mContext, false, "请稍后");
    }

    public void closeProgressDialog() {
//        ProgressDialog.cancle();
    }
    
    
}
  
