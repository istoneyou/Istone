package com.stone.njubbs.data;

/**
 * Created by Stone on 2016/4/14.
 */
public class Article {
    private String mTitle;
    private String mBoard;
    private String mAuthor;
    private String mUri;
    private String mNum;

    public Article(String board, String title, String uri, String author, String num) {
        mTitle = title;
        mBoard = board;
        mAuthor = author;
        mUri = uri;
        mNum = num;
    }

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

    public String getUri() {
        return mUri;
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

    public void setUri(String mUri) {
        this.mUri = mUri;
    }
}
