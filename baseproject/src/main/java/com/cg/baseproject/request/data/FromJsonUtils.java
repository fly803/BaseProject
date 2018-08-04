package com.cg.baseproject.request.data;

import com.google.gson.GsonBuilder;

/**
 * @author sam
 * @version 1.0
 * @date 2018/4/13
 */
public class FromJsonUtils {
    public static BaseResponse fromJson(String json, Class clazz) {
        return new GsonBuilder()
                .registerTypeAdapter(BaseResponse.class, new JsonFormatParser(clazz))
                .enableComplexMapKeySerialization()
                .serializeNulls()
                .create()
                .fromJson(json, BaseResponse.class);
    }
}
