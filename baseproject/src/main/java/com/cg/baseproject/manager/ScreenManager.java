package com.cg.baseproject.manager;

/**
 * @author
 * @version 1.0
 * @date 2018/5/4
 */
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.view.Window;
import android.view.WindowManager;

import com.cg.baseproject.base.BaseActivityOld;

/**
 * 屏幕管理类
 */
public class ScreenManager {
    private static ScreenManager instance;

    private ScreenManager() {
    }

    public synchronized static ScreenManager getInstance() {
        if (instance == null) {
            instance = new ScreenManager();
        }
        return instance;
    }

    /**
     * 窗口全屏
     */
    public void setFullScreen(boolean isChange,BaseActivityOld mActivity) {
        if(!isChange){
            return;
        }
        mActivity.getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        mActivity.requestWindowFeature(Window.FEATURE_NO_TITLE);
    }

    /**
     * [沉浸状态栏]
     */
    public void setStatusBar(boolean isChange,BaseActivityOld mActivity) {
        if (!isChange){
            return;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // 透明状态栏
            mActivity.getWindow().addFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            // 透明导航栏
            mActivity.getWindow().addFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
    }

    /**
     * 旋转屏幕
     **/
    public void setScreenRoate(boolean isPortrait, BaseActivityOld mActivity) {
        if (isPortrait) {
            mActivity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        } else {
            mActivity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
    }
}
