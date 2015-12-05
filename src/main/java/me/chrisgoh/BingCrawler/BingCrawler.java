package me.chrisgoh.BingCrawler;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chris on 5/12/15.
 */
public class BingCrawler {
    public static final List<String> searchTerms = new ArrayList<String>();
    public static final String baseUrl = "http://www.bing.com/search?q=";
    public static final List<BingResult> searchResults = new ArrayList<BingResult>();

    public static void main(String[] args) throws Exception {
        searchTerms.add("tsunami");

        for (String searchTerm : searchTerms) {
            StringBuilder queryUrl = new StringBuilder(baseUrl);
            queryUrl.append(searchTerm);

            Connection.Response res = Jsoup.connect(queryUrl.toString()).userAgent(Constants.CRAWLER_USER_AGENT).execute();
            Document result = res.parse();

            Element nextPage;
            int page = 0;
            do {
                System.out.println("Page: " + ++page);
                Elements links = result.select("h2>a");

                for (Element link : links) {
                    String title = link.text();
                    String url_string = link.attr("abs:href");

                    if (url_string.startsWith("http://www.bing.com/"))
                        // Some Bing internal link, not important
                        continue;

                    System.out.println("title = " + title);
                    System.out.println("url_string = " + url_string);
                }

                nextPage = result.select(".sb_pagN").isEmpty() ? null : result.select(".sb_pagN").first();
                if (nextPage != null) {
                    res = Jsoup.connect(nextPage.attr("abs:href")).cookies(res.cookies()).userAgent(Constants.CRAWLER_USER_AGENT).execute();
                    result = res.parse();
                }
            } while (nextPage != null);
        }
    }
}
