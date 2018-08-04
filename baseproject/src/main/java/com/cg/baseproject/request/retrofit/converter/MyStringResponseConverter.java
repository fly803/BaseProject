package com.cg.baseproject.request.retrofit.converter;

import com.cg.baseproject.configs.BaseProjectConfig;
import com.cg.baseproject.request.data.BaseResponse;
import com.cg.baseproject.request.exception.ServerException;
import com.google.gson.Gson;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * @author sam
 * @version 1.0
 * @date 2018/3/14
 */

public class MyStringResponseConverter implements Converter<ResponseBody, String> {
    
    @Override
    public String convert(ResponseBody value) throws IOException {
        try {
            return value.string();
        } finally {
            value.close();
        }
    }
}

