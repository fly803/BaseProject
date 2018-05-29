package com.cg.baseproject.utils;

import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonUtils {
	
	public static <T> T json2Bean(String result , Class<T> clazz){
        Gson gson = new Gson();
        T t = gson.fromJson(result, clazz);
        return t;
       
   }
	
    /**
     * @Title: toJson
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param bean
     * @return String 返回类型
     * @throws：
     */
    public static String toJson(Object bean){
    	Gson gson = new Gson();
        return gson.toJson(bean);
    }
    
    public static String toJson(Object bean,Type type){
    	Gson gson = new Gson();
        return gson.toJson(bean, type);
    }
    
    /**
     * @Title: fromJson
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param json
     * @param type
     * @return T 返回类型
     * @throws：
     */
    public static Object fromJson(String json,Type type){
    	Gson gson = new Gson();
        return gson.fromJson(json, type);
    }
    
    /**
     * @Title: fromJson
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param <T>
     * @param json
     * @param classOfT
     * @return T 返回类型
     * @throws：
     */
    public  static <T>T fromJson(String json,Class<T> classOfT){
    	Gson gson = new Gson();
        return gson.fromJson(json, classOfT);
    }
}
