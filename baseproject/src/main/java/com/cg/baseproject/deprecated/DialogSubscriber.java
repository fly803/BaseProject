package com.cg.baseproject.deprecated;

import android.app.ProgressDialog;
import android.content.Context;


/**
 * @author liuml
 * @explain 自定义对话框订阅器
 * @time 2017/3/4 16:39
 */

public abstract class DialogSubscriber<T> extends BaseSubscriber<T> {

    ProgressDialog loadingDialog;
    private String loadingMsg;
    private boolean isShowLoading = true;

    public DialogSubscriber(Context mContext) {
        super(mContext);
        this.loadingMsg = "加载中...";
    }

    public DialogSubscriber(Context context, String loadingMsg) {
        super(context);
        this.loadingMsg = loadingMsg;
    }

    /**
     * @param isShowLoading 是否显示对话框
     */
    public DialogSubscriber(Context context, boolean isShowLoading) {
        super(context);
        this.isShowLoading = isShowLoading;
    }


    protected void showDialog() {
        dismissDialog();
        loadingDialog = new ProgressDialog(mContext);
        loadingDialog.show();
    }


    protected void dismissDialog() {
        if (loadingDialog != null && loadingDialog.isShowing())
            loadingDialog.cancel();

    }


//    @Override
//    public void onStart() {
//        super.onStart();
//        if (isShowLoading) {
//            showDialog();
//        }
//    }


    @Override
    public void onError(Throwable e) {
        super.onError(e);
        if (isShowLoading) {
            dismissDialog();
        }
    }


//    @Override
//    public void onCompleted() {
//        if (isShowLoading) {
//            dismissDialog();
//        }
//    }
}
