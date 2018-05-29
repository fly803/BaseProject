package com.cg.baseproject.utils.android;

import android.content.Context;
import android.content.res.AssetManager;

import com.cg.baseproject.utils.JsonUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wlj
 * @date 2017/3/29
 * @email wanglijundev@gmail.com
 * @packagename wanglijun.vip.androidutils.utils
 * @desc: Assets获取的相关操作类
 */

public class AssetsUtils {
    private static final String ENCODING = "UTF-8";
    /**
     * 从assets获取文件
     *
     * @param context
     * @param fileName
     * @return
     * @throws IOException
     */
    public static InputStream getFileFromAssets(Context context, String fileName) throws IOException {
        AssetManager am = context.getAssets();
        return am.open(fileName);
    }

    /**
     * 从assets获取文本文件
     *
     * @param context
     * @param fileName
     * @return
     */
    public static String getTextFromAssets(Context context, String fileName) {
        String result = null;
        try {
            InputStream in = getFileFromAssets(context, fileName);
            int length = in.available();
            byte[] buffer = new byte[length];
            in.read(buffer);
            result = new String(buffer, Charset.forName(ENCODING));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 从assets获取文本文件
     *
     * @param context
     * @param fileName
     * @return
     */
    public static String getTextFromAssets2(Context context, String fileName) {
        try {
            InputStreamReader inputReader = new InputStreamReader(getFileFromAssets(context, fileName));
            BufferedReader bufReader = new BufferedReader(inputReader);
            String line = "";
            StringBuilder result = new StringBuilder();
            while ((line = bufReader.readLine()) != null) {
                result.append(line);
            }
            return result.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Parse a json file in the assets to ArrayList<?>
     * 解析json文件成ArrayList，在assets中
     * @param context
     * @param jsonName
     * @return
     */
    public static <T> ArrayList<T> parseJsonToArrayList(Context context, String jsonName) {
        String json = getTextFromAssets(context, jsonName);
        return JsonUtils.jsonToArrayList(json);
    }

    /**
     * Parse a json file in the assets to List<?>
     * 解析json文件成List，在assets中
     * @param context
     * @param jsonName
     * @return
     */
    public static <T> List<T> parseJsonToList(Context context, String jsonName) {
        String json = getTextFromAssets(context, jsonName);
        return JsonUtils.jsonToList(json);
    }

    /**
     * Parse a json file in the assets to Bean
     * 解析assets中的json文件
     * @param context
     * @param jsonName
     * @param clazz
     * @return
     */
    public static <T> T parseJsonToObject(Context context, String jsonName, Class<T> clazz) {
        String json = getTextFromAssets(context, jsonName);
        return JsonUtils.jsonToObject(json, clazz);
    }
    
    
}
