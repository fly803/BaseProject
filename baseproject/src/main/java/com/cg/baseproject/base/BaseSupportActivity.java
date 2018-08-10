package com.cg.baseproject.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.cg.baseproject.R;
import com.cg.baseproject.manager.ActivityStackManager;
import com.cg.baseproject.manager.ScreenManager;
import com.cg.baseproject.manager.ScreenManagerSupportActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportActivity;

/**
 * @author sam
 * @version 1.0
 * @date 2018/5/4
 * https://blog.csdn.net/xx244488877/article/details/65937778
 */
public abstract class BaseSupportActivity extends SupportActivity {
    private static final String TAG = "BaseSupportActivity";
    protected LinearLayout badnetworkLayout, loadingLayout,baseactivityLayout;
    protected LayoutInflater inflater;
    protected boolean isStatusBar = false;//是否沉浸状态栏
    protected boolean isFullScreen = false;//是否允许全屏
    protected boolean isScreenPortrait = true;//是否禁止旋转屏幕
    protected Context ctx;//Context
    private boolean isDebug;// 是否输出日志信息
    protected abstract int getActivityLayoutId();////布局中Fragment的ID
    protected abstract void initViews();//初始化界面
    protected abstract void registerListener();//绑定事件
    protected abstract void initData();// 初始化数据,请求网络数据等
    //布局中Fragment的ID
    protected abstract int getFragmentContentId();
    protected abstract void setScreenManager();
    private ScreenManagerSupportActivity screenManager;
    Unbinder unbinder;
    public static final int FIRST = 0;
    public static final int SECOND = 1;
    public static final int THIRD = 2;
    public static final int FOURTH = 3;
    public static final int FIFTH = 4;
    public static final int CENTER = 10;
    public static int position = 0;
    public static int prePosition = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityStackManager.getActivityStackManager().pushActivity(this);
        initScreenManage();
        setContentView(getActivityLayoutId());
        inflater = LayoutInflater.from(this);
        unbinder = ButterKnife.bind(this);
    }
    

    private void initScreenManage(){
        setScreenManager();
        screenManager = ScreenManagerSupportActivity.getInstance();
        screenManager.setStatusBar(isStatusBar, this);
        screenManager.setScreenRoate(isScreenPortrait, this);
        screenManager.setFullScreen(isFullScreen, this);
    }
    
    /**
     * 跳转Activity
     * skip Another Activity
     *
     * @param activity
     * @param cls
     */
    public static void skipAnotherActivity(Activity activity, Class<? extends Activity> cls) {
        Intent intent = new Intent(activity, cls);
        activity.startActivity(intent);
        activity.finish();
    }

    /**
     * 退出应用
     * called while exit app.
     */
    public void exitLogic() {
        ActivityStackManager.getActivityStackManager().popAllActivity();//remove all activity.
        System.exit(0);//system exit.
    }
    

    //添加fragment
    protected void addFragment(BaseSupportFragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction().replace(getFragmentContentId(),
                    fragment, fragment.getClass().getSimpleName())
                    .addToBackStack(fragment.getClass().getSimpleName())
                    .commitAllowingStateLoss();
        }
    }

    //移除fragment
    protected void removeFragment() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            getSupportFragmentManager().popBackStack();
        } else {
            finish();
        }
    }

    //返回键返回事件  
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (KeyEvent.KEYCODE_BACK == keyCode) {
            if (getSupportFragmentManager().getBackStackEntryCount() == 1) {
                finish();
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 防止快速点击
     * @return
     */
    private boolean fastClick() {
        long lastClick = 0;
        if (System.currentTimeMillis() - lastClick <= 1000) {
            return false;
        }
        lastClick = System.currentTimeMillis();
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
        initViews();
        registerListener();
        initData();
        ctx = this;
        Log.i(TAG, "--->onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "--->onResume()");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "--->onRestart()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "--->onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "--->onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "--->onDestroy()");
        ActivityStackManager.getActivityStackManager().popActivity(this);
        if(unbinder!=null){
            unbinder.unbind();
        }
    }
}
