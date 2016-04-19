package com.stone.njubbs.data;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.stone.njubbs.R;

/**
 * Created by Stone on 2016/4/19.
 */
public class URLImageParser implements Html.ImageGetter{

    Context mContext;
    TextView mTextView;
    ImageLoader.ImageCache mImageCache;
    private Drawable mDefaultDrawable;


    public URLImageParser(Context context, TextView textView, ImageLoader.ImageCache imageCache) {
        mContext = context;
        mTextView = textView;
        mImageCache = imageCache;
        mDefaultDrawable = mContext.getDrawable(R.drawable.ic_menu_camera);

    }

    @Override
    public Drawable getDrawable(final String source) {
        Drawable mDrawable;
        mDefaultDrawable.setBounds(0, 0, 300, 300);

        ImageLoader imageLoader = new ImageLoader(Volley.newRequestQueue(mContext), mImageCache);
        ImageLoader.ImageListener listeners = new ImageLoader.ImageListener() {
            @Override
            public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
                if (response.getBitmap() != null) {
                } else {
                    Log.v("youlei1", "======== null");
                }

            }

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.v("youlei1", "error");

            }
        };
        mDrawable = new BitmapDrawable(imageLoader.get(source, listeners).getBitmap());
        mDrawable.setBounds(0, 0, 600, 800);

        Log.v("youlei1", "return");
        return mDrawable;
    }

    @SuppressWarnings("deprecation")
    public class UrlDrawable extends BitmapDrawable {
        protected Bitmap bitmap;

        @Override
        public void draw(Canvas canvas) {
            // override the draw to facilitate refresh function later
            if (bitmap != null) {
                canvas.drawBitmap(bitmap, 0, 0, getPaint());
            }
        }
    }
}
