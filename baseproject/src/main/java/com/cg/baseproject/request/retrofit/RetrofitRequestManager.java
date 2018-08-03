package com.cg.baseproject.request.retrofit;

import android.util.Log;
import com.cg.baseproject.configs.BaseProjectConfig;
import com.cg.baseproject.request.retrofit.converter.GsonResponseBodyConverter;
import com.cg.baseproject.request.retrofit.factory.MyGsonConverterFactory;
import com.cg.baseproject.request.retrofit.interceptor.BaseUrlInterceptor;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author
 * @version 1.0
 * @date 3/5/2018
 * https://www.cnblogs.com/oceanfhy/p/7699379.html
 * http://blog.csdn.net/picasso_l/article/details/53200926
 * 
 *{
    "code": 0,
    "msg": "正常",
    "data": {
        "id": 1,
        "account": "121313",
        "accountName": "alipay",
        "income": "600.000000"
    }
  }
    msg为服务器端返回的操作信息。
    无论操作成功与否，客户端都应该根据业务给出准确的提示，客户端则根据实际情况选择展示与否。
    code 	说明
    0 	操作成功的消息提示
    1 	客户端认证失败，一般是客户端被恶意修改
    2 	用户认证失败 	
    3 	提交参数错误：参数缺失、参数名不对等
    4 	提交参数校验失败，一般是提交给服务端的数据格式有误，多发生在表单提交的场景中
    5 	自定义错误，服务端发生不可恢复的错误等
    data 定义
    data则是请求返回的具体内容，通常data根据请求接口的不同最终会被解析成不同的实体类。
 */
public class RetrofitRequestManager {
    private static final String TAG = "RetrofitManager";
    private static RetrofitRequestManager mRetrofitRequestManager;
    private Retrofit mRetrofit;
    private static Converter.Factory gsonConverterFactory = MyGsonConverterFactory.create();
//    private static Converter.Factory gsonConverterFactory = GsonConverterFactory.create();

    private RetrofitRequestManager() {
        initRetrofit();
    }

    public static synchronized RetrofitRequestManager getInstance() {
        if (mRetrofitRequestManager == null) {
            mRetrofitRequestManager = new RetrofitRequestManager();
        }
        return mRetrofitRequestManager;
    }

    public Retrofit getRetrofit(){
        return mRetrofit;
    }
    
    private void initRetrofit() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(BaseProjectConfig.BASE_URL)//配置服务器路径
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(gsonConverterFactory)//配置转化库 字符串类型 的
                // 配置转化库，默认是Gson(返回参数不规范 要不然可以直接转换成实体类)
                //配置回调库，采用RxJava
                //设置OKHttpClient为网络客户端
                .client(genericClient())
                .build();
    }

    public static OkHttpClient genericClient() {
        /**
         * 类型为BASIC，其实日志级别分为4类：NONE、BASIC、HEADERS、BODY。
         大家看下我打印出来的日志，就知道这4类的区别了。
         1、NONE
         没有任何log
         2、BASIC
         请求/响应行
         basic的格式:
         --> POST 地址 http/1.1 (0-byte body)
         <-- 200 OK 地址 (154ms, unknown-length body)
         3、HEADERS
         请求/响应行 + 头
         4、BODY
         请求/响应行 + 头 + 体 
         */
        final OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(15, TimeUnit.SECONDS);
        builder.readTimeout(20, TimeUnit.SECONDS);
        builder.writeTimeout(20, TimeUnit.SECONDS);
        //错误重连
        builder.retryOnConnectionFailure(true);
        //新建log拦截器
        final HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.w(BaseProjectConfig.TAG, "genericClient log: "+message);
            }
        });
        HttpLoggingInterceptor.Level level = HttpLoggingInterceptor.Level.BASIC;
        loggingInterceptor.setLevel(level);
        if (BaseProjectConfig.DEBUG) {
            builder.addInterceptor(loggingInterceptor);//添加retrofit日志打印 } 
//            builder.addInterceptor(new HeaderInterceptor());
            builder.addInterceptor(new BaseUrlInterceptor());
        }
        OkHttpClient httpClient = builder.build();
        return httpClient;
    }
    
    
}
