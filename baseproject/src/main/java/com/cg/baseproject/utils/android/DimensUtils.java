package com.cg.baseproject.utils.android;

/**
 * @author
 * @version 1.0
 * @date 2018/2/1 0001
 */

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.DimenRes;
/*        屏幕密度 	范围(dpi) 	标准分辨率 	dp与px 	图标尺寸
        ldpi(QVGA) 	~ 120 	240 * 320 	1dp=0.75px 	36 * 36
        mdpi(HVGA) 	120 ~ 160 	320 * 480 	1dp=1px 	48 * 48
        hdpi(WVGA) 	160 ~ 240 	480 * 800 	1dp=1.5px 	72 * 72
        xhdpi(720P) 	240 ~ 320 	720 * 1280 	1dp=2px 	96 * 96
        xxhdpi(1080p) 	320 ~ 480 	1080 * 1920 	1dp=3px 	144 * 144
        xxxhdpi(2K) 	480 ~ 640 	1440 × 2560 	1dp=4px 	192 * 192*/

/**
 * 尺寸工具箱，提供与Android尺寸相关的工具方法
 */
public class DimensUtils {
    public static float pixelsFromSpResource(Context context, @DimenRes int sizeRes) {
        final Resources res = context.getResources();
        return res.getDimension(sizeRes) / res.getDisplayMetrics().density;
    }
    
    /**
     * dp单位转换为px
     * @param context 上下文，需要通过上下文获取到当前屏幕的像素密度
     * @param dpValue dp值
     * @return px值
     */
    public static int dp2px(Context context, float dpValue){
        return (int)(dpValue * (context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    /**
     * px单位转换为dp
     * @param context 上下文，需要通过上下文获取到当前屏幕的像素密度
     * @param pxValue px值
     * @return dp值
     */
    public static int px2dp(Context context, float pxValue){
        return (int)(pxValue / (context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    /** sp转换成px */
    public static int sp2px(Context context,float spValue) {
        float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    /** px转换成sp */
    public static int px2sp(Context context,float pxValue) {
        float fontScale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / fontScale + 0.5f);
    }
}
