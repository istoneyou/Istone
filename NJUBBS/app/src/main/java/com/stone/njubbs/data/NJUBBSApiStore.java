package com.stone.njubbs.data;

import com.stone.njubbs.Utils.UrlUtils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by youlei1 on 2018/1/19.
 */

public class NJUBBSApiStore {

    public NJUBBSApi buildGetArticleApi(int type) {
        Retrofit retrofitNJUBBSApi = new Retrofit.Builder()
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
