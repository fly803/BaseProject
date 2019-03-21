package com.cg.baseproject.configs;

import android.app.Application;

import com.cg.baseproject.crash.CrashHandler;
import com.squareup.leakcanary.LeakCanary;

import java.util.HashMap;
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
    public static  int successCode = 0;
    public static  boolean neqRequestLog = true;
    public static  String baseURL = "http://ip.taobao.com/";
    public static  String TAG = "BaseProjectConfig";
    public static final String DOUBAN_BASE_URL = "https://api.douban.com/";
    public static final String GANK_BASE_URL = "https://gank.io/";

    public static float baseScale = 3.0f;
    public static float widthDp = 360f;
    public static String apiReturnCode = "未知";
    public static String loadingMessage = "数据请求中...";
    public static Map<Integer, String> mapServerReturnCode = new HashMap<Integer, String>();

    /**
     * 初始化BaseProject
     * @param application 应用的application
     * @param isLeakCanary 是否集成内存检测库LeakCanary
     * @param isCrashHandel 是否集成全局Crash监控
     * @param isNetRequestLog 是否打印网络请求log
     * @param stackview 栈结构分析样式
     * @param baseurl 网络请求baseurl
     * @param successcode 网络请求成功code，例如200
     * @param tag 打印log的tag
     * @param loadingmessage 数据加载loading的显示语
     * @param map 应用工程自定义的API异常
     */
    public static void init(Application application, boolean isLeakCanary, boolean isCrashHandel, boolean isNetRequestLog,int stackview,
                             String baseurl, int successcode, String tag,String loadingmessage, Map<Integer, String> map){
        if(isLeakCanary){
            LeakCanary.install(application);
        }
        if(isCrashHandel){
            CrashHandler crashHandler = CrashHandler.getInstance();
            crashHandler.init(application);
        }
        initFragmentation(stackview);
        neqRequestLog = isNetRequestLog;
        baseURL = baseurl;
        successCode = successcode;
        TAG = tag;
        loadingMessage = loadingmessage;
        mapServerReturnCode = map;
    }

    private static void initFragmentation(int stackViewMode) {
        Fragmentation.builder()
                // 设置 栈视图 模式为 （默认）悬浮球模式   SHAKE: 摇一摇唤出  NONE：隐藏， 仅在Debug环境生效
                .stackViewMode(stackViewMode)
                .debug(BaseProjectConfig.neqRequestLog) // 实际场景建议.debug(BuildConfig.neqRequestLog)
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
