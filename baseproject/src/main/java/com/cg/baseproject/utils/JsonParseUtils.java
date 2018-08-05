package com.cg.baseproject.utils;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.cg.baseproject.request.data.GenericType;
import com.google.gson.Gson;
import com.google.gson.JsonElement;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * @author
 * @version 1.0
 * @date 2018/8/4
 */
public class JsonParseUtils {
    /**
     * Gson 本身线程安全
     * 直接采用“懒汉方式”单例写法
     */
    private static Gson singleton;
    private static Gson getSingleton() {
        if (singleton == null) {
            singleton = new Gson();
        }
        return singleton;
    }

    /**
     * 定制Gson
     * 使用GsonBuilder
     */
    public static void setSingletonInstance(@NonNull Gson gson) {
        if (gson == null) {
            throw new IllegalArgumentException("Gson must not be null");
        }

        synchronized (JsonParseUtils.class) {
            if (singleton != null) {
                throw new IllegalStateException("Singleton instance already exists");
            }
            singleton = gson;
        }

    }

    /**
     * Json转Object
     * <p>
     * 如：UserInfo
     *
     *      UserInfo userInfo = JsonParseUtil.parseToObject(dataStr, UserInfo.class);
     *
     * @param dataStr String
     */
    public static <T> T parseToObject(String dataStr, Class<T> cls) {
        if (TextUtils.isEmpty(dataStr)) {
            return null;
        }
        /*T t = getSingleton().fromJson(dataStr, cls);*/
        T t = JSON.parseObject(dataStr, cls);
        return t;
    }
    /**
     * Json转Object
     * <p>
     * 如：UserInfo
     *
     *      UserInfo userInfo = JsonParseUtil.parseToObject(dataStr, UserInfo.class);
     *
     * 只在 GSON 中适用
     *
     * @param jsonElement JsonElement
     */
    public static <T> T parseToObject(Object jsonElement, Class<T> cls) {
        if (jsonElement instanceof String) {
            return parseToObject(jsonElement, cls);
        }
        if (jsonElement instanceof JsonElement && !((JsonElement)jsonElement).isJsonNull()) {
            T t = getSingleton().fromJson((JsonElement)jsonElement, cls);
            return t;
        }

        return parseToObject("", cls);
    }

    /**
     * Json转泛型Object
     * <p>
     * 如：HttpResult<UserInfo>
     *
     *     HttpResult<UserInfo> tmp
     *          = JsonParseUtil.parseToGenericObject(dataStr, new GenericType<HttpResult<UserInfo>>(){})
     */
    public static <T> T parseToGenericObject(String dataStr, GenericType<T> genericType) {
        if (TextUtils.isEmpty(dataStr)) {
            return null;
        }
        /*T t = getSingleton().fromJson(dataStr, genericType.getType());*/
        T t = JSON.parseObject(dataStr, genericType.getType());
        return t;
    }


    /**
     * Object转Json String
     * <p>
     * 如：UserInfo
     */
    public static String parseToJson(Object object) {
        /*return (object == null) ? "" : getSingleton().toJson(object);*/
        return (object == null) ? "" : JSON.toJSONString(object);
    }



    /**
     * Json转单纯的List （dataStr为一个完整Json数组）
     * <p>
     * 如：[{"code":1,"name":"小米"},{"code":2,"name":"大米"}]  -->  List<GoodInfo>
     */
    public static <T> List<T> parseToPureList(String dataStr, Class<T> cls) {
        /*if (TextUtils.isEmpty(dataStr)) {
            return null;
        }
        T[] array = getSingleton().fromJson(dataStr, cls);
        return new ArrayList<>(Arrays.asList(array));*/

        List<T> list = JSON.parseArray(dataStr, cls);
        return list;
    }



    /**
     * Json转指定Class数组 内涵不确定字段名Json数组
     * <p>
     * 如： { "XXX":[{"Id":1352}] }  -->  List<YYY>
     */
    public static <T> List<T> parseToDynamicList(String dataStr, String fieldName, Class<T> cls) {

        String listData = "";
        if (!TextUtils.isEmpty(dataStr)) {
            try {
                JSONObject jsonObj = new JSONObject(dataStr);
                listData = jsonObj.optString(fieldName);
            } catch (JSONException exp) {

            }
        }
        return parseToPureList(listData, cls);
    }

    /**
     * Json转指定Class 内涵不确定字段名Json对象
     * <p>
     * 如： { "XXX":{"Id":1352} }  -->  YYY
     */
    public static <T> T parseToDynamicObject(String dataStr, String fieldName, Class<T> cls) {

        String objectData = "";
        if (!TextUtils.isEmpty(dataStr)) {
            try {
                JSONObject jsonObj = new JSONObject(dataStr);
                objectData = jsonObj.optString(fieldName);
            } catch (JSONException exp) {

            }
        }
        return parseToObject(objectData, cls);
    }
}
