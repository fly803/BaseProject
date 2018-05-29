package com.cg.baseproject.utils.android;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cg.baseproject.R;
import com.cg.baseproject.utils.StringUtils;
import com.cg.baseproject.view.view.progress.ProgressBarLayout;

/**
 * Created by Administrator on 2016/12/1.
 */

public class DialogUtils {
    public static Dialog createProgressDiaolg(Context context, String prompt,boolean isCancle) {
        Dialog mProgressDialog = new Dialog(context, R.style.CustomTheme_Dialog);
        ProgressBarLayout mProgressDialogView = (ProgressBarLayout) View.inflate(context, R.layout.progressbar_dialog, null);
        if (!StringUtils.isEmpty(prompt)) {
            TextView promptTv = (TextView) mProgressDialogView.findViewById(R.id.tvProgress);
            promptTv.setText(prompt);
        }
        mProgressDialog.setContentView(mProgressDialogView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        mProgressDialog.setCanceledOnTouchOutside(isCancle);
        mProgressDialog.setCancelable(isCancle);
        return mProgressDialog;
    }
}
