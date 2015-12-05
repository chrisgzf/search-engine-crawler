package me.chrisgoh.BingCrawler;

/**
 * Created by chris on 5/12/15.
 */
public class BingResult {
    private String title;
    private String url;
    private int page;
    private int rank;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public BingResult(String title, String url, int page, int rank) {
        this.title = title;
        this.url = url;
        this.page = page;
        this.rank = rank;
    }
}
