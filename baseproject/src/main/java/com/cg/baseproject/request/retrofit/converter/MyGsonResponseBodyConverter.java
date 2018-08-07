package com.cg.baseproject.request.retrofit.converter;

import com.cg.baseproject.configs.BaseProjectConfig;
import com.cg.baseproject.request.data.BaseResponse;
import com.cg.baseproject.request.data.FromJsonUtils;
import com.cg.baseproject.request.data.HttpStatus;
import com.cg.baseproject.request.exception.ApiException;
import com.cg.baseproject.request.exception.ServerException;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.nio.charset.Charset;

import okhttp3.MediaType;
import okhttp3.ResponseBody;
import retrofit2.Converter;

import static okhttp3.internal.Util.UTF_8;

/**
 * https://www.jianshu.com/p/5b8b1062866b
 * @author sam
 * @version 1.0
 * @date 2018/3/14
 */

public class MyGsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private  Gson gson;
    private  TypeAdapter<T> adapter;
    
    private Class<T> clazz;

    public MyGsonResponseBodyConverter(Class<T> clazz) {
        this.clazz = clazz;
    }
    
    public MyGsonResponseBodyConverter(Gson gson, TypeAdapter<T> adapter) {
        this.gson = gson;
        this.adapter = adapter;
    }


    
/*    @Override
    public T convert(ResponseBody value) throws IOException {
//        String response = value.string();
//        HttpStatus httpStatus = gson.fromJson(response, HttpStatus.class);
//        if (httpStatus.isCodeInvalid()) {
//            value.close();
//            throw new ApiException(httpStatus.getCode(), httpStatus.getMessage());
//        }
        JsonReader jsonReader = gson.newJsonReader(value.charStream());
        try {
            return adapter.read(jsonReader);
        } finally {
            value.close();
        }
    }*/

    @Override
    public T convert(ResponseBody value) throws IOException {
        String responseString = value.string();
//        responseString = FromJsonUtils.fromJson(responseString,BaseResponse.class).toString();
        MediaType contentType = value.contentType();
        Charset charset = contentType != null ? contentType.charset(UTF_8) : UTF_8;
        InputStream inputStream = new ByteArrayInputStream(responseString.getBytes());
        Reader reader = new InputStreamReader(inputStream, charset);
        JsonReader jsonReader = gson.newJsonReader(reader);
        try {
            return adapter.read(jsonReader);
        } finally {
            value.close();
        }
    }
}
