package com.cg.baseproject;

import android.app.Activity;
import android.content.Context;
import android.support.multidex.MultiDexApplication;

import com.cg.baseproject.configs.BaseProjectConfig;

import java.util.Set;

import me.yokeyword.fragmentation.Fragmentation;
import me.yokeyword.fragmentation.helper.ExceptionHandler;

/**
 * @author sam
 * @version 1.0
 * @date 2018/3/2
 */
public class BaseApplication extends MultiDexApplication {
    private static BaseApplication application = null;
    private static Context mContext;
    private static String uid ="10001";//用户id，用户唯一标识
    private Set<Activity> allActivities;
    
    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        application = this;
        init();
    }

    public static BaseApplication getBaseApplication(){
        return application;
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
//        initFragmentation();
    }

    /**
     * 退出app
     */
    public void exitApp() {
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
    }
    
    /**
     * 退出app
     */
    public void exitAppAll() {
        if (allActivities != null) {
            synchronized (allActivities) {
                for (Activity act : allActivities) {
                    act.finish();
                }
            }
        }
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
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


