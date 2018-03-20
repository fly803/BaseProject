package com.cg.baseproject.request.retrofit.interceptor;


import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 添加请求头
 * @author
 * @version 1.0
 * @date 3/8/2018
 */

public class HeaderInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originalRequest = chain.request();
        Request.Builder builder = originalRequest.newBuilder();
        builder.addHeader("version", "1");
        builder.addHeader("time", System.currentTimeMillis() + "");

        Request.Builder requestBuilder = builder.method(originalRequest.method(), originalRequest.body());
        Request request = requestBuilder.build();
        return chain.proceed(request);
    }
//    @Override
//    public Response intercept(Chain chain) throws IOException {
//        Request original = chain.request();
//        Request request = original.newBuilder()
//                .addHeader("userData", "json")
//                .method(original.method(), original.body())
//                .build();
//        return chain.proceed(request);
//    }
}
