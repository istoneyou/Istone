package com.stone.njubbs.data;

import android.content.Context;
import android.text.Editable;
import android.text.Html;
import android.text.Spanned;
import android.text.style.ImageSpan;

import org.xml.sax.XMLReader;

import java.util.Locale;

/**
 * Created by Stone on 2016/12/15.
 */

public class URLImageTagHandler implements Html.TagHandler {

    public  static final String IMG_TAG = "img";
    private Context mContext;

    public URLImageTagHandler(Context context) {
        mContext = context;
    }
    @Override
    public void handleTag(boolean opening, String tag, Editable output, XMLReader xmlReader) {
        if (tag.toLowerCase(Locale.getDefault()).equals(IMG_TAG)) {
            int len = output.length();
            ImageSpan[] imageSpen = output.getSpans(len-1, len, ImageSpan.class);
            String imgURL = imageSpen[0].getSource();
            output.setSpan(new ClickableImage(mContext, imgURL), len -1, len, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
    }
}
