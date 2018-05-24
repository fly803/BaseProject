package com.cg.baseproject.configs;

import android.app.Application;

import com.cg.baseproject.crash.CrashHandler;
import com.squareup.leakcanary.LeakCanary;

/**
 * @author
 * @version 1.0
 * @date 2018/3/20
 */

public class BaseProjectConfig {
    public static  int SUCCESS_CODE = 0;
    public static  boolean DEBUG = true;
    public static  String BASE_URL = "http://ip.taobao.com/";
    public static  String TAG = "retrofit";
    public static final String DOUBAN_BASE_URL = "https://api.douban.com/";
    public static final String GANK_BASE_URL = "https://gank.io/";
    public static void init(Application application, boolean isLeakCanary,boolean isCrashHandel, boolean debug, String baseurl, int successcode, String tag){
        if(isLeakCanary){
            LeakCanary.install(application);
        }
        if(isCrashHandel){
            CrashHandler crashHandler = CrashHandler.getInstance();
            crashHandler.init(application);
        }
        DEBUG = debug;
        BASE_URL = baseurl;
        SUCCESS_CODE = successcode;
        TAG = tag;
    }
}
