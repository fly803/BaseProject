package com.ivy.baseproject.test.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.ivy.baseproject.test.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NetImageActivity extends AppCompatActivity {

    @BindView(R.id.btn_movie)
    Button mBtnMovie;
    @BindView(R.id.btn_award)
    Button mBtnAward;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_netimage);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_movie, R.id.btn_award})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_movie:
                Intent intentMovie = new Intent(NetImageActivity.this, MovieListActivity.class);
                startActivity(intentMovie);
                break;
            case R.id.btn_award:
                Intent intentGirl = new Intent(NetImageActivity.this, GirlListActivity.class);
                startActivity(intentGirl);
                break;
        }
    }
}
