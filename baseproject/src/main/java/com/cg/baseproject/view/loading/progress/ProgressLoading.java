package com.cg.baseproject.view.loading.progress;


import android.app.Dialog;
import android.content.Context;
import android.widget.TextView;

import com.cg.baseproject.R;

public class ProgressLoading {
	private Context mContext;
	private Dialog progressDialog;

	public ProgressLoading(Context context) {
		mContext = context;
	}

	public Dialog loadDialog() {
		progressDialog = new Dialog(mContext, R.style.progress_dialog);
        progressDialog.setContentView(R.layout.progress_loading);
        progressDialog.setCancelable(true);
        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        TextView msg = (TextView) progressDialog.findViewById(R.id.id_tv_loadingmsg);
        msg.setText("玩命加载中...");
        progressDialog.setCanceledOnTouchOutside(true);
        progressDialog.show();
        return progressDialog;
	}

	public void removeDialog() {
		progressDialog.dismiss();
	}
}
    