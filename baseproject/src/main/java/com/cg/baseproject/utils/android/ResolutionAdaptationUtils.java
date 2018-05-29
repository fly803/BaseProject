package com.cg.baseproject.utils.android;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.cg.baseproject.R;

import java.lang.reflect.Method;

public class ResolutionAdaptationUtils {
    /**
     * 分辨率相关adb 命令：查看分辨率，adb shell wm size；查看手机适配分辨率信息，查看信息最后部分adb shell dumpsys
     * activity； mConfiguration: {1.0 460mcc2mnc zh_CN ?layoutDir sw360dp w360dp
     * h567dp 480dp nrml port finger -keyb/v/h -nav/h s.5}
     */
    private static String TAG = "ResolutionAdaptationUtils";
    private static final String[] getDpi = {"ldpi", "mdpi", "hdpi", "xhdpi", "xxhdpi", "xxxhdpi"};

    enum DrawableDPIIndex {
        ldpi, mdpi, hdpi, xhdpi, xxhdpi, xxxhdpi;
    }

    /**
     * 1dp定义为屏幕密度值为160ppi时的1px，即，在mdpi时，1dp = 1px。 以mdpi为标准，这些屏幕的密度值比为： ldpi :
     * mdpi : hdpi : xhdpi : xxhdpi : xxxhdpi= 0.75 : 1 : 1.5 : 2 : 3 :
     * 4；即，在xhdpi的密度下，1dp=2px；
     * 在hdpi情况下，1dp=1.5px。其他类推。主流分辨率：800×480、960×540、1280×720、1920×1080、2560 x
     * 1440 DENSITY_LOW = 120 DENSITY_MEDIUM = 160 //默认值 DENSITY_TV = 213 //TV专用
     * DENSITY_HIGH = 240 DENSITY_XHIGH = 320 DENSITY_400 = 400 DENSITY_XXHIGH =
     * 480 DENSITY_XXXHIGH = 640 ldpi 120dp~160dp 32×32px mdpi 120dp~160dp
     * 48×48px hdpi 160dp~240dp 72×72px xhdpi 240dp~320dp 96×96px xxhdpi
     * 320dp~480dp 144×144px xxxhdpi 480dp~640dp 192×192px 在Google官方开发文档中，说明了
     * mdpi：hdpi：xhdpi：xxhdpi：xxxhdpi=2：3：4：6：8 的尺寸比例进行缩放。
     * 例如，一个图标的大小为48×48dp，表示在mdpi上
     * ，实际大小为48×48px，在hdpi像素密度上，实际尺寸为mdpi上的1.5倍，即72×72px，以此类推 Android上常见度量单位
     * px（像素）：屏幕上的点，绝对长度，与硬件相关。 in（英寸）：长度单位。 mm（毫米）：长度单位。 pt（磅）：1/72英寸，point。
     * dp（与密度无关的像素）：一种基于屏幕密度的抽象单位。在每英寸160点的显示器上，1dp = 1px。
     * dip：Density-independent pixel,同dp相同。 sp：在dp的基础上，还与比例无关，个人理解为是一个矢量图形单位
     * <p>
     * 得到手机values文件夹方法，在cmd窗口下输入：adb shell am get-config
     * 得到Nexus5手机信息如下:Android程序默认会读取values下的dimens， 如果定义了h567dp
     * values，如values-h567dp
     * ，就会读取该文件夹下在的dimens，getResources().getDimension(R.dimen.witch_values)，
     * 在Android程序中定义主流设备的dimens，并根据缩放系数定义多个dp值，就能做到屏幕适配了。 config:
     * en-rUS-ldltr-sw360dp-w360dp-h567dp-normal-notlong-port-notnight-xxhdpi-f
     * inger-keysexposed-nokeys-navhidden-nonav-v22 abi: armeabi-v7a,armeabi
     * <p>
     * 得到dip，缩放系数
     *
     * @param context
     * @return
     */
    public static float getDipScale(Context context) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return scale;
    }

    public static String getResolutionInfo(Context context) {
        return context.getResources().getDisplayMetrics().toString();
    }

    /**
     * 获取屏幕密度，每英寸的像素数
     *
     * @param context
     * @return
     */
    public static int getDensity(Context context) {
        return context.getResources().getDisplayMetrics().densityDpi;
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static float px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return pxValue / scale;
    }

    public static float convertPixelsToDp(float px, Context context){
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        return px / (metrics.densityDpi / 160f);
    }

    public static float convertDpToPixel(float dp, Context context){
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        return dp * (metrics.densityDpi / 160f);
    }
    
    /**
     * 手动计算设备的屏幕像素密度
     *
     * @param context
     * @return
     */
    public static int getPPI(Context context) {
        Point point = new Point();
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getSize(point);
        int height = point.y;
        int width = point.x;
        return (int) (Math.sqrt(height * height + width * width) / getScreenSizeInch(context));
    }

    public static int getDpi(int height, int width, int inch) {
        return (int) (Math.sqrt(height * height + width * width) / inch);
    }

    /**
     * 得到该手机的drawable文件夹密码
     *
     * @param context
     */
    public static String getDensityString(Context context) {
        final float density = getDipScale(context);
        String desintyString;
        if (0.75 == density) {
            desintyString = "ldpi";
        } else if (1.0 == density) {
            desintyString = "mdpi";
        } else if (1.5 == density) {
            desintyString = "hdpi";
        } else if (2.0 == density) {
            desintyString = "xhdpi";
        } else if (3.0 == density) {
            desintyString = "xxhdpi";
        } else if (4.0 == density) {
            desintyString = "xxxhdpi";
        } else {
            desintyString = "hdpi";
        }
        return desintyString;
    }

    public static void getDensityInfo(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        Log.d(TAG, "Density is " + displayMetrics.density + " densityDpi is " + displayMetrics.scaledDensity + " height: " + displayMetrics.heightPixels + " width: " + displayMetrics.widthPixels);
    }

    public static void getDisplayInfomation(Context context) {
        Point point = new Point();
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getSize(point);
        System.out.println(point.x);
        System.out.println(point.y);
        Log.d(TAG, "the screen size is " + point.toString());
    }

    //不包含虚拟键在内的屏幕高度
    public static int getScreenHeightWithoutVirtualBar(Activity activity) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.heightPixels;
    }

    /**
     * 获取虚拟按键的高度，无关虚拟按键是否显示
     */
    public static int getNavigationBarHeight(Activity activity) {
        int resourceId = activity.getResources().getIdentifier("navigation_bar_height", "dimen", "android");
        return activity.getResources().getDimensionPixelSize(resourceId);
    }

    /**
     * 得到设备屏幕尺寸 http://blog.csdn.net/lincyang/article/details/42679589
     *
     * @param context
     */
    public static double getScreenSizeInch(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Point point = new Point();
        windowManager.getDefaultDisplay().getRealSize(point);
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        double x = Math.pow(point.x / dm.xdpi, 2);
        double y = Math.pow(point.y / dm.ydpi, 2);
        double screenInches = Math.sqrt(x + y);
        Log.d(TAG, "Screen height inches: " + point.y / dm.xdpi);
        Log.d(TAG, "Screen width inches: " + point.x / dm.xdpi);
        Log.d(TAG, "Screen height mm: " + point.y / dm.xdpi * 25.4);
        Log.d(TAG, "Screen width mm: " + point.x / dm.xdpi * 25.4);
        Log.d(TAG, "Screen inches : " + screenInches);
        return screenInches;
    }

    /**
     * 获取设备的分辨率信息
     *
     * @param context
     */
    public static void getScreenSizeOfDevice(Context context, Activity activity) {
        Point point = new Point();
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getRealSize(point);
        DisplayMetrics mDisplayMetrics = new DisplayMetrics();//屏幕分辨率容器  
        activity.getWindowManager().getDefaultDisplay().getMetrics(mDisplayMetrics);
        int width = mDisplayMetrics.widthPixels;
        int height = mDisplayMetrics.heightPixels;
        Log.d(TAG, "当前设备---分辨率： " + point.toString());
        Log.d(TAG, "当前设备---app分辨率： " + +width + "x" + height);
        Log.d(TAG, "当前设备---虚拟键高度： " + getNavigationBarHeight(context));
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        Log.d(TAG, "当前设备---屏幕像素密度(PPI) dm.xdpi == ： " + dm.xdpi);
        Log.d(TAG, "当前设备---屏幕像素密度(PPI) dm.ydpi == ： " + dm.ydpi);
        double x = Math.pow(point.x / dm.xdpi, 2); // 根据分辨率、屏幕尺寸和屏幕像素密度三者之间的公式
        double y = Math.pow(point.y / dm.ydpi, 2);
        double screenInches = Math.sqrt(x + y);
        double density = Math.sqrt(point.x * point.x + point.y * point.y) / screenInches;
        // double density = Math.sqrt(point.x*point.x + point.y*point.y)/4.95;
        // //Nexus尺寸4.95，计算所得4.971247911145661
        Log.d(TAG, "当前设备---屏幕尺寸： " + screenInches);
        Log.d(TAG, "当前设备---系统参数密度Density： " + dm.densityDpi);
        Log.d(TAG, "当前设备---系统参数密度Dpi缩放系数： " + dm.scaledDensity);
        Log.d(TAG, "当前设备---计算密度Density： " + density);
        Log.d(TAG, "当前设备---计算密度Dpi缩放系数： " + density / 160);
    }

    /**
     * 得到设备状态栏高度
     *
     * @param context
     * @return
     */
    public static int getStatusHeightOnRReflect(Context context) {
        int statusHeight = -1;
        try {
            Class<?> clazz = Class.forName("com.android.internal.R$dimen.xml");
            Object object = clazz.newInstance();
            int height = Integer.parseInt(clazz.getField("status_bar_height").get(object).toString());
            statusHeight = context.getResources().getDimensionPixelSize(height);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return statusHeight;
    }

    /**
     * 得到statusbar高度
     *
     * @param context
     * @return
     */
    public static int getStatusHeight(Context context) {
        /**
         * 获取状态栏高度——方法1
         * */
        int statusBarHeight = -1;
        // 获取status_bar_height资源的ID
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            // 根据资源ID获取响应的尺寸值
            statusBarHeight = context.getResources().getDimensionPixelSize(resourceId);
        }
        return statusBarHeight;
    }

    /**
     * 标题栏高度
     *
     * @return
     */
    public static int getTitleHeight(Activity activity) {
        return activity.getWindow().findViewById(Window.ID_ANDROID_CONTENT).getTop();
    }

    /**
     * 获取 虚拟按键的高度
     *
     * @param context
     * @return
     */
    public static int getBottomStatusHeight(Context context) {
        int totalHeight = getDpi(context);
        int contentHeight = getScreenHeight(context);
        return totalHeight - contentHeight;
    }

    //获取屏幕原始尺寸高度，包括虚拟功能键高度
    public static int getDpi(Context context) {
        int dpi = 0;
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        @SuppressWarnings("rawtypes") Class c;
        try {
            c = Class.forName("android.view.Display");
            @SuppressWarnings("unchecked") Method method = c.getMethod("getRealMetrics", DisplayMetrics.class);
            method.invoke(display, displayMetrics);
            dpi = displayMetrics.heightPixels;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dpi;
    }

    /**
     * 通过WindowManager得到虚拟键高度
     *
     * @param context
     * @return
     */
    public static int getNavigationBarHeight(Context context) {
        Point point = new Point();
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        // 不含虚拟按键
        windowManager.getDefaultDisplay().getSize(point);
        int screenHeigth = point.y;
        // 包含虚拟按键
        windowManager.getDefaultDisplay().getRealSize(point);
        int screenRealHeigth = point.y;
        return screenRealHeigth - screenHeigth;
    }

    /**
     * 获取屏幕的宽高
     */
    public static void measure(Context context) {
        Point point = new Point();
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        // 不含虚拟按键
        windowManager.getDefaultDisplay().getSize(point);
        // 包含虚拟按键
        // windowManager.getDefaultDisplay().getRealSize(point);
        // 屏幕宽度
        int height = point.y;
        // 屏幕高度
        int width = point.x;
        Log.d(TAG, " height:" + height + "width:" + width);
    }

    // 获取是否存在NavigationBar虚拟键
    public static boolean checkDeviceHasNavigationBar(Context context) {
        boolean hasNavigationBar = false;
        Resources rs = context.getResources();
        int id = rs.getIdentifier("config_showNavigationBar", "bool", "android");
        if (id > 0) {
            hasNavigationBar = rs.getBoolean(id);
        }
        try {
            Class systemPropertiesClass = Class.forName("android.os.SystemProperties");
            Method m = systemPropertiesClass.getMethod("get", String.class);
            String navBarOverride = (String) m.invoke(systemPropertiesClass, "qemu.hw.mainkeys");
            if ("1".equals(navBarOverride)) {
                hasNavigationBar = false;
            } else if ("0".equals(navBarOverride)) {
                hasNavigationBar = true;
            }
        } catch (Exception e) {

        }
        return hasNavigationBar;
    }

    /**
     * 获得屏幕高度
     *
     * @param context
     * @return
     */
    public static int getScreenHeight(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.heightPixels;
    }

    /**
     * 获取屏幕宽度
     *
     * @param context
     * @return
     */
    public static int getScreenWeight(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.widthPixels;
    }

    // 获取屏幕原始尺寸高度，包括虚拟功能键高度
    public static int getScreenRealHeight(Context context) {
        int dpi = 0;
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        @SuppressWarnings("rawtypes") Class c;
        try {
            c = Class.forName("android.view.Display");
            @SuppressWarnings("unchecked") Method method = c.getMethod("getRealMetrics", DisplayMetrics.class);
            method.invoke(display, displayMetrics);
            dpi = displayMetrics.heightPixels;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dpi;
    }

    /**
     * 获取 虚拟按键的高度
     *
     * @param context
     * @return
     */
    public static int getNavigationBarHeight1(Context context) {
        int totalHeight = getScreenRealHeight(context);
        int contentHeight = getScreenHeight(context);
        return totalHeight - contentHeight;
    }

    //获得手机的宽度和高度像素单位为px  
    // 通过WindowManager获取    
    public static void getScreenDensity_ByWindowManager(Activity activity) {
        DisplayMetrics mDisplayMetrics = new DisplayMetrics();//屏幕分辨率容器  
        activity.getWindowManager().getDefaultDisplay().getMetrics(mDisplayMetrics);
        int width = mDisplayMetrics.widthPixels;
        int height = mDisplayMetrics.heightPixels;
        float density = mDisplayMetrics.density;
        int densityDpi = mDisplayMetrics.densityDpi;
        Log.d(TAG, "Screen Ratio: [" + width + "x" + height + "],density=" + density + ",densityDpi=" + densityDpi);
        Log.d(TAG, "Screen mDisplayMetrics: " + mDisplayMetrics);
    }

    // 通过Resources获取    
    public static void getScreenDensity_ByResources(Context activity) {
        DisplayMetrics mDisplayMetrics = activity.getResources().getDisplayMetrics();
        int width = mDisplayMetrics.widthPixels;
        int height = mDisplayMetrics.heightPixels;
        float density = mDisplayMetrics.density;
        int densityDpi = mDisplayMetrics.densityDpi;
        Log.d(TAG, "Screen Ratio: [" + width + "x" + height + "],density=" + density + ",densityDpi=" + densityDpi);
        Log.d(TAG, "Screen mDisplayMetrics: " + mDisplayMetrics);

    }

    /**
     * Android机顶盒信息
     * @param activity
     */
    public static void getAndriodTvResolutionInfo(Context context,Activity activity) {
        DisplayMetrics metric = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(metric);
        // 屏幕宽度（像素） 
        int width = metric.widthPixels;
        // 屏幕高度（像素） 
        int height = metric.heightPixels;
        // 屏幕密度（1.0 / 1.5 / 2.0） 
        float density = metric.density;
        // 屏幕密度DPI（160 / 240 / 320） 
        int densityDpi = metric.densityDpi;
        String info = "机顶盒型号: " + android.os.Build.MODEL + ",\nSDK版本:" + android.os.Build.VERSION.SDK + ",\n系统版本:" 
                + android.os.Build.VERSION.RELEASE + "\n屏幕宽度（像素）: " + width + "\n屏幕高度（像素）: " + height + "\n屏幕密度: " 
                + density + "\n屏幕密度DPI: " + densityDpi +"\n1dp像素: "+context.getResources().getDimension(R.dimen.xxxvalues);
        Log.d(TAG, info);
    }

    /**
     * 隐藏虚拟键
     * @param activity
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static void hideNavBar(Activity activity) {
        int flag = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION ;// hide
        // 获取属性
        activity.getWindow().getDecorView().setSystemUiVisibility(flag);
    }

    /**
     * 显示虚拟键
     * @param activity
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static void showNavBar(Activity activity) {
        int flag = View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR; // show
        // 获取属性
        activity.getWindow().getDecorView().setSystemUiVisibility(flag);
    }

}
