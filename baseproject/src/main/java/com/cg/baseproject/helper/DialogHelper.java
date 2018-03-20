package com.cg.baseproject.helper;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cg.baseproject.R;

/**
 * @author
 * @version 1.0
 * @date 2018/3/14
 */

public class DialogHelper {
    /**
     * 通用Dialog
     *
     */
    // 因为本类不是activity所以通过继承接口的方法获取到点击的事件
    public interface OnOkClickListener {
        abstract void onOkClick();
    }

    /**
     * Listener
     */
    public interface OnCancelClickListener {
        abstract void onCancelClick();
    }

    private static AlertDialog mDialog;

    public static void showDialog(Context context, String title, String content, final OnOkClickListener listenerYes,
                                  final OnCancelClickListener listenerNo) {
        showDialog(context, context.getString(android.R.string.ok), context.getString(android.R.string.cancel), title, content, listenerYes, listenerNo);
    }

    public static void showDialog(Context context, String ok, String cancel, String title, String content, final OnOkClickListener listenerYes,
                                  final OnCancelClickListener listenerNo) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(content);
        // 设置title
        builder.setTitle(title);
        // 设置确定按钮，固定用法声明一个按钮用这个setPositiveButton
        builder.setPositiveButton(ok,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // 如果确定被电击
                        if (listenerYes != null) {
                            listenerYes.onOkClick();
                        }
                        mDialog = null;
                    }
                });
        // 设置取消按钮，固定用法声明第二个按钮要用setNegativeButton
        builder.setNegativeButton(cancel,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // 如果取消被点击
                        if (listenerNo != null) {
                            listenerNo.onCancelClick();
                        }
                        mDialog = null;
                    }
                });

        // 控制这个dialog可不可以按返回键，true为可以，false为不可以
        builder.setCancelable(false);
        // 显示dialog
        mDialog = builder.create();
        if (!mDialog.isShowing())
            mDialog.show();
    }

    public static void showDialog(Context context, int ok, int cancel, int title, int content, final OnOkClickListener listenerYes,
                                  final OnCancelClickListener listenerNo) {
        showDialog(context, context.getString(ok), context.getString(cancel), context.getString(title), context.getString(content), listenerYes, listenerNo);
    }

    static ProgressDialog progressDlg = null;

    /**
     * 启动进度条
     *
     * @param strMessage 进度条显示的信息
     * @param //         当前的activity
     */
    public static void showProgressDlg(Context ctx, String strMessage) {

        if (null == progressDlg) {
            if (ctx == null) return;
            progressDlg = new ProgressDialog(ctx);
            //设置进度条样式
            progressDlg.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            //提示的消息
            progressDlg.setMessage(strMessage);
            progressDlg.setIndeterminate(false);
            progressDlg.setCancelable(true);
            progressDlg.show();
        }
    }

    public static void showProgressDlg(Context ctx) {
        showProgressDlg(ctx, "");
    }

    /**
     * 结束进度条
     */
    public static void stopProgressDlg() {
        if (null != progressDlg && progressDlg.isShowing()) {
            progressDlg.dismiss();
            progressDlg = null;
        }
        if (null != dialog && dialog.isShowing()) {
            dialog.dismiss();
            dialog = null;
        }
    }

    private static Dialog dialog;

    public static void showDialogForLoading(Context context, String msg, boolean cancelable) {
//        if (null == dialog) {
//            if (null == context) return;
//            View view = LayoutInflater.from(context).inflate(R.layout.layout_loading_dialog, null);
//            TextView loadingText = (TextView)view.findViewById(R.id.loading_tip_text);
//            loadingText.setText(msg);
//
//            dialog = new Dialog(context, R.style.loading_dialog_style);
//            dialog.setCancelable(cancelable);
//            dialog.setCanceledOnTouchOutside(cancelable);
//            dialog.setContentView(view, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
//            Activity activity = (Activity) context;
//            if (activity.isFinishing()) return;
//            dialog.show();
//        }
    }

}
