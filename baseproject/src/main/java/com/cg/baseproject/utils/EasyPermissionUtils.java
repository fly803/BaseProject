package com.cg.baseproject.utils;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import java.util.List;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * @author
 * @version 1.0
 * @date 2018/3/17
 */

public class EasyPermissionUtils {
    private static final String TAG = "EasyPermissions";

    public interface PermissionCallbacks extends ActivityCompat.OnRequestPermissionsResultCallback {
        void onPermissionsGranted(int requestCode, List<String> perms);
        void onPermissionsDenied(int requestCode, List<String> perms);
    }

    private static StringBuffer logStringBuffer = new StringBuffer();

    // 查看权限是否已申请
    @RequiresApi(api = Build.VERSION_CODES.M)
    public static String checkPermissions(Context context, String... permissions) {
        logStringBuffer.delete(0, logStringBuffer.length());
        for (String permission : permissions) {
            logStringBuffer.append(permission);
            logStringBuffer.append(" is applied? \n     ");
            logStringBuffer.append(isAppliedPermission(context, permission));
            logStringBuffer.append("\n\n");
        }
        return logStringBuffer.toString();
    }

    /**
     *
     * @param context
     * return true:已经获取权限
     * return false: 未获取权限，主动请求权限
     */
    // @AfterPermissionGranted 是可选的
    public static boolean checkPermission(Activity context,String[] perms) {
        return EasyPermissions.hasPermissions(context, perms);
    }

    // 查看权限是否已申请
    @RequiresApi(api = Build.VERSION_CODES.M)
    private static boolean isAppliedPermission(Context context, String permission) {
        return context.checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED;
    }

    /**
     * 请求权限
     *
     第一个参数：Context对象
     第二个参数：权限弹窗上的文字提示语。告诉用户，这个权限用途。
     第三个参数：这次请求权限的唯一标示，code。
     第四个参数 : 一些系列的权限。
     * @param context
     */
    public static void requestPermission(Activity context, String tip, int requestCode, String[] perms) {
        EasyPermissions.requestPermissions(context, tip,requestCode,perms);
    }

//    @AfterPermissionGranted(Constance.WRITE_PERMISSION_CODE) 
//    public void onPermissionsSuccess() {
//        ToastUtils.showToast(getApplicationContext(), "用户授权成功");
//    }
}
