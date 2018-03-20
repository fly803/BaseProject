package com.cg.baseproject.utils;

import android.text.TextUtils;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @author sam
 * @version 1.0
 * @date 2018/1/1
 */

public class NullUtils {
    /**
     * 判断字符串是否为null
     * @param str
     * @return
     */
    public static boolean isEmptyString(CharSequence str) {
        if (TextUtils.isEmpty(str) || "null".equals(str)) {//后台可能会返回“null”
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断list是否为null
     * @param list
     * @return
     */
    public static boolean isEmptyList(List list) {
        if (list == null || list.size() == 0) {
            return true;
        }else {
            return false;
        }
    }

    /**
     * 判断对象是否为null
     * @param object
     * @return
     */
    public static boolean isEmptyObject(Object object) {
        if (object == null) {
            return true;
        }else {
            return false;
        }
    }

    /**
     * 判断对象或对象数组中每一个对象是否为空: 对象为null，字符序列长度为0，集合类、Map为empty 
     * @param obj
     * @return
     */
    public static boolean isNull(Object obj) {
        if (obj == null) {
            return true;
        }
        if (obj instanceof CharSequence) {
            if (TextUtils.isEmpty((CharSequence) obj) || "null".equals((CharSequence) obj)) {//后台可能会返回“null”
                return true;
            }
        }
        if (obj instanceof Collection) {
            return ((Collection) obj).isEmpty();
        }
        if (obj instanceof Map) {
            return ((Map) obj).isEmpty();
        }
        if (obj instanceof Object[]) {
            Object[] object = (Object[]) obj;
            if (object.length == 0) {
                return true;
            }
            boolean empty = true;
            for (int i = 0; i < object.length; i++) {
                if (!isNull(object[i])) {
                    empty = false;
                    break;
                }
            }
            return empty;
        }
        return false;
    }



    /**
     * java反射机制判断对象所有属性是否全部为空
     * @param obj
     * @return
     */
    private boolean checkObjFieldIsNotNull(Object obj){
        try {
            for (Field f : obj.getClass().getDeclaredFields()) {
                f.setAccessible(true);
                if (f.get(obj) != null) {
                    return true;
                }
            }
        }catch (IllegalAccessException e){

        }
        return false;
    }

}
