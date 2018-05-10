package com.ivy.baseproject.test.sample;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.cg.baseproject.base.BaseFragment;
import com.cg.baseproject.configs.IConstants;
import com.ivy.baseproject.test.R;

/**
 * 时 间: 2017/3/18
 */

public class SampleFragment extends BaseFragment {

    @Override
    protected void onClickFailureResetButton(View view) {
        super.onClickFailureResetButton(view);
        Toast.makeText(getActivity(), "SampleFragment网络错误重新加载", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected View getSuccessView() {
        View view = View.inflate(getActivity(), R.layout.fragment_sample, null);
        return view;
    }


    @Override
    protected Object requestData() {
        return IConstants.STATE_FAILED;
    }


}
