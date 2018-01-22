package com.stone.njubbs.data;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * Created by youlei1 on 2018/1/19.
 */

public final class BBSDataConverterFactory extends Converter.Factory {
    public final static int CONVERTTER_GET_TOP_TEN = 1;
    public final static int CONVERTTER_GET_ARTICLE = 2;
    private static int converterType = 0;
    public static final BBSDataConverterFactory INSTANCE = new BBSDataConverterFactory();

    public static BBSDataConverterFactory create(int type) {
        converterType = type;
        return INSTANCE;
    }

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        switch (converterType) {
            case CONVERTTER_GET_TOP_TEN:
                return TopTenConverter.INSTANCE;
            case CONVERTTER_GET_ARTICLE:
                return ArticleDataConverter.INSTANCE;
            default:
                return null;
        }
    }
}
