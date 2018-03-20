package com.cg.baseproject.request.retrofit.cache;

import com.cg.baseproject.BaseApplication;

import java.io.File;

import okhttp3.Cache;

/**
 * @author
 * @version 1.0
 * @date 2018/3/9
 */

public class RetrofitCache {
    //创建Cache
    File httpCacheDirectory = new File(BaseApplication.getContext().getCacheDir(), "OkHttpCache");
    Cache cache = new Cache(httpCacheDirectory, 10 * 1024 * 1024);
//    httpClientBuilder.cache(cache);
//    //设置缓存
//    httpClientBuilder.addNetworkInterceptor(getCacheInterceptor2());
//    httpClientBuilder.addInterceptor(getCacheInterceptor2());
}
