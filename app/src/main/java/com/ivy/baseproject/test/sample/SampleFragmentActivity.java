package com.ivy.baseproject.test.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.cg.baseproject.base.BaseActivity;
import com.cg.baseproject.base.BaseSupportActivity;
import com.cg.baseproject.base.BaseSupportFragment;
import com.cg.baseproject.base.BaseTempActivity;
import com.ivy.baseproject.test.R;

import butterknife.ButterKnife;

public class SampleFragmentActivity extends BaseSupportActivity implements BaseSupportFragment.OnBackToFirstListener{

    @Override
    protected int getActivityLayoutId() {
        return R.layout.activity_samplefragment_main;
    }

    @Override
    protected void initViews() {
        
    }

    @Override
    protected void registerListener() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected int getFragmentContentId() {
        return 0;
    }

    @Override
    protected void setScreenManager() {

    }


    @Override
    public void onBackToFirstFragment() {
        
    }
}
