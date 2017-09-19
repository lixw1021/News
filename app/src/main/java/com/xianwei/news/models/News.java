package com.xianwei.news.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by xianwei li on 8/14/2017.
 */

public class News implements Parcelable {
    private String title;
    private String description;
    private String urlString;
    private String imageUrlString;
    private String date;

    public News(String title, String description, String urlString, String imageUrlString, String date) {
        this.title = title;
        this.description = description;
        this.urlString = urlString;
        this.imageUrlString = imageUrlString;
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getUrlString() {
        return urlString;
    }

    public String getImageUrlString() {
        return imageUrlString;
    }

    public String getDate() {
        return date;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.description);
        dest.writeString(this.urlString);
        dest.writeString(this.imageUrlString);
        dest.writeString(this.date);
    }

    protected News(Parcel in) {
        this.title = in.readString();
        this.description = in.readString();
        this.urlString = in.readString();
        this.imageUrlString = in.readString();
        this.date = in.readString();
    }

    public static final Parcelable.Creator<News> CREATOR = new Parcelable.Creator<News>() {
        @Override
        public News createFromParcel(Parcel source) {
            return new News(source);
        }

        @Override
        public News[] newArray(int size) {
            return new News[size];
        }
    };
}
