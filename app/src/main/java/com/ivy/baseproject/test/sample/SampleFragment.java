package com.ivy.baseproject.test.sample;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cg.baseproject.base.BaseFragment;
import com.ivy.baseproject.test.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 时 间: 2017/3/18
 */

public class SampleFragment extends BaseFragment {

//    @BindView(R.id.tvFragmentSample)
//    TextView mTvFragmentSample;

    @Override
    protected int getFragmentLayoutId() {
        return R.layout.fragment_sample;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void registerListener() {

    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }


//    @OnClick(R.id.tvFragmentSample)
//    public void onViewClicked() {
//        
//    }
}
