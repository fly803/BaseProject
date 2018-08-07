package com.cg.baseproject.request.retrofit.factory;

import com.cg.baseproject.request.retrofit.converter.MyStringRequestBodyConverter;
import com.cg.baseproject.request.retrofit.converter.MyStringResponseConverter;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;
/**
 * @author
 * @version 1.0
 * @date 2018/8/4
 */
public class MyStringConverterFactory extends Converter.Factory {

    public static final MyStringConverterFactory INSTANCE = new MyStringConverterFactory();

    public static MyStringConverterFactory create() {
        return INSTANCE;
    }

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations,
                                                            Retrofit retrofit) {
        return new MyStringResponseConverter();
    }

    @Override
    public Converter<?, RequestBody> requestBodyConverter(Type type,
                                                          Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {
        return new MyStringRequestBodyConverter();
    }

}
