package com.cg.baseproject.request.retrofit.interceptor;

import com.cg.baseproject.configs.BaseProjectConfig;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author sam
 * @version 1.0
 * @date 3/8/2018
 */

public class BaseUrlInterceptor implements Interceptor {
  @Override public Response intercept(Chain chain) throws IOException {
    //获取request
    Request request = chain.request();
    //从request中获取原有的HttpUrl实例oldHttpUrl
    HttpUrl oldHttpUrl = request.url();
    //获取request的创建者builder
    Request.Builder builder = request.newBuilder();
    //从request中获取headers，通过给定的键url_name
    List<String> headerValues = request.headers("baseurl");
    if (headerValues != null && headerValues.size() > 0) {
      //如果有这个header，先将配置的header删除，因此header仅用作app和okhttp之间使用
      builder.removeHeader("baseurl");
      //匹配获得新的BaseUrl
      String headerValue = headerValues.get(0);
      HttpUrl newBaseUrl = null;
      for (Map.Entry<String, String> entry : BaseProjectConfig.mapBaseURL.entrySet()) {
        if (entry.getKey().equals(headerValue)) {
          newBaseUrl = HttpUrl.parse(entry.getValue());
        }
      }
      //重建新的HttpUrl，修改需要修改的url部分

      HttpUrl.Builder mBuilder = oldHttpUrl.newBuilder();
      if (newBaseUrl.isHttps()) {
        mBuilder.scheme("https")//更换网络协议
            .host(newBaseUrl.host())//更换主机名
            .port(newBaseUrl.port());//更换端口
        //                    .removePathSegment(0)//移除第一个参数
      } else {
        mBuilder.scheme("http")//更换网络协议
            .host(newBaseUrl.host())//更换主机名
            .port(newBaseUrl.port());//更换端口
        //                    .removePathSegment(0)//移除第一个参数
      }
      //重建这个request，通过builder.url(newFullUrl).build()；
      // 然后返回一个response至此结束修改
      HttpUrl newFullUrl = mBuilder.build();
      return chain.proceed(builder.url(newFullUrl).build());
    }
    return chain.proceed(request);
  }
}
