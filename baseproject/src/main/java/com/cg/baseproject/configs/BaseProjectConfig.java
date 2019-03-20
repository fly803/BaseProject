package com.cg.baseproject.configs;

import android.app.Application;
import android.app.Fragment;

import com.cg.baseproject.crash.CrashHandler;
import com.squareup.leakcanary.LeakCanary;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.yokeyword.fragmentation.Fragmentation;
import me.yokeyword.fragmentation.helper.ExceptionHandler;

/**
 * @author sam
 * @version 1.0
 * @date 2018/3/20
 */
public class BaseProjectConfig {
    /**
     * Dont display stack view.
     */
    public static final int NONE = 0;
    /**
     * Shake it to display stack view.
     */
    public static final int SHAKE = 1;
    /**
     * As a bubble display stack view.
     */
    public static final int BUBBLE = 2;
    public static  int SUCCESS_CODE = 0;
    public static  boolean DEBUG = true;
    public static  String BASE_URL = "http://ip.taobao.com/";
    public static  String TAG = "retrofit";
    public static final String DOUBAN_BASE_URL = "https://api.douban.com/";
    public static final String GANK_BASE_URL = "https://gank.io/";

    public static float baseScale = 3.0f;
    public static float widthDp = 360f;
    public static String apiReturnCode = "未知";
    public static Map<Integer, String> mapServerReturnCode = new HashMap<Integer, String>();

    public static void init(Application application, boolean isLeakCanary, boolean isCrashHandel, int stackview,
                            boolean debug, String baseurl, int successcode, String tag, Map<Integer, String> map){
        if(isLeakCanary){
            LeakCanary.install(application);
        }
        if(isCrashHandel){
            CrashHandler crashHandler = CrashHandler.getInstance();
            crashHandler.init(application);
        }
        initFragmentation(stackview);
        DEBUG = debug;
        BASE_URL = baseurl;
        SUCCESS_CODE = successcode;
        TAG = tag;
        mapServerReturnCode = map;
    }

    private static void initFragmentation(int stackViewMode) {
        Fragmentation.builder()
                // 设置 栈视图 模式为 （默认）悬浮球模式   SHAKE: 摇一摇唤出  NONE：隐藏， 仅在Debug环境生效
                .stackViewMode(stackViewMode)
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
     * 根据异常码，显示异常原因
     * @date   2019/3/20
     * @version 1.0
     * @param  * @param null
     * @return  
     */ 
    public static String getApiReason(int code){
        String reason = "未知";
        try {
            for(Map.Entry<Integer, String> entry : mapServerReturnCode.entrySet()){
                if(entry.getKey() == code){
                    reason = entry.getValue();
                }
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return reason;
    }

}
