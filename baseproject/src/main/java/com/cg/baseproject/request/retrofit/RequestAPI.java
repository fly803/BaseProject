package com.cg.baseproject.request.retrofit;

/**
 * @author sam
 * @version 1.0
 * @date 2018/3/20
 */

public class RequestAPI<T> extends BaseRequestBusiness {
    //    private RequestApiInterface requesteApiInterface;
    private T t;

    private static RequestAPI mRequestAPI;

    /**
     * 单例模式，得到requestbusiness的实例
     *
     * @return
     */
    public static synchronized RequestAPI getInstance() {
        if (mRequestAPI == null) {
            mRequestAPI = new RequestAPI();
        }
        return mRequestAPI;
    }

    public T getApi(Class apiInterface) {
        if (t == null) {
            t = (T) RetrofitRequestManager.getInstance().getRetrofit().create(apiInterface);
        }
        return t;
    }
}
