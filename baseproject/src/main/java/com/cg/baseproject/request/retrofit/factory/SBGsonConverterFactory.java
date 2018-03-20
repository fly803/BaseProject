package com.cg.baseproject.request.retrofit.factory;

import com.cg.baseproject.request.retrofit.converter.SBResponseBody;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * 创建者: wwd
 * 创建日期:16/6/28
 * 类的功能描述:
 */
public class SBGsonConverterFactory extends Converter.Factory {
  public static SBGsonConverterFactory create() {
    return new SBGsonConverterFactory();
  }

  @Override
  public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations,
      Retrofit retrofit) {
    return new SBResponseBody<>(type);
  }
}
