package com.xianwei.news.models;

/**
 * Created by xianwei li on 8/14/2017.
 */

public class News {
    private String title;
    private String author;
    private String section;
    private String date;
    private String webUrl;

    public News(String title, String author, String section, String date, String webUrl) {
        this.title = title;
        this.author = author;
        this.section = section;
        this.date = date;
        this.webUrl = webUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getSection() {
        return section;
    }

    public String getDate() {
        return date;
    }

    public String getWebUrl() {
        return webUrl;
    }
}
