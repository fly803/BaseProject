package com.cg.baseproject.base;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.cg.baseproject.R;
import com.cg.baseproject.manager.ActivityStackManager;
import com.cg.baseproject.manager.ScreenManager;
import com.cg.baseproject.utils.android.DialogUtils;
import com.cg.baseproject.view.view.progress.ProgressBarLayout;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 功能：activity基类
 *
 * @author sam
 * @Version : 1.0
 * @date : 2018.5.8
 */
public abstract class BaseActivity extends AppCompatActivity implements OnClickListener {
    private static final String TAG = "BaseActivity";
    private RelativeLayout relTitleBar;// 顶部导航栏
    private TextView moduleTextView;
    private ImageView topLeftButton;
    private TextView topRightText;
    private ImageView topRightImg;
    private FrameLayout mFraLayoutContent;
    private FrameLayout mFraLayoutHeadView;
    private RelativeLayout mRelLayoutBase;
    private ProgressBarLayout mLoadingBar;
    private RelativeLayout errorLayout;
    private RelativeLayout emptyLayout;
    private TextView tvEmtyHit;
    private TextView mResetButton;
    private Dialog mProgressDialog;//不可取消框
    private Dialog mProgressDialogCancle;//可取消加载框
    private int titlebarResId = R.layout.top_titlebar_base;
    Unbinder unbinder;
    private ScreenManager screenManager;
    protected boolean isStatusBar = false;//是否沉浸状态栏
    protected boolean isFullScreen = false;//是否允许全屏
    protected boolean isScreenPortrait = true;//是否禁止旋转屏幕

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityStackManager.getActivityStackManager().pushActivity(this);
        setScreenManager();
        screenManager = ScreenManager.getInstance();
        screenManager.setStatusBar(isStatusBar, this);
        screenManager.setScreenRoate(isScreenPortrait, this);
        screenManager.setFullScreen(isFullScreen, this);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "--->onRestart()");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "--->onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "--->onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "--->onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "--->onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "--->onDestroy()");
        ActivityStackManager.getActivityStackManager().popActivity(this);
        unbinder.unbind();
    }
    protected abstract void setScreenManager();
    protected abstract int getActivityLayoutId();////布局中Fragment的ID
    //    protected abstract void initView();//初始化界面
    //    protected abstract void registerListener();//绑定事件
    protected abstract void initData();// 初始化数据,请求网络数据等

    protected void onClickedTopLeftButtton(View view) {
        finish();
    }

    protected void onClickFailureResetButton(View view) {
    }

    protected void onClickTitlebarShare(View view) {
    }

    protected void onClickTitlebarRight(View view) {
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(R.layout.activity_base);
        mRelLayoutBase = (RelativeLayout) findViewById(R.id.relLayoutBase);
        mFraLayoutContent = (FrameLayout) findViewById(R.id.fraLayoutContent);
        mFraLayoutHeadView = (FrameLayout) findViewById(R.id.fraLayoutHeadView);
        LayoutInflater.from(this).inflate(titlebarResId, mFraLayoutHeadView, true);
        LayoutInflater.from(this).inflate(layoutResID, mFraLayoutContent, true);
        mLoadingBar = (ProgressBarLayout) findViewById(R.id.load_bar_layout);
        errorLayout = (RelativeLayout) findViewById(R.id.errorLayout);
        emptyLayout = (RelativeLayout) findViewById(R.id.emptyLayout);
        mResetButton = (TextView) findViewById(R.id.reset_button);
        mResetButton.setOnClickListener(this);

        //如果有使用黄油刀，请在这边加入即可
        unbinder = ButterKnife.bind(this);
    }

    @Override
    public void setContentView(View view, LayoutParams params) {
        super.setContentView(view, params);
        //如果有使用黄油刀，请在这边加入即可
        unbinder = ButterKnife.bind(this);
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        //如果有使用黄油刀，请在这边加入即可
        unbinder = ButterKnife.bind(this);
    }

    /**
     * 功能：如果自定义headview,一定要在setContentView前调用,否则无效
     *
     * @param layoutResID
     * @author:
     */
    protected void setHeadView(int layoutResID) {
        this.titlebarResId = layoutResID;
    }

    //隐藏头部
    protected void hideHeadView() {
        mFraLayoutHeadView.setVisibility(View.GONE);
    }

    //显示头部
    protected void showHeadView() {
        mFraLayoutHeadView.setVisibility(View.VISIBLE);
    }

    //显示加载布局
    protected void showLoadingBar() {
        showLoadingBar(false);
    }

    //隐藏加载布局
    public void hideLoadingBar() {
        mLoadingBar.hide();
    }

    public void showLoadingBar(boolean transparent) {
        mLoadingBar.setBackgroundColor(transparent ? Color.TRANSPARENT : getResources().getColor(R.color.main_bg));
        mLoadingBar.show();
    }

    //加载布局是否显示
    public boolean isLoadingBarShow() {
        return mLoadingBar.getVisibility() == View.VISIBLE;
    }

    //显示无网络布局
    protected void showErrorLayout() {
        errorLayout.setVisibility(View.VISIBLE);
    }

    //隐藏无网络布局
    protected void hideErrorLayout() {
        errorLayout.setVisibility(View.GONE);
    }

    //显示空页面
    public void showEmptyLayout(String emptyHit) {
        emptyLayout.setVisibility(View.VISIBLE);
        if (tvEmtyHit == null) {
            tvEmtyHit = (TextView) findViewById(R.id.tvEmtyHit);
        }
        tvEmtyHit.setText(emptyHit);
    }

    //隐藏空页面
    public void hideEmptyLayout() {
        emptyLayout.setVisibility(View.GONE);
    }

    protected RelativeLayout getErrorLayout() {
        return errorLayout;
    }

    //设置头部颜色
    protected void setTitleBarBackgroundColor(int color) {
        if (relTitleBar == null) {
            relTitleBar = (RelativeLayout) findViewById(R.id.relTitleBar);
        }
        relTitleBar.setBackgroundColor(getResources().getColor(color));
    }

    //设置头部文字颜色
    protected void setModuleTitleColor(int resourceId) {
        if (moduleTextView == null) {
            moduleTextView = (TextView) findViewById(R.id.tvTitlebarTitle);
        }
        moduleTextView.setTextColor(getResources().getColor(resourceId));
    }

    //设置头部局部
    protected void setModuleTitle(int resourceId) {
        if (moduleTextView == null) {
            moduleTextView = (TextView) findViewById(R.id.tvTitlebarTitle);
        }
        moduleTextView.setText(resourceId);
    }

    //设置头部文字
    protected void setModuleTitle(String text) {
        if (moduleTextView == null) {
            moduleTextView = (TextView) findViewById(R.id.tvTitlebarTitle);
        }
        moduleTextView.setVisibility(View.VISIBLE);
        moduleTextView.setText(text);
    }

    //设置头部图片
    protected void setModuleTitleImg(int resId) {
        if (moduleTextView == null) {
            moduleTextView = (TextView) findViewById(R.id.tvTitlebarTitle);
        }
        moduleTextView.setVisibility(View.VISIBLE);
        moduleTextView.setText("");
        moduleTextView.setCompoundDrawablesWithIntrinsicBounds(resId, 0, 0, 0);
    }

    //隐藏头部文字
    protected void hideModuleTitle() {
        if (moduleTextView == null) {
            moduleTextView = (TextView) findViewById(R.id.tvTitlebarTitle);
        }
        moduleTextView.setVisibility(View.GONE);
    }

    //隐藏左上
    protected void hideTopLeftButton() {
        if (topLeftButton == null) {
            topLeftButton = (ImageView) findViewById(R.id.ivTitlebarLeft);
        }
        topLeftButton.setVisibility(View.GONE);
    }

    //显示左上，默认为箭头
    protected void showTopLeftButton() {
        showTopLeftButton("", R.drawable.vbtn_arrow_back);
    }

    //显示左上，可添加文字 +箭头
    protected void showTopLeftButton(String text) {
        showTopLeftButton(text, R.drawable.vbtn_arrow_back);
    }

    //显示左上，无箭头
    protected void showTopLeftText(String text) {
        showTopLeftButton(text, 0);
    }

    protected void showTopLeftButton(String text, int resId) {
        if (topLeftButton == null) {
            topLeftButton = (ImageView) findViewById(R.id.ivTitlebarLeft);
            topLeftButton.setOnClickListener(this);
        }
        topLeftButton.setVisibility(View.VISIBLE);
    }

    protected RelativeLayout getlayoutBase() {
        return mRelLayoutBase;
    }

    protected View getHeadView() {
        return mFraLayoutHeadView;
    }

    protected ImageView getTopLeftButton() {
        if (topLeftButton == null) {
            topLeftButton = (ImageView) findViewById(R.id.ivTitlebarLeft);
        }
        return topLeftButton;
    }


    //显示右上图片，只有图片 不含文字
    protected void showTopRightImg(int img) {
        if (topRightImg == null) {
            topRightImg = (ImageView) findViewById(R.id.ivTitlebarRight);
            topRightImg.setOnClickListener(this);
        }
        topRightImg.setVisibility(View.VISIBLE);
        topRightImg.setImageResource(img);
    }

    //隐藏右上图片，只有图片 不含文字
    protected void hideTopRightImg() {
        if (topRightImg == null) {
            topRightImg = (ImageView) findViewById(R.id.ivTitlebarRight);
        }
        topRightImg.setVisibility(View.INVISIBLE);
    }

    protected void showLoadingDialogCancle() {
        if (mProgressDialogCancle == null) {
            mProgressDialogCancle = DialogUtils.createProgressDiaolg(this, "加载中...", true);
        }
        if (!mProgressDialogCancle.isShowing()) {
            mProgressDialogCancle.show();
        }
    }

    protected void hideLoadingDialogCancle() {
        if (mProgressDialogCancle == null) {
            mProgressDialogCancle = DialogUtils.createProgressDiaolg(this, "加载中...", true);
        }
        if (mProgressDialogCancle.isShowing()) {
            mProgressDialogCancle.dismiss();
        }
    }

    protected void showLoadingDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = DialogUtils.createProgressDiaolg(this, "加载中...", false);
        }
        if (!mProgressDialog.isShowing()) {
            mProgressDialog.show();
        }
    }

    protected void hideLoadingDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = DialogUtils.createProgressDiaolg(this, "加载中...", false);
        }
        if (mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    public View getmFraLayoutContent() {
        return mFraLayoutContent;
    }

    @Override
    public final void onClick(View v) {
        int i = v.getId();
        if (i == R.id.ivTitlebarLeft) {
            onClickedTopLeftButtton(v);
        } else if (i == R.id.top_right_text) {
            onClickTitlebarShare(v);
        } else if (i == R.id.ivTitlebarRight) {
            onClickTitlebarRight(v);
        } else if (i == R.id.reset_button) {
            onClickFailureResetButton(v);
            //如果使用黄油刀，请注释掉这里
        } else {

        }
    }

    /**
     * 跳转Activity
     * skip Another Activity
     *
     * @param activity
     * @param cls
     */
    public static void skipAnotherActivity(Activity activity, Class<? extends Activity> cls) {
        Intent intent = new Intent(activity, cls);
        activity.startActivity(intent);
        activity.finish();
    }

    //添加fragment
   /* protected void addFragment(BaseFragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction().replace(getFragmentContentId(), fragment, fragment.getClass().getSimpleName()).addToBackStack(fragment.getClass().getSimpleName()).commitAllowingStateLoss();
        }
    }*/

    //移除fragment
    /*protected void removeFragment() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            getSupportFragmentManager().popBackStack();
        } else {
            finish();
        }
    }*/

    /**
     * 退出应用
     * called while exit app.
     */
    public void exitApp() {
        ActivityStackManager.getActivityStackManager().popAllActivity();//remove all activity.
        System.exit(0);//system exit.
    }
}
