package com.ivy.baseproject.test.sample;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.cg.baseproject.base.BaseActivity;
import com.ivy.baseproject.test.R;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author sam
 * @version 1.0
 * @date 2018/5/5
 */
public class SampleActivity extends BaseActivity {
    @BindView(R.id.btnHideHead)
    Button mBtnHideHead;
    @BindView(R.id.btnShowHead)
    Button mBtnShowHead;
    @BindView(R.id.btnSetTitle)
    Button mBtnSetTitle;
    @BindView(R.id.btnTopLeft)
    Button mBtnTopLeft;
    @BindView(R.id.btnTopRight)
    Button mBtnTopRight;
    @BindView(R.id.btnShowLoadDialog)
    Button mBtnShowLoadDialog;
    @BindView(R.id.btnShowLoadDialogCancle)
    Button mBtnShowLoadDialogCancle;
    @BindView(R.id.btnShowLoad)
    Button mBtnShowLoad;
    @BindView(R.id.btnShowEror)
    Button mBtnShowEror;
    @BindView(R.id.btnShowEmpty)
    Button mBtnShowEmpty;
    private Context mContext;

    @Override
    protected void setScreenManager() {
        super.isScreenPortrait = true;
        super.isFullScreen = true;
        super.isScreenPortrait = true;
    }

    @Override
    protected int getActivityLayoutId() {
        return R.layout.activity_second;
    }

    @Override
    protected void initData() {
        
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getActivityLayoutId());
        //因为BaseActivity的BufferKnife已经做过绑定view，因此子类不需要再进行绑定unbinder = ButterKnife.bind(this);
        setModuleTitle("第二页");
        mContext = this;
    }

    @Override
    protected void onClickFailureResetButton(View view) {
        super.onClickFailureResetButton(view);
        Toast.makeText(mContext, "onClickFailureResetButton点击重新加载", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onClickTitlebarRight(View view) {
        super.onClickTitlebarRight(view);
        Toast.makeText(mContext, "点击标题栏右侧图标", Toast.LENGTH_SHORT).show();
    }



    @OnClick({R.id.btnHideHead, R.id.btnShowHead, R.id.btnSetTitle, R.id.btnTopLeft, R.id.btnTopRight, R.id.btnShowLoadDialog, R.id.btnShowLoadDialogCancle, R.id.btnShowLoad, R.id.btnShowEror, R.id.btnShowEmpty})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnHideHead:
                hideHeadView();
                break;
            case R.id.btnShowHead:
                showHeadView();
                break;
            case R.id.btnSetTitle:
                setModuleTitle("从新设置标题");
                break;
            case R.id.btnTopLeft:
                //只显示默认返回箭头
                showTopLeftButton();
                //文字
                //            showTopLeftButton("返回");
                //也可以自己设置返回箭头
                //            showTopLeftButton("返回", R.mipmap.ic_launcher);
                break;
            case R.id.btnTopRight:
                //设置文字
                showTopRightImg(R.drawable.vbtn_titlebar_me);
                //图片
                //            showTopRightImg(R.mipmap.share);
                break;
            case R.id.btnShowLoadDialog:
                showLoadingDialog();
                new Thread() {
                    @Override
                    public void run() {
                        super.run();
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        SampleActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                hideLoadingDialog();
                            }
                        });
                    }
                }.start();
                break;
            case R.id.btnShowLoadDialogCancle:
                showLoadingDialogCancle();
                break;
            case R.id.btnShowLoad:
                showLoadingBar();
                new Thread() {
                    @Override
                    public void run() {
                        super.run();
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        SampleActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                hideLoadingBar();
                            }
                        });
                    }
                }.start();
                break;
            case R.id.btnShowEror:
                showErrorLayout();
                break;
            case R.id.btnShowEmpty:
                showEmptyLayout("空页面");
                break;
        }
    }
}
