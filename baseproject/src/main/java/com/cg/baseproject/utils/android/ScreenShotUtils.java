package com.cg.baseproject.utils.android;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Build;
import android.view.View;

import com.cg.baseproject.utils.FileUtils;

import java.io.File;

import github.nisrulz.screenshott.ScreenShott;

/**
 * @author
 * @version 1.0
 * @date 2018/5/31
 */
public class ScreenShotUtils {
    @TargetApi(Build.VERSION_CODES.M)
    public static void easyPermissionCheck(Activity activity) {
        String[] perms = {
                // 把你想要申请的权限放进这里就行，注意用逗号隔开
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
        };
        boolean flag = EasyPermissionUtils.checkPermission(activity,perms);
        if(!flag){
            ToastUtils.showShort("没有相关授权，请去登录页从新进行授权");
            //            Constants.isLogin = false;
            //            activity.startActivity(new Intent(activity, LoginActivity.class));
        }
    }


    private static Bitmap loadBitmapFromView(View v) {
        int w = v.getWidth();
        int h = v.getHeight();
        Bitmap bmp = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(bmp);
        //        c.drawColor(Color.WHITE);
        /** 如果不设置canvas画布为白色，则生成透明 */
        v.layout(0, 0, w, h);
        v.draw(c);
        return bmp;
    }

    public static String getViewBitmapPath(Activity activity, View view, String dirName, String fileName){
        String bitmapFilePath = null;
        File file = null;
        try {
            file = FileUtils.saveBitmapFile(activity,dirName,fileName,loadBitmapFromView(view));
            bitmapFilePath = file.getAbsolutePath();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmapFilePath;
    }

    public static String getViewBitmapPath(Activity activity,View view){
        String bitmapFilePath = null;
        File file = null;
        try {
            file = ScreenShott.getInstance().saveScreenshotToPicturesFolder(activity, loadBitmapFromView(view), "my_screenshot_filename");
            bitmapFilePath = file.getAbsolutePath();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmapFilePath;
    }
}
