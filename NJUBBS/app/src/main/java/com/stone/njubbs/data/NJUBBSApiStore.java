package com.stone.njubbs.data;

import com.stone.njubbs.Utils.UrlUtils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.http.GET;

/**
 * Created by youlei1 on 2018/1/19.
 */

public class NJUBBSApiStore {

    public NJUBBSApi buildTopTenApi() {
        Retrofit retrofitNJUBBSApi = new Retrofit.Builder()
                .baseUrl(UrlUtils.URL_BBS)
                .addConverterFactory(TopTenConverterFactory.create())
                .build();
        return retrofitNJUBBSApi.create(NJUBBSApi.class);
    }

    public interface NJUBBSApi {
        @GET(UrlUtils.TOP_TEN)
        Call<List<Article>> getTopTen();

    }
}
