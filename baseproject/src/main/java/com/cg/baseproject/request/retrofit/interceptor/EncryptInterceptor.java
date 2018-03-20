package com.cg.baseproject.request.retrofit.interceptor;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author
 * @version 1.0
 * @date 3/8/2018
 */

public class EncryptInterceptor implements Interceptor {
    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Request request = chain.request(); 
        //这个是请求的url，也就是咱们前面配置的baseUrl
        String url = request.url().toString();
        //这个是请求方法 
        String method = request.method(); 
//        long t1 = System.nanoTime(); request = encrypt(request);
        //模拟的加密方法 
        Response response = chain.proceed(request); 
        long t2 = System.nanoTime(); 
//        response = decrypt(response);
        return response; 
    } 
    
    
}

