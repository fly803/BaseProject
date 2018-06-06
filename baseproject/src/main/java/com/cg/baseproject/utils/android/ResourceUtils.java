package com.cg.baseproject.utils.android;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import com.cg.baseproject.BaseApplication;

/**
 * Created by sam on 2017/4/17.
 * 有关UI的工具类，如获取资源(颜色，字符串，drawable等)，
 * 屏幕宽高，dp与px转换
 */

public class ResourceUtils {

    private static Context getContext() {
        return BaseApplication.getContext();
    }

    private static Resources getResources() {
        return getContext().getResources();
    }

    /**
     * 得到dimen值
     * resId = R.dimen.px1500
     * @param resId
     * @return
     */
    public static float getDimen(int resId) {
        return getResources().getDimension(resId);
    }

    public static int getDimenInt(int resId) {
        return (int)getResources().getDimension(resId);
    }
    
    /**
     * 获取颜色值
     * @param resId 颜色资源id
     * @return 颜色值
     */
    public static int getColor(int resId) {
        return getResources().getColor(resId);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public static int getColor(int resId, Resources.Theme theme) {
        return getResources().getColor(resId, theme);
    }

    public static int getColor(String color) {
        return Color.parseColor(color);
    }

    /**
     * 获取Drawable
     * @param resTd Drawable资源id
     * @return Drawable
     */
    public static Drawable getDrawable(int resTd) {
        return getResources().getDrawable(resTd);
    }

    /**
     * 获取字符串
     * @param resId 字符串资源id
     * @return 字符串
     */
    public static String getString(int resId) {
        return getResources().getString(resId);
    }

    /**
     * 获取字符串数组
     * @param resId 数组资源id
     * @return 字符串数组
     */
    public static String[] getStringArray(int resId) {
        return getResources().getStringArray(resId);
    }

    /**
     * 根据资源名获得资源id
     * @param context 上下文
     * @param name 资源名
     * @param type 资源类型
     * @return 资源id，找不到返回0
     */
    public static int getResourceId(Context context,String name,String type){
        Resources resources=null;
        PackageManager pm=context.getPackageManager();
        try {
            resources=context.getResources();
            return resources.getIdentifier(name, type, context.getPackageName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

}
