package com.cg.baseproject;

import android.content.Context;
import android.support.multidex.MultiDexApplication;

import com.cg.baseproject.crash.CrashHandler;
import com.squareup.leakcanary.LeakCanary;

/**
 * @author
 * @version 1.0
 * @date 2018/3/2
 */
public class BaseApplication extends MultiDexApplication {
    private static Context mContext;
    private static String uid ="10001";//用户id，用户唯一标识

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        init();
    }

    
    /**
     * 得到Application环境变量
     * @return
     */
    public static Context getContext() {
        return mContext;
    }

    /**
     * 初始化话app信息
     */
    private void init() {
        LeakCanary.install(this);
//        CrashHandler crashHandler = CrashHandler.getInstance();
//        crashHandler.init(this);
    }

    /**
     * 得到登录用户id
     * @return
     */
    public static String getUID() {
        return uid;
    }

    /**
     * 设置用户id
     * @param userID
     */
    public static void setUID(String userID) {
        uid = userID;
    }
}


