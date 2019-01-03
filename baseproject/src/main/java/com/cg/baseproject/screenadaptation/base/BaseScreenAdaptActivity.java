package com.cg.baseproject.screenadaptation.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * @author sam
 * @version 1.0
 * @date 2018/8/12
 */
public abstract class BaseScreenAdaptActivity extends AppCompatActivity {

    protected abstract void initScreenAdaption();//初始化屏幕适配
    protected abstract int getActivityLayoutId();////布局中Fragment的ID
    protected abstract void initViews();//初始化界面
    protected abstract void initData();// 初始化数据,请求网络数据等

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initScreenAdaption();
        setContentView(getActivityLayoutId());
        initViews();
        initData();
    }
}
