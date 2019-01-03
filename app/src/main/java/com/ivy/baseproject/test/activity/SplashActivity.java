package com.ivy.baseproject.test.activity;

import android.content.Intent;
import android.os.Handler;

import com.cg.baseproject.screenadaptation.base.BaseScreenAdaptActivity;
import com.cg.baseproject.utils.android.ScreenUtils;
import com.ivy.baseproject.test.R;
import com.ivy.baseproject.test.api.AppConfig;


public class SplashActivity extends BaseScreenAdaptActivity {
    @Override
    protected void initScreenAdaption() {
        if (ScreenUtils.isPortrait()) {
            ScreenUtils.adaptScreen4VerticalSlide(this, AppConfig.widthInPx);
        } else {
            ScreenUtils.adaptScreen4HorizontalSlide(this, AppConfig.heightInPx);
        }
    }

    @Override
    protected int getActivityLayoutId() {
        return R.layout.activity_splash_layout;
    }

    @Override
    protected void initViews() {
        new Handler().postDelayed(new Runnable() {
            public void run() {
                /* Create an Intent that will start the Main WordPress Activity. */
                startActivity(new Intent(SplashActivity.this,ResolutionAdaptionDemoActivity.class));
                SplashActivity.this.finish();
            }
        }, 1000);
    }

    @Override
    protected void initData() {

    }
}
