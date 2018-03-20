package com.cg.baseproject.utils;

import android.content.Context;
import android.graphics.Color;
import android.widget.Toast;

import com.cg.baseproject.BaseApplication;


/**
 * @author
 * @version 1.0
 * @date 2/1/2018
 */

public class ToastUtils {
    public static void showToast(String message) {
        Toast.makeText(BaseApplication.getContext(),message,Toast.LENGTH_LONG).show();
    }

    public static void showShort(String message) {
        Toast.makeText(BaseApplication.getContext(),message,Toast.LENGTH_SHORT).show();
    }
    
    public static void showToast(Context context, String message) {
        //        new StyleableToast.Builder(context).text(message).textColor(Color.WHITE).backgroundColor(Color.BLACK).show();
    }
}
