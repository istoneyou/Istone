package com.stone.njubbs.data;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Stone on 2016/4/14.
 */
public class Article implements Parcelable{
    private String mTitle;
    private String mBoard;
    private String mAuthor;
    private String mUrl;
    private String mNum;

    public Article() {

    }

    public Article(String board, String title, String url, String author, String num) {
        mTitle = title;
        mBoard = board;
        mAuthor = author;
        mUrl = url;
        mNum = num;
    }

    public Article(Parcel source) {
        mTitle = source.readString();
        mBoard = source.readString();
        mAuthor = source.readString();
        mUrl = source.readString();
        mNum = source.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mTitle);
        dest.writeString(mBoard);
        dest.writeString(mAuthor);
        dest.writeString(mUrl);
        dest.writeString(mNum);
    }

    public static final Parcelable.Creator<Article> CREATOR = new Parcelable.Creator<Article>() {
        @Override
        public Article createFromParcel(Parcel source) {
            return new Article(source);
        }

        @Override
        public Article[] newArray(int size) {
            return new Article[0];
        }
    };

    public String getAuthor() {
        return mAuthor;
    }

    public String getBoard() {
        return mBoard;
    }

    public String getNum() {
        return mNum;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setAuthor(String mAuthor) {
        this.mAuthor = mAuthor;
    }

    public void setBoard(String mBoard) {
        this.mBoard = mBoard;
    }

    public void setNum(String mNum) {
        this.mNum = mNum;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public void setUrl(String mUrl) {
        this.mUrl = mUrl;
    }
}
