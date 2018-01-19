package com.stone.njubbs.data;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * Created by youlei1 on 2018/1/19.
 */

public final class TopTenConverterFactory extends Converter.Factory {
    public static final TopTenConverterFactory INSTANCE = new TopTenConverterFactory();

    public static TopTenConverterFactory create() {
        return INSTANCE;
    }
    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        return TopTenConverter.INSTANCE;
    }
}
