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
import com.stone.njubbs.NJUBBSApplication;
import com.stone.njubbs.R;

/**
 * Created by Stone on 2016/4/19.
 */
public class URLImageParser implements Html.ImageGetter{
    private final int DEFAULT_WIDTH = 800;
    private final int DEFAULT_HEIGH = 800;

    Context mContext;
    TextView mTextView;
    ImageLoader.ImageCache mImageCache;
    private Drawable mDefaultDrawable;


    public URLImageParser(Context context, TextView textView, ImageLoader.ImageCache imageCache) {
        mContext = context;
        mTextView = textView;
        mImageCache = imageCache;
        mDefaultDrawable = mContext.getDrawable(R.mipmap.ic_launcher);

    }

    @Override
    public Drawable getDrawable(final String source) {
        final UrlDrawable mDrawable = new UrlDrawable();
        mDefaultDrawable.setBounds(0, 0, DEFAULT_WIDTH, DEFAULT_HEIGH);
        ImageLoader imageLoader = new ImageLoader(NJUBBSApplication.getRequestQueue(), mImageCache);
        ImageLoader.ImageListener listeners = new ImageLoader.ImageListener() {
            @Override
            public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
                Bitmap rawBitmap = response.getBitmap();
                if (rawBitmap != null) {
                    mDrawable.bitmap = rawBitmap;
                    mDrawable.setBounds(0, 0, mDrawable.bitmap.getWidth(), mDrawable.bitmap.getHeight());
                    mTextView.setText(mTextView.getText());
                } else {
                }
            }

            @Override
            public void onErrorResponse(VolleyError error) {

            }
        };
        Bitmap bt = imageLoader.get(source, listeners, DEFAULT_WIDTH, DEFAULT_HEIGH).getBitmap();
        if (bt == null) {
            mDrawable.bitmap = ((BitmapDrawable) mDefaultDrawable).getBitmap();
            mDrawable.setBounds(0, 0, mDrawable.bitmap.getWidth(), mDrawable.bitmap.getHeight());
        }
        return mDrawable;
    }

    @SuppressWarnings("deprecation")
    public class UrlDrawable extends BitmapDrawable {
        protected Bitmap bitmap;

        @Override
        public void draw(Canvas canvas) {
            if (bitmap != null) {
                canvas.drawBitmap(bitmap, 0, 0, getPaint());
            }
        }
    }
}
