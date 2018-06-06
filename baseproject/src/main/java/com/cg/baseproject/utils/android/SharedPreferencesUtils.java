package com.cg.baseproject.utils.android;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.SharedPreferences;
import android.os.Environment;

import com.cg.baseproject.BaseApplication;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * https://www.jianshu.com/p/64ef6eb7406f
 * https://github.com/grandcentrix/tray
 * SharedPreferences的一个工具类，调用setParam就能保存String, Integer, Boolean, Float, Long类型的参数
 * 同样调用getParam就能获取到保存在手机里面的数据
 *
 * @author xiaanming
 *         <p>
 *         保存数据：
 *         SharedPreferencesUtils.setParam(this, "String", "xiaanming");
 *         SharedPreferencesUtils.setParam(this, "int", 10);
 *         SharedPreferencesUtils.setParam(this, "boolean", true);
 *         SharedPreferencesUtils.setParam(this, "long", 100L);
 *         SharedPreferencesUtils.setParam(this, "float", 1.1f);
 *         获取数据：
 *         1     SharedPreferencesUtils.getParam(TimerActivity.this, "String", "");                                                                                        SharedPreferencesUtils.getParam(TimerActivity.this, "int", 0);
 *         2     SharedPreferencesUtils.getParam(TimerActivity.this, "boolean", false);
 *         3     SharedPreferencesUtils.getParam(TimerActivity.this, "long", 0L);
 *         4     SharedPreferencesUtils.getParam(TimerActivity.this, "float", 0.0f);
 */
public class SharedPreferencesUtils {
    /**
     * 保存在手机里面的文件名
     */
    private static final String SharedFILE_NAME = "share_date";
    // SD卡路径
    public final static String SDCARDPATH = Environment.getExternalStorageDirectory().getAbsolutePath();
    // SD卡目录路径
    public final static String SDCARDDIR = SDCARDPATH + File.separator;
    // 手机内存路径
    public String PHONEPATH;// eg.PHONEPATH:/data/data/com.example.androidtest/cache
    // 手机内存目录路径
    public String PHONEDIR;//
    private SharedPreferences mSharedPreferences;
    private static SharedPreferencesUtils preferenceUtils = null;
    private SharedPreferences.Editor editor;

    protected SharedPreferencesUtils() {
        mSharedPreferences = BaseApplication.getContext().getSharedPreferences(SharedFILE_NAME, Context.MODE_PRIVATE);
        editor = mSharedPreferences.edit();
    }


    public static SharedPreferencesUtils getInstance() {
        if (preferenceUtils == null) {
            synchronized (SharedPreferencesUtils.class) {
                if (preferenceUtils == null) {
                    preferenceUtils = new SharedPreferencesUtils();
                }
            }
        }
        return preferenceUtils;
    }

