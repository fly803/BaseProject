package com.cg.baseproject.utils.apkupdate;

import android.app.DownloadManager;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;

import com.cg.baseproject.BaseApplication;
import com.cg.baseproject.utils.android.AppUtils;


/**
 * Created by sam on 2017/6/1.
 */

public class ApkDownloadManager {

    private static final String TAG = "ApkDownloadManager";

    private DownloadManager downloadManager;

    private Context context;

    private static ApkDownloadManager instance;

    private ApkDownloadManager(Context context) {
        downloadManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
        context = context.getApplicationContext();
    }

    public static ApkDownloadManager getInstance(Context context) {
        if (instance == null) {
            instance = new ApkDownloadManager(context);
        }

        return instance;
    }

    /**
     * 下载
     * @param uri uri
     * @param title 标题
     * @param description 描述
     * @return
     */
    public long download(String uri, String title, String description) {
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(uri));

        //设置下载标题描述等信息
        request.setTitle(title);
        request.setDescription(description);
        request.setMimeType("application/vnd.android.package-archive");

        //在WIFI的情况下才会下载
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI);
//        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE);

        //设置下载完成后会在通知栏显示
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);

        //设置下载文件保存的位置
        //这种保存的形式为file:///storage/emulated/0/Android/data/your-package/files/Download/packageName.apk
        request.setDestinationInExternalFilesDir(context, Environment.DIRECTORY_DOWNLOADS, AppUtils.getPackageName(BaseApplication.getContext()));
//        LogUtils.d(TAG, "APK save path is : " + Environment.DIRECTORY_DOWNLOADS);

        //保存路径还可以自定义
        //request.setDestinationUri();

        return downloadManager.enqueue(request);
    }

    /**
     * 获取APk保存路径
     * @param downloadId
     * @return
     */
    public String getDownloadPath(long downloadId) {
        DownloadManager.Query query = new DownloadManager.Query().setFilterById(downloadId);
        Cursor cursor = downloadManager.query(query);
        if (null != cursor) {
            try {
                if (cursor.moveToFirst()) {
                    return cursor.getString(cursor.getColumnIndexOrThrow(DownloadManager.COLUMN_LOCAL_URI));
                }
            } finally {
                cursor.close();
            }
        }

        return null;
    }

    /**
     * 获取apk保存的uri
     * @param downloadId
     * @return
     */
    public Uri getDownloadUri(long downloadId) {
        return downloadManager.getUriForDownloadedFile(downloadId);
    }

    public DownloadManager getDownloadManager() {
        return downloadManager;
    }

    /**
     * 获取下载状态
     * @param downloadId
     * @return
     */
    public int getDownloadStatus(long downloadId) {
        DownloadManager.Query query = new DownloadManager.Query().setFilterById(downloadId);
        Cursor c = downloadManager.query(query);
        if (c != null) {
            try {
                if (c.moveToFirst()) {
                    return c.getInt(c.getColumnIndexOrThrow(DownloadManager.COLUMN_STATUS));

                }
            } finally {
                c.close();
            }
        }
        return -1;
    }

}
