package com.stone.njubbs.Utils;

import android.content.Context;
import android.graphics.Matrix;
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

    public static  boolean isAvailablePicUrl(String str) {
        str = str.toLowerCase();
        return (str.startsWith("http") && (str.endsWith(".JPG") || str.endsWith(".jpg")
                || str.endsWith(".png") || str.endsWith(".jpeg") || str.endsWith(".gif")));
    }

    public static Matrix getBitmapMatrix(int srcWidth, int srcHeight, int dstWidth, int dstHeight) {
        Matrix matrix = new Matrix();
        float scaleWidth = ((float) dstWidth) / srcWidth;
        float scaleHeight = ((float) dstHeight) / srcHeight;
        if (scaleWidth > 1 && scaleHeight > 1) {
            scaleWidth = 1;
        } else {
            scaleWidth = scaleWidth > scaleHeight ? scaleHeight : scaleWidth;
        }
        matrix.postScale(scaleWidth, scaleWidth);
        return matrix;
    }
}
