package com.stone.njubbs.data;

import com.stone.njubbs.Utils.UrlUtils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * Created by youlei1 on 2018/1/19.
 */

public final class TopTenConverter implements Converter<ResponseBody, List<Article>> {
    public static final TopTenConverter INSTANCE = new TopTenConverter();

    @Override
    public List<Article> convert(ResponseBody value) throws IOException {
        return jsoupTopTenParser(value.string());
    }
    private List<Article> jsoupTopTenParser(String htmlStr) {
        List<Article> topTenData = new ArrayList();
        if (htmlStr == null || htmlStr.isEmpty()) {
            return topTenData;
        }
        Document doc = Jsoup.parse(htmlStr);
        Elements trs = doc.select("tr");
        for (int i =1; i < trs.size(); i ++ ) {
            Elements cols = trs.get(i).children();
            String board = cols.get(1).text();
            String title = cols.get(2).text();
            String uri = UrlUtils.BASE_BBS_URL + cols.get(2).select("a").attr("href").trim() + UrlUtils.ARTICLE_ALL;
            String author = cols.get(3).text();
            String num = cols.get(4).text();
            Article article = new Article(board, title, uri, author, num);
            topTenData.add(article);
        }
        return topTenData;
    }
}
