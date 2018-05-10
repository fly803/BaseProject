package com.ivy.baseproject.test.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.cg.baseproject.base.BaseActivity;
import com.cg.baseproject.base.BaseSupportActivity;
import com.cg.baseproject.base.BaseTempActivity;
import com.ivy.baseproject.test.R;

import butterknife.ButterKnife;

public class SampleFragmentActivity extends BaseTempActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_samplefragment_main);
        ButterKnife.bind(this);
    }
    
}
