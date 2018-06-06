package com.cg.baseproject.utils.apkupdate;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.cg.baseproject.utils.android.SharedPreferencesUtils;


/**
 * Created by sam on 2017/6/1.
 * 安装广播
 */

public class ApkInstallReceiver extends BroadcastReceiver {

    private static final String TAG = "ApkInstallReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(DownloadManager.ACTION_DOWNLOAD_COMPLETE)) {
            long downloadId = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);
            long spApkId = (long) SharedPreferencesUtils.getParam("downloadId", -1L);
            if (downloadId == spApkId) {
                installApk(context, downloadId);
            }
        }
    }

    private void installApk(Context context, long downloadId) {

        DownloadManager downloadManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
        Intent intent = new Intent(Intent.ACTION_VIEW);
        Uri fileUri = downloadManager.getUriForDownloadedFile(downloadId);
        if (null != fileUri) {
//            LogUtils.d(TAG, fileUri.toString());
            intent.setDataAndType(fileUri, "application/vnd.android.package-archive");
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        } else {
//            LogUtils.d(TAG, "无APK，下载失败");
        }
    }
}
