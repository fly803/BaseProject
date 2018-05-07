package com.ivy.baseproject.test.sample;

import android.os.Bundle;
import android.widget.TextView;

import com.cg.baseproject.base.BaseActivity;
import com.cg.baseproject.utils.ToastUtils;
import com.ivy.baseproject.test.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author sam
 * @version 1.0
 * @date 2018/5/5
 */
public class SampleActivity extends BaseActivity {

    @BindView(R.id.tvActivitySample)
    TextView mTvActivitySample;

    @Override
    protected int getActivityLayoutId() {
        return R.layout.activity_sample;
    }

    @Override
    protected void initView() {
//        showLoadingLayout();
    }

    @Override
    protected void registerListener() {

    }

    @Override
    protected void initData() {

    }


    @OnClick(R.id.tvActivitySample)
    public void onViewClicked() {
        ToastUtils.showShort("ActivitySample");
    }
}
