package com.stone.njubbs.data;

import com.stone.njubbs.Utils.NetworkUtils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * Created by youlei1 on 2018/1/22.
 */

public final class ArticleDataConverter implements Converter<ResponseBody, List<Article>> {
    public static final ArticleDataConverter INSTANCE = new ArticleDataConverter();

    @Override
    public List<Article> convert(ResponseBody value) throws IOException {
        return jsoupTopicAndComments(value.string());
    }

    private List<Article> jsoupTopicAndComments(String htmlStr) {
        List<Article> mData = new ArrayList();
        Document doc = Jsoup.parse(htmlStr);
        Elements tables = doc.select("table");
        for(Element table : tables) {
            Element textArea = table.select("textarea").first();
            String[] strings = textArea.text().replaceAll("\r", "").split("\n");
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 3; i < strings.length -1; i ++)
            {
                String s = strings[i].trim();
                if (s.endsWith("[m")) {
                    break;
                }
                if (!s.isEmpty()) {
                    if (NetworkUtils.isAvailablePicUrl(s)) {
                        stringBuilder.append("<br>");
                        stringBuilder.append("<img src='");
                        stringBuilder.append(s);
                        stringBuilder.append("'/>");
                        stringBuilder.append("<br>");
                    } else {
                        stringBuilder.append(strings[i]);
                    }
                }
            }
            Article article = new Article();
            article.setTitle(stringBuilder.toString());
            mData.add(article);
        }
        return mData;
    }
}
