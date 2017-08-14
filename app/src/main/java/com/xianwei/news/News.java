package com.xianwei.news;

import java.util.Date;

/**
 * Created by xianwei li on 8/14/2017.
 */

public class News {
    private String title;
    private String author;
    private String section;
    private Date date;

    public News(String title, String author, String section, Date date) {
        this.title = title;
        this.author = author;
        this.section = section;
        this.date = date;
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

    public Date getDate() {
        return date;
    }
}
