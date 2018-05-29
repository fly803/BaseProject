package com.cg.baseproject.utils.android;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.telephony.TelephonyManager;

import java.util.Locale;

/**
 * @author
 * @version 1.0
 * @date 2018/2/8 0008
 */

public class AndroidSystemUtils {
    /**
     * 获取当前手机系统语言。 
     *
     * @return 返回当前系统语言。例如：当前设置的是“中文-中国”，则返回“zh-CN” 
     */
    public static String getSystemLanguage() {
        return Locale.getDefault().getLanguage();
    }

    /**
     * 获取当前系统上的语言列表(Locale列表) 
     *
     * @return  语言列表
     */
    public static Locale[] getSystemLanguageList() {
        return Locale.getAvailableLocales();
    }

    /**
     * 获取当前手机系统版本号 
     *
     * @return  系统版本号
     */
    public static String getSystemVersion() {
        return android.os.Build.VERSION.RELEASE;
    }

    /**
     * 获取手机型号 
     *
     * @return  手机型号
     */
    public static String getSystemModel() {
        return android.os.Build.MODEL;
    }

    /**
     * 获取手机厂商 
     *
     * @return  手机厂商
     */
    public static String getDeviceBrand() {
        return android.os.Build.BRAND;
    }

    public static String getMaxAspect(Context context) {
        ApplicationInfo applicationInfo = null;
        try {
            applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        if(applicationInfo == null){
            throw new IllegalArgumentException(" get application info = null, has no meta data! ");
        }
        return applicationInfo.metaData.getString("android.max_aspect");
    }

    /**
     * 设置系统MaxAspect
     * @param context
     * @param aspectValue
     */
    public static void setMaxAspect(Context context,String aspectValue) {
        ApplicationInfo applicationInfo = null;
        try {
            applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        if(applicationInfo == null){
            throw new IllegalArgumentException(" get application info = null, has no meta data! ");
        }
        //        applicationInfo.metaData.putString("android.max_aspect", aspectValue);
    }
}
