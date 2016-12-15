package com.stone.njubbs.data;

import android.content.Context;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.View;

/**
 * Created by Stone on 2016/12/15.
 */

public class ClickableImage extends ClickableSpan {
    private String mUrl;
    private Context mContext;

    public  ClickableImage(Context context, String url) {
        mContext = context;
        mUrl = url;
    }

    @Override
    public void onClick(View widget) {
        Log.v("stone", "onClick");
    }
}
