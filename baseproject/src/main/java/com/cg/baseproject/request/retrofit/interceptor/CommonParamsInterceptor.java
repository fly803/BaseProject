package com.cg.baseproject.request.retrofit.interceptor;


import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author
 * @version 1.0
 * @date 2018/3/9
 */

public class CommonParamsInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originRequest = chain.request();
        Request request;
        HttpUrl httpUrl = originRequest.url().newBuilder().
                addQueryParameter("paltform", "android").
                addQueryParameter("version", "1.0.0").build();
        request = originRequest.newBuilder().url(httpUrl).build();
        return chain.proceed(request);
    }
}
