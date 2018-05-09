package com.ivy.baseproject.test.sample;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cg.baseproject.base.BaseFragment;
import com.cg.baseproject.configs.IConstants;
import com.ivy.baseproject.test.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 时 间: 2017/3/18
 */

public class SampleFragment extends BaseFragment {

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
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected View getSuccessView() {
        View view = View.inflate(getActivity(), R.layout.fragment_sample, null);
        return view;
    }


    @Override
    protected Object requestData() {
        return IConstants.STATE_SUCCESSED;
    }


}
