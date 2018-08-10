package com.cg.baseproject.utils.android;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import me.weyye.hipermission.HiPermission;
import me.weyye.hipermission.PermissionCallback;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * @author sam
 * @version 1.0
 * @date 2018/3/17
 */

public class EasyPermissionUtils {
    private static final String TAG = "EasyPermissions";
    public static String[] perms = {
            // 把你想要申请的权限放进这里就行，注意用逗号隔开
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.ACCESS_NETWORK_STATE,
    };

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

    /*private static final int num = 23;//用于验证获取的权
    @AfterPermissionGranted(num)*/
    public static void requireSomePermission(Activity context, String tip, int requestCode, String[] perms) {
        if (EasyPermissions.hasPermissions(context, perms)) {
            // Already have permission, do the thing
            // ...
            Toast.makeText(context, "已授权!", Toast.LENGTH_LONG).show();
        } else {
            // Do not have permissions, request them now
            Toast.makeText(context, "授权被拒绝，请从新点击确认允许授权!", Toast.LENGTH_LONG).show();
            EasyPermissions.requestPermissions(context, tip,requestCode, perms);
        }
    }

/*    //在请求权限回调中
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);      
        EasyPermissions.onRequestPermissionsResult(requestCode,permissions,grantResults,this);
    }*/


    public static void hiPermission(final Context context){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            HiPermission.create(context)
                    .checkMutiPermission(new PermissionCallback() {
                        @Override
                        public void onClose() {
//                            Log.i(TAG, "onClose");
//                            Toast.makeText(context,"They cancelled our request",Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onFinish() {
//                            Toast.makeText(context,"All permissions requested completed",Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onDeny(String permission, int position) {
//                            Log.i(TAG, "onDeny");
                        }

                        @Override
                        public void onGuarantee(String permission, int position) {
//                            Log.i(TAG, "onGuarantee");
                        }
                    });
        }
    }
}
