package com.cg.baseproject.utils.apkupdate;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.util.Log;

import com.cg.baseproject.BaseApplication;
import com.cg.baseproject.utils.android.SharedPreferencesUtils;
import com.cg.baseproject.utils.android.AppUtils;

/**
 * Created by sam on 2017/6/1.
 * Apk更新工具类
 */

public class ApkUpdateUtils {

    private static final String TAG = "ApkUpdateUtils";

    private static final String DOWNLOAD_ID = "downloadId";

    public static void download(Context context, String url, String title) {
        long downloadId = (long) SharedPreferencesUtils.getParam(DOWNLOAD_ID, -1L);
        if (downloadId != -1L) {
            ApkDownloadManager apkDownloadManager = ApkDownloadManager.getInstance(context);
            int status = apkDownloadManager.getDownloadStatus(downloadId);
            if (status == DownloadManager.STATUS_SUCCESSFUL) {
                //启动更新界面
                Uri uri = apkDownloadManager.getDownloadUri(downloadId);
                if (uri != null) {
                    if (compare(getApkInfo(context, uri.getPath()))) {
                        startInstall(context, uri);
                        return;
                    } else {
                        apkDownloadManager.getDownloadManager().remove(downloadId);
                    }
                }
                start(context, url, title);
            } else if (status == DownloadManager.STATUS_FAILED) {
                start(context, url, title);
            } else {
                Log.d(TAG, "apk is already downloading");
            }
        } else {
            start(context, url, title);
        }
    }

    private static void start(Context context, String url, String title) {
        long id = ApkDownloadManager.getInstance(context).download(url, title, "下载完成后点击打开");
        SharedPreferencesUtils.setParam(DOWNLOAD_ID, id);
        Log.d(TAG, "apk start download " + id);
    }

    public static void startInstall(Context context, Uri uri) {
        Intent install = new Intent(Intent.ACTION_VIEW);
        install.setDataAndType(uri, "application/vnd.android.package-archive");
        install.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(install);
    }

    /**
     * 获取下载的apk信息
     * @param context
     * @param path
     * @return
     */
    private static PackageInfo getApkInfo(Context context, String path) {
        PackageManager manager = context.getPackageManager();
        PackageInfo info = manager.getPackageArchiveInfo(path, PackageManager.GET_ACTIVITIES);
        if (null != info) {
            return info;
        }

        return null;
    }

    /**
     * APK版本比较
     * @param info 下载的apk信息
     * @return 下载的apk版本号大于当前版本号时返回true，反之返回false
     */
    private static boolean compare(PackageInfo info) {
        if (info == null) {
            return false;
        }

        if (info.packageName.equals(AppUtils.getPackageName(BaseApplication.getContext()))) {
            if (info.versionCode > AppUtils.getVersionCode(BaseApplication.getContext())) {
                return true;
            }
        }

        return false;
    }
}
