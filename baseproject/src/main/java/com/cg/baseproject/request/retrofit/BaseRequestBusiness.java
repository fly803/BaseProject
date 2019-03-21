package com.cg.baseproject.request.retrofit;

import android.util.Log;
import com.cg.baseproject.request.data.BaseResponse;
import com.cg.baseproject.configs.BaseProjectConfig;
import com.cg.baseproject.request.exception.ApiException;
import com.cg.baseproject.request.retrofit.subscriber.ProgressSubscriber;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * http://blog.csdn.net/sk719887916/article/details/75223423
 * @author sam
 * @version 1.0
 * @date 2018/3/18
 */

public abstract class BaseRequestBusiness {
    /**
     * 所以调研接口，统一调用这个方法
     * @param ob
     * @param subscriber
     */
  public void toSubscribe(Observable ob, final ProgressSubscriber subscriber) {
    //数据预处理
    ObservableTransformer<BaseResponse<Object>, Object> result = handleResult();
    Observable observable = ob.compose(result);
    observable.subscribeWith(subscriber);
  }

    /**
     * 默认情况下,发送者和接收者都运行在主线程,但是这显然是不符合实际需求的,我们在日常使用中,
     * 通常用的最多的就是在子线程进行各种耗时操作,然后发送到主线程进行,难道我们就没有办法继续
     * 用这个优秀的库了?想多了你,一个优秀的库如果连这都想不到,怎么能被称为优秀呢,RxJava中有线
     * 程调度器,通过线程调度器,
     * 
     * Transformer的变化:RxJava1.X为rx.Observable.Transformer接口, 继承自
     * Func1<Observable<T>, Observable<R>>, RxJava2.X为io.reactivex.ObservableTransformer<Upstream, Downstream>,是一个独立的接口。
     * Flowable则是FlowableTransformer，如果你使用Flowable，以下ObservableTransformer
     * 替换FlowableTransformer即可。
     * @param <T>
     * @return
     */
  public static <T> ObservableTransformer<BaseResponse<T>, T> handleResult() {
    return new ObservableTransformer<BaseResponse<T>, T>() {
      @Override public Observable<T> apply(Observable<BaseResponse<T>> tObservable) {
        return tObservable.flatMap(new Function<BaseResponse<T>, ObservableSource<T>>() {
          @Override public Observable<T> apply(BaseResponse<T> result) {
            //成功后交给界面处理
            if (result.getCode() == BaseProjectConfig.successCode) {
                return createData(result.getData());
            } else {
                //统一处理服务器返回值非正常结果
                Log.d(BaseProjectConfig.TAG, "BaseRequestBusiness统一处理服务器返回值非正常结果apply: " + BaseProjectConfig.getApiReason(result.getCode()));
                return Observable.error(new ApiException(BaseProjectConfig.getApiReason(result.getCode())));
            }
          }
        })
         /*
          - Schedulers.io()      io操作的线程, 通常io操作,如文件读写,读写数据库、网络信息交互等.
          - Schedulers.computation()      计算线程,适合高计算,数据量高的操作.
          - Schedulers.newThread()      创建一个新线程,适合子线程操作.
          - AndroidSchedulers.mainThread()      Android的主线程,主线程       
         */
        .subscribeOn(Schedulers.io()) //线程调度器,将发送者运行在子线程,subscribeOn(),只有在第一次调用的时候生效,之后不管调用多少次,只会以第一次为准.
        .unsubscribeOn(Schedulers.io())//解除订阅
//        .subscribeOn(AndroidSchedulers.mainThread())//
        .observeOn(AndroidSchedulers.mainThread());//接受者运行在主线程 observeOn(),可以被调用多次,每次调用都会更改线程.
      }
    };
  }

  /**
   * 创建成功的数据,观察者模式,这里产生事件,事件产生后发送给接受者
   */
  private static <T> Observable<T> createData(final T data) {
    return Observable.create(new ObservableOnSubscribe<T>() {
      @Override public void subscribe(ObservableEmitter<T> e) throws Exception {
          e.onNext(data);
          e.onComplete();
      }
    });
  }
}
