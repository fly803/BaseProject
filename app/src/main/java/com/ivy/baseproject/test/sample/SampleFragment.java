package com.ivy.baseproject.test.sample;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

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
    protected void onClickFailureResetButton(View view) {
        super.onClickFailureResetButton(view);
        Toast.makeText(getActivity(), "网络错误从新加载", Toast.LENGTH_SHORT).show();
        
    }

    @Override
    protected int getFragmentLayoutId() {
        return 0;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void registerListener() {

    }


    @Override
    protected View getSuccessView() {
        View view = View.inflate(getActivity(), R.layout.fragment_sample, null);
        return view;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        
    }

    @Override
    protected Object requestData() {
        new Handler().postDelayed(new Runnable(){
            public void run() {
                //execute the task   
                refreshPage(IConstants.STATE_FAILED);
            }
        }, 3000);
        return IConstants.STATE_LOADING;
    }

    @OnClick(R.id.tvFragmentSample)
    public void onViewClicked() {
        Toast.makeText(getActivity(), "SampleFragment点击测试", Toast.LENGTH_SHORT).show();
    }
}
