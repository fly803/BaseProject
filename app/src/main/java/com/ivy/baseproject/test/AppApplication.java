package com.ivy.baseproject.test;

import android.content.Context;

import com.cg.baseproject.BaseApplication;
import com.ivy.baseproject.test.api.AppConfig;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * @author
 * @version 1.0
 * @date 2018/3/2
 */
public class AppApplication extends BaseApplication {
    private static AppApplication application;
    private static final int MEMORY_SIZE = 5 * 1024 * 1024;
    private static final int DISK_SIZE = 20 * 1024 * 1024;
    
    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        initImageLoader();
        initConfig();
    }

    public static AppApplication getInstance() {
        return application;
    }

    private void initConfig(){
        AppConfig.init(this,false,false,
                AppConfig.BUBBLE,true, AppConfig.BASE_URL,AppConfig.SUCCESS_CODE,"AppConfig",getServerReturnCodeMap());
    }

    /** 
     * 外部工程传自己的API异常码给类库工程
     * @date   2019/3/20
     * @version 1.0
     * @param  * @param null
     * @return  
     */ 
    private Map<Integer, String> getServerReturnCodeMap(){
        Map<Integer, String> map = new HashMap<Integer, String>();
        map.put(101, "消息101");
        map.put(102, "消息102");
        map.put(103, "消息101");
        map.put(104, "消息102");
        map.put(105, "消息101");
        map.put(106, "消息102");
        map.put(999, "不明原因999");
        return map;
    }
    
    private void initImageLoader(){
        // 初始化 Image-Loader
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .build();

        ImageLoaderConfiguration configuration = new ImageLoaderConfiguration.Builder(this)
                .memoryCache(new LruMemoryCache(MEMORY_SIZE))
                .diskCache(new UnlimitedDiscCache(new File(getCacheDir(),"caches")))
                .diskCacheSize(DISK_SIZE)
                .defaultDisplayImageOptions(options)
                .build();

        ImageLoader.getInstance().init(configuration);
    }
}