    /**
     * 存入某个key对应的value值
     *
     * @param key
     * @param value
     */
    public static void setParam(String key, Object value) {
        SharedPreferences sharedPreferences = BaseApplication.getContext().getSharedPreferences(SharedFILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if (value instanceof String) {
            editor.putString(key, (String) value);
        } else if (value instanceof Integer) {
            editor.putInt(key, (Integer) value);
        } else if (value instanceof Boolean) {
            editor.putBoolean(key, (Boolean) value);
        } else if (value instanceof Float) {
            editor.putFloat(key, (Float) value);
        } else if (value instanceof Long) {
            editor.putLong(key, (Long) value);
        }
        editor.apply();
    }

    /**
     * 得到某个key对应的值
     *
     * @param key
     * @param defValue
     * @return
     */
    public static Object getParam(String key, Object defValue) {
        SharedPreferences sharedPreferences = BaseApplication.getContext().getSharedPreferences(SharedFILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        if (defValue instanceof String) {
            return sharedPreferences.getString(key, (String) defValue);
        } else if (defValue instanceof Integer) {
            return sharedPreferences.getInt(key, (Integer) defValue);
        } else if (defValue instanceof Boolean) {
            return sharedPreferences.getBoolean(key, (Boolean) defValue);
        } else if (defValue instanceof Float) {
            return sharedPreferences.getFloat(key, (Float) defValue);
        } else if (defValue instanceof Long) {
            return sharedPreferences.getLong(key, (Long) defValue);
        }
        return null;
    }

    /**
     * 保存数据的方法，我们需要拿到保存数据的具体类型，然后根据类型调用不同的保存方法
     *
     * @param context
     * @param key
     * @param object
     */
    public static void setParam(Context context, String key, Object object) {
        String type = object.getClass().getSimpleName();
        SharedPreferences sp = context.getSharedPreferences(SharedFILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();

        if ("String".equals(type)) {
            editor.putString(key, (String) object);
        } else if ("Integer".equals(type)) {
            editor.putInt(key, (Integer) object);
        } else if ("Boolean".equals(type)) {
            editor.putBoolean(key, (Boolean) object);
        } else if ("Float".equals(type)) {
            editor.putFloat(key, (Float) object);
        } else if ("Long".equals(type)) {
            editor.putLong(key, (Long) object);
        }

        editor.commit();
    }


    /**
     * 得到保存数据的方法，我们根据默认值得到保存的数据的具体类型，然后调用相对于的方法获取值
     *
     * @param context
     * @param key
     * @param defaultObject
     * @return
     */
    public static Object getParam(Context context, String key, Object defaultObject) {
        String type = defaultObject.getClass().getSimpleName();
        SharedPreferences sp = context.getSharedPreferences(SharedFILE_NAME, Context.MODE_PRIVATE);
        if ("String".equals(type)) {
            return sp.getString(key, (String) defaultObject);
        } else if ("Integer".equals(type)) {
            return sp.getInt(key, (Integer) defaultObject);
        } else if ("Boolean".equals(type)) {
            return sp.getBoolean(key, (Boolean) defaultObject);
        } else if ("Float".equals(type)) {
            return sp.getFloat(key, (Float) defaultObject);
        } else if ("Long".equals(type)) {
            return sp.getLong(key, (Long) defaultObject);
        }
        return null;
    }

    public Object get(String key, Object defValue) {
        if (defValue instanceof String) {
            return mSharedPreferences.getString(key, (String) defValue);
        } else if (defValue instanceof Integer) {
            return mSharedPreferences.getInt(key, (Integer) defValue);
        } else if (defValue instanceof Boolean) {
            return mSharedPreferences.getBoolean(key, (Boolean) defValue);
        } else if (defValue instanceof Float) {
            return mSharedPreferences.getFloat(key, (Float) defValue);
        } else if (defValue instanceof Long) {
            return mSharedPreferences.getLong(key, (Long) defValue);
        }
        return null;
    }

    public void set(String key, Object value) {
        if (value instanceof String) {
            editor.putString(key, (String) value);
        } else if (value instanceof Integer) {
            editor.putInt(key, (Integer) value);
        } else if (value instanceof Boolean) {
            editor.putBoolean(key, (Boolean) value);
        } else if (value instanceof Float) {
            editor.putFloat(key, (Float) value);
        } else if (value instanceof Long) {
            editor.putLong(key, (Long) value);
        }
        editor.apply();
    }
    public static boolean isHaveSD() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }

    public static void setSharedPreferencesToPath(Context context, String dirName, String sharePreName, String key, String value) {
        SharedPreferences mySharedPreferences = null;
        SharedPreferences.Editor editor = null;
        try {
            Field field;
            // 获取ContextWrapper对象中的mBase变量。该变量保存了ContextImpl对象
            field = ContextWrapper.class.getDeclaredField("mBase");
            field.setAccessible(true);
            // 获取mBase变量
            Object obj = field.get(context);
            // 获取ContextImpl。mPreferencesDir变量，该变量保存了数据文件的保存路径
            field = obj.getClass().getDeclaredField("mPreferencesDir");
            field.setAccessible(true);
            // 创建自定义路径
            File file;
            if (isHaveSD()) {
                file = new File(SDCARDDIR, dirName);
            } else {
                file = new File(context.getCacheDir().getAbsolutePath() + File.separator, dirName);
            }
            //			System.out.println("@@@context.getCacheDir().getAbsolutePath()+java.io.File.separator:"+context.getCacheDir().getAbsolutePath()+java.io.File.separator);
            // 修改mPreferencesDir变量的值
            field.set(obj, file);
            mySharedPreferences = context.getSharedPreferences(sharePreName, Context.MODE_PRIVATE);
            editor = mySharedPreferences.edit();
            // if (isExistFile) {
            // Toast.makeText(this, "文件还不存在", 5000).show();
            editor.putString(key, value);
            editor.commit();
            // }
            // new Handler().postDelayed(delayRunable,5000);
            // firstUseTime = mySharedPreferences.getString("time", null);
            // // 使用Toast提示信息
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static String getSharedPreferencesFromPath(Context context, String dirName, String sharePreName, String key, String defaultValue) {
        SharedPreferences mySharedPreferences = null;
        try {
            Field field;
            // 获取ContextWrapper对象中的mBase变量。该变量保存了ContextImpl对象
            field = ContextWrapper.class.getDeclaredField("mBase");
            field.setAccessible(true);
            // 获取mBase变量
            Object obj = field.get(context);
            // 获取ContextImpl。mPreferencesDir变量，该变量保存了数据文件的保存路径
            field = obj.getClass().getDeclaredField("mPreferencesDir");
            field.setAccessible(true);
            // 创建自定义路径
            File file;
            if (isHaveSD()) {
                file = new File(SDCARDDIR, dirName);
            } else {
                file = new File(context.getCacheDir().getAbsolutePath() + File.separator, dirName);
            }
            // 修改mPreferencesDir变量的值
            field.set(obj, file);
            mySharedPreferences = context.getSharedPreferences(sharePreName, Context.MODE_PRIVATE);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return mySharedPreferences.getString(key, defaultValue);
    }

    /**
     * 移除某个key值已经对应的值
     *
     * @param context
     * @param key
     */
    public static void remove(Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences(SharedFILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.remove(key);
        SharedPreferencesCompat.apply(editor);
    }

    /**
     * 清除所有数据
     *
     * @param context
     */
    public static void clear(Context context) {
        SharedPreferences sp = context.getSharedPreferences(SharedFILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        SharedPreferencesCompat.apply(editor);
    }

    /**
     * 查询某个key是否已经存在
     *
     * @param context
     * @param key
     * @return
     */
    public static boolean contains(Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences(SharedFILE_NAME, Context.MODE_PRIVATE);
        return sp.contains(key);
    }

    /**
     * 返回所有的键值对
     *
     * @param context
     * @return
     */
    public static Map<String, ?> getAll(Context context) {
        SharedPreferences sp = context.getSharedPreferences(SharedFILE_NAME, Context.MODE_PRIVATE);
        return sp.getAll();
    }

    /**
     * 创建一个解决SharedPreferencesCompat.apply方法的一个兼容类
     *
     * @author zhy
     */
    private static class SharedPreferencesCompat {
        private static final Method sApplyMethod = findApplyMethod();

        /**
         * 反射查找apply的方法
         *
         * @return
         */
        @SuppressWarnings({"unchecked", "rawtypes"})
        private static Method findApplyMethod() {
            try {
                Class clz = SharedPreferences.Editor.class;
                return clz.getMethod("apply");
            } catch (NoSuchMethodException e) {
            }

            return null;
        }

        /**
         * 如果找到则使用apply执行，否则使用commit
         *
         * @param editor
         */
        public static void apply(SharedPreferences.Editor editor) {
            try {
                if (sApplyMethod != null) {
                    sApplyMethod.invoke(editor);
                    return;
                }
            } catch (IllegalArgumentException e) {
            } catch (IllegalAccessException e) {
            } catch (InvocationTargetException e) {
            }
            editor.commit();
        }
    }
}
