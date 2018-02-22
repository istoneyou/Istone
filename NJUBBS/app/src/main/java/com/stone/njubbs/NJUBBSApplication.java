package com.stone.njubbs;

import android.app.Application;
import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by Stone on 2016/11/8.
 */

public class NJUBBSApplication extends Application {

    public static RequestQueue requestQueue;
    public static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        requestQueue = Volley.newRequestQueue(this);
        mContext = getApplicationContext();
    }

    public static RequestQueue getRequestQueue() {
        return requestQueue;
    }

    public static Context getInstance() {
        return mContext;
    }
}
