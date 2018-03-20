package com.cg.baseproject.utils;

import android.util.Log;

/**
 * Created by sam on 2017/4/19.
 *
 * Log工具类
 * 通过设置打印等级决定是否要打印Log
 */
public class LogUtils {

    public static final int LEVEL_VERBOSE = 0;
    public static final int LEVEL_DEBUG = 1;
    public static final int LEVEL_INFO = 2;
    public static final int LEVEL_WARNING = 3;
    public static final int LEVEL_ERROR = 4;

    public static final int LEVEL_NO_LOG = 5;

    private static int mLevel = LEVEL_VERBOSE;

    public static void setLogLevel(int level) {
        mLevel = level;
    }

    public static void setIsLog(boolean isLog) {
        mLevel = isLog ? LEVEL_VERBOSE : LEVEL_NO_LOG;
    }

    public static void v(String tag, String msg) {
        if (mLevel > LEVEL_VERBOSE) {
            return;
        }
        Log.v(tag, msg);
    }

    public static void v(String tag, String msg, Throwable throwable) {
        if (mLevel > LEVEL_VERBOSE) {
            return;
        }
        Log.v(tag, msg, throwable);
    }

    public static void d(String tag, String msg) {
        if (mLevel > LEVEL_DEBUG) {
            return;
        }
        Log.d(tag, msg);
    }

    public static void d(String tag, String msg, Throwable throwable) {
        if (mLevel > LEVEL_DEBUG) {
            return;
        }
        Log.d(tag, msg, throwable);
    }

    public static void i(String tag, String msg) {
        if (mLevel > LEVEL_INFO) {
            return;
        }
        Log.i(tag, msg);
    }

    public static void i(String tag, String msg, Throwable throwable) {
        if (mLevel > LEVEL_INFO) {
            return;
        }
        Log.i(tag, msg, throwable);
    }

    public static void w(String tag, String msg) {
        if (mLevel > LEVEL_WARNING) {
            return;
        }
        Log.w(tag, msg);
    }

    public static void w(String tag, String msg, Throwable throwable) {
        if (mLevel > LEVEL_WARNING) {
            return;
        }
        Log.w(tag, msg, throwable);
    }

    public static void e(String tag, String msg) {
        if (mLevel > LEVEL_ERROR) {
            return;
        }
        Log.e(tag, msg);
    }

    public static void e(String tag, String msg, Throwable throwable) {
        if (mLevel > LEVEL_ERROR) {
            return;
        }
        Log.e(tag, msg, throwable);
    }
}
