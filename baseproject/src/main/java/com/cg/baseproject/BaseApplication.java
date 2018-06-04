package com.cg.baseproject;

import android.app.Activity;
import android.content.Context;
import android.support.multidex.MultiDexApplication;

import com.cg.baseproject.configs.BaseProjectConfig;

import java.util.Set;

import me.yokeyword.fragmentation.Fragmentation;
import me.yokeyword.fragmentation.helper.ExceptionHandler;

/**
 * @author
 * @version 1.0
 * @date 2018/3/2
 */
public class BaseApplication extends MultiDexApplication {
    private static Context mContext;
    private static String uid ="10001";//用户id，用户唯一标识
    private Set<Activity> allActivities;
    
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
        initFragmentation();
    }

    private void initFragmentation() {
        Fragmentation.builder()
                // 设置 栈视图 模式为 （默认）悬浮球模式   SHAKE: 摇一摇唤出  NONE：隐藏， 仅在Debug环境生效
                .stackViewMode(Fragmentation.BUBBLE)
                .debug(BaseProjectConfig.DEBUG) // 实际场景建议.debug(BuildConfig.DEBUG)
                /**
                 * 可以获取到{@link me.yokeyword.fragmentation.exception.AfterSaveStateTransactionWarning}
                 * 在遇到After onSaveInstanceState时，不会抛出异常，会回调到下面的ExceptionHandler
                 */
                .handleException(new ExceptionHandler() {
                    @Override
                    public void onException(Exception e) {
                        // 以Bugtags为例子: 把捕获到的 Exception 传到 Bugtags 后台。
                        // Bugtags.sendException(e);
                    }
                })
                .install();
    }

    /**
     * 退出app
     */
    public void exitApp() {
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


