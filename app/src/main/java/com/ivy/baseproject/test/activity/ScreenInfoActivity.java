package com.ivy.baseproject.test.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.ivy.baseproject.test.R;
import com.ivy.baseproject.test.utils.ScreenUtils;

public class ScreenInfoActivity extends AppCompatActivity {

    private final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * 返回包括虚拟键在内的总的屏幕高度
     * 即使虚拟按键显示着，也会加上虚拟按键的高度
     */
    public void getTotalScreenHeight(View view) {
        Log.e(TAG, "getTotalScreenHeight " + ScreenUtils.getTotalScreenHeight(this));
    }

    /**
     * 返回屏幕的宽度
     */
    public void getScreenWidth(View view) {
        Log.e(TAG, "getScreenWidth " + ScreenUtils.getScreenWidth(this));
    }

    /**
     * 返回屏幕可用高度
     * 当显示了虚拟按键时，会自动减去虚拟按键高度
     */
    public void getAvailableScreenHeight(View view) {
        Log.e(TAG, "getAvailableScreenHeight " + ScreenUtils.getAvailableScreenHeight(this));
    }

    /**
     * 状态栏高度
     */
    public void getStatusBarHeight(View view) {
        Log.e(TAG, "getStatusBarHeight " + ScreenUtils.getStatusBarHeight(this));
    }

    /**
     * 获取虚拟按键的高度
     * 会根据当前是否有显示虚拟按键来返回相应的值
     * 即如果隐藏了虚拟按键，则返回零
     */
    public void getVirtualBarHeightIfRoom(View view) {
        Log.e(TAG, "getVirtualBarHeightIfRoom " + ScreenUtils.getVirtualBarHeightIfRoom(this));
    }

    /**
     * 获取虚拟按键的高度，不论虚拟按键是否显示都会返回其固定高度
     */
    public void getVirtualBarHeight(View view) {
        Log.e(TAG, "getVirtualBarHeight " + ScreenUtils.getVirtualBarHeight(this));
    }

    /**
     * 标题栏高度，如果隐藏了标题栏则返回零
     */
    public void getTitleHeight(View view) {
        Log.e(TAG, "getTitleHeight " + ScreenUtils.getTitleHeight(this));
    }

}
