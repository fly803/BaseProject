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

import com.cg.baseproject.base.BaseActivity;
import com.cg.baseproject.base.BaseSupportActivity;

/**
 * 屏幕管理类
 */
public class ScreenManagerSupportActivity {
    private static ScreenManagerSupportActivity instance;

    private ScreenManagerSupportActivity() {
    }

    public synchronized static ScreenManagerSupportActivity getInstance() {
        if (instance == null) {
            instance = new ScreenManagerSupportActivity();
        }
        return instance;
    }

    /**
     * 窗口全屏
     */
    public void setFullScreen(boolean isChange,BaseSupportActivity mActivity) {
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
    public void setStatusBar(boolean isChange,BaseSupportActivity mActivity) {
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
    public void setScreenRoate(boolean isPortrait, BaseSupportActivity mActivity) {
        if (isPortrait) {
            mActivity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        } else {
            mActivity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
    }
}
