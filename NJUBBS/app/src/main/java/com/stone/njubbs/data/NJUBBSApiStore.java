package com.stone.njubbs.data;

import com.stone.njubbs.NJUBBSApplication;
import com.stone.njubbs.Utils.NetworkUtils;
import com.stone.njubbs.Utils.UrlUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by youlei1 on 2018/1/19.
 */

public class NJUBBSApiStore {

    private final Interceptor REWRITE_RESPONSE_INTERCEPTOR_OFFLINE = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            if (!NetworkUtils.isNetworkAvailable(NJUBBSApplication.getInstance())) {
                request = request.newBuilder()
                        .cacheControl(CacheControl.FORCE_CACHE)
                        .build();
            }
            return chain.proceed(request);
        }
    };

    private final Cache cache = new Cache(new File(NJUBBSApplication.getInstance().getCacheDir(), "retrofix"), 1024 * 1024 * 100);

    public NJUBBSApi buildGetArticleApi(int type) {
        OkHttpClient client = new OkHttpClient.Builder()
                .cache(cache)
                .addInterceptor(REWRITE_RESPONSE_INTERCEPTOR_OFFLINE)
                .build();
        Retrofit retrofitNJUBBSApi = new Retrofit.Builder()
                .client(client)
                .baseUrl(UrlUtils.BASE_BBS_URL)
                .addConverterFactory(BBSDataConverterFactory.create(type))
                .build();
        return retrofitNJUBBSApi.create(NJUBBSApi.class);
    }

    public interface NJUBBSApi {
        @GET(UrlUtils.TOP_TEN)
        Call<List<Article>> getTopTen();

        @GET
        Call<List<Article>> getArticleData(@Url String url);

    }
}
