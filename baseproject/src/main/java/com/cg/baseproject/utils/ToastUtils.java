package com.cg.baseproject.utils;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.Toast;

import com.cg.baseproject.BaseApplication;


/**
 * @author
 * @version 1.0
 * @date 2/1/2018
 */

public class ToastUtils {
    public static void showToast(String message) {
        Toast toast = Toast.makeText(BaseApplication.getContext(),message,Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    public static void showShort(String message) {
        Toast toast = Toast.makeText(BaseApplication.getContext(),message,Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }
}
