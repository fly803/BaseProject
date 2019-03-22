package com.cg.baseproject.request.retrofit.subscriber;

import android.content.Context;

import com.cg.baseproject.configs.BaseProjectConfig;
import com.cg.baseproject.interfaces.SubscriberOnNextListener;
import com.cg.baseproject.request.exception.ExceptionEngine;
import com.cg.baseproject.request.retrofit.progress.ProgressCancelListener;
import com.cg.baseproject.view.loading.CommonLoading;
import com.orhanobut.logger.Logger;
import com.shashank.sony.fancytoastlib.FancyToast;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * 用于在Http请求开始时，自动显示一个ProgressDialog
 * 在Http请求结束是，关闭ProgressDialog
 * 调用者自己对请求数据进行处理
 * Created by sam on 16/3/10.
 */
public class ProgressSubscriber<T> implements ProgressCancelListener, Observer<T> {
    /*
    Disposable,翻译成一次性的。是用来控制发送者和接受者之间的纽带的,默认为false,表示发送者和接受者直接的通信阀门关闭,可以正常通信,
    在调用dispose()方法之后,阀门开启,会阻断发送者和接收者之间的通信,从而断开连接.
     */
  private Disposable mDisposable;
  private SubscriberOnNextListener mSubscriberOnNextListener;
//  private ProgressDialogHandler mProgressDialogHandler;
  private Context context;
  private CommonLoading mCommonLoading;

  public ProgressSubscriber(SubscriberOnNextListener mSubscriberOnNextListener, Context context) {
    this.mSubscriberOnNextListener = mSubscriberOnNextListener;
    this.context = context;
    mCommonLoading = new CommonLoading(context, BaseProjectConfig.loadingMessage);
  }

  private void showProgressDialog() {
    if (mCommonLoading != null) {
        mCommonLoading.showLoading();
    }
  }

  private void dismissProgressDialog() {
    if (mCommonLoading != null) {
      mCommonLoading.closeLoading();
    }
  }

  /**
   * 对错误进行统一处理
   * 隐藏ProgressDialog
   * 当发送了onError事件之后,发送者onError之后的事件依旧会继续发送,但是接收者当接收到onError之后就会停止接收事件了.
   */
  @Override public void onError(Throwable e) {
      FancyToast.makeText(context,"返回值错误ProgressSubscriber onError:" + ExceptionEngine.handleException(e).getMessage(), 
              FancyToast.LENGTH_LONG,FancyToast.ERROR,false).show();
    dismissProgressDialog();
  }

  /**
   * 当发送了onComplete事件之后,发送者的onComplete之后的事件依旧会继续发送,
   * 但是接收者接收到onComplete之后就停止接收事件了.
   */
  @Override public void onComplete() {
    dismissProgressDialog();
    if (!mDisposable.isDisposed()) {
      Logger.d("取消订阅者");
      mDisposable.dispose();
    }
//    Toast.makeText(context, "数据获取成功", Toast.LENGTH_SHORT).show();
  }

  @Override public void onSubscribe(Disposable d) {
    mDisposable = d;
    showProgressDialog();
  }

  /**
   * 将onNext方法中的返回结果交给Activity或Fragment自己处理
   *
   * @param t 创建Subscriber时的泛型类型
   */
  @Override public void onNext(T t) {
    if (mSubscriberOnNextListener != null) {
      mSubscriberOnNextListener.onNext(t);
    }
  }

  @Override public void onCancelProgress() {
    if (!mDisposable.isDisposed()) {
      Logger.d("取消订阅者");
      mDisposable.dispose();
    }
  }
}