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

public class MyGsonConverter<T> implements Converter<ResponseBody, T> {
    private final Gson gson;
    private final Type type;

    MyGsonConverter(Gson gson, Type type) {
        this.gson = gson;
        this.type = type;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        try {
            String response = value.string();
            BaseResponse<T> resultBaseResponse = gson.fromJson(response,type);
            //对返回码进行判断，如果是200，便返回object
            if (resultBaseResponse.code == BaseProjectConfig.SUCCESS_CODE) {
                return resultBaseResponse.data;
            } else {
                //抛出自定义服务器异常
                throw new ServerException(resultBaseResponse.code, resultBaseResponse.msg);
            }
        }finally {
//                        Utils.closeQuietly(reader);
        }
    }
}
