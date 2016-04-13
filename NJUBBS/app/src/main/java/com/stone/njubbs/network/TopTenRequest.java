package com.stone.njubbs.network;

import android.util.Log;

import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;

import java.io.UnsupportedEncodingException;

/**
 * Created by Stone on 2016/4/13.
 */
public class TopTenRequest extends StringRequest{
    public TopTenRequest(String url, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        super(url, listener, errorListener);
    }

    public TopTenRequest(int method, String url, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        super(method, url, listener, errorListener);
    }

//    @Override
//    protected Response<String> parseNetworkResponse(NetworkResponse response) {
//        String parsed;
//        try {
//            parsed = new String(response.data, "utf-8");
//        } catch (UnsupportedEncodingException e) {
//            parsed = new String(response.data);
//        }
//        return Response.success(parsed, HttpHeaderParser.parseCacheHeaders(response));
//    }
}
