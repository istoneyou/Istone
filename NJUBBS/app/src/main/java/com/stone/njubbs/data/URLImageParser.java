package com.stone.njubbs.data;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.stone.njubbs.NJUBBSApplication;
import com.stone.njubbs.R;
import com.stone.njubbs.Utils.NetworkUtils;

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
                    Matrix matrix = NetworkUtils.getBitmapMatrix(rawBitmap.getWidth(), rawBitmap.getHeight(), DEFAULT_WIDTH, DEFAULT_HEIGH);
                    Bitmap result = Bitmap.createBitmap(rawBitmap, 0, 0, rawBitmap.getWidth(), rawBitmap.getHeight(), matrix, true);
                    mDrawable.bitmap = result;
                    mTextView.setText(mTextView.getText());
                } else {
                }
            }

            @Override
            public void onErrorResponse(VolleyError error) {

            }
        };
        Bitmap bt = imageLoader.get(source, listeners).getBitmap();
        if (bt != null) {
            Matrix matrix = NetworkUtils.getBitmapMatrix(bt.getWidth(), bt.getHeight(), DEFAULT_WIDTH, DEFAULT_HEIGH);
            Bitmap result = Bitmap.createBitmap(bt, 0, 0, bt.getWidth(), bt.getHeight(), matrix, true);
            mDrawable.bitmap = result;
        } else {
            mDrawable.bitmap = ((BitmapDrawable) mDefaultDrawable).getBitmap();
        }
        mDrawable.setBounds(0, 0, DEFAULT_WIDTH, DEFAULT_HEIGH);
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
