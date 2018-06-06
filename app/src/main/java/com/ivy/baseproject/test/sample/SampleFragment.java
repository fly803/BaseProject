package com.ivy.baseproject.test.sample;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.cg.baseproject.base.BaseContentPageFragment;
import com.cg.baseproject.base.BaseSupportFragment;
import com.cg.baseproject.configs.IConstants;
import com.ivy.baseproject.test.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 时 间: 2017/3/18
 */

public class SampleFragment extends BaseSupportFragment {
    @BindView(R.id.tvFragmentSample)
    TextView mTvFragmentSample;

    @Override
    protected int getFragmentLayoutId() {
        return  R.layout.fragment_sample;
    }

    @Override
    protected void initViews() {
        new Handler().postDelayed(new Runnable(){
            public void run() {
                //execute the task   
                pdLoading.cancel();
            }
        }, 1000);
    }

    @Override
    protected void registerListener() {

    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @OnClick(R.id.tvFragmentSample)
    public void onViewClicked() {
        Toast.makeText(getActivity(), "SampleFragment点击测试", Toast.LENGTH_SHORT).show();
    }
}
