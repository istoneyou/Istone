package com.stone.njubbs.Utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Stone on 2016/4/15.
 */
public class NetworkUtils {

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();
        return  (info != null && info.isConnected());
    }

    public static  boolean isAvailablePicUrl(Context context, String str) {
        return (str.startsWith("http") && (str.endsWith(".jpg") || str.endsWith(".png") || str.endsWith(".jpeg")));
    }
}
