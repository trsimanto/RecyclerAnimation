package com.towhid.recyclerviewanimation;

public class NewsItem {
    private String title, content, date;
    private int userPhoto;

    public NewsItem() {
    }


    public NewsItem(String title, String content, String date, int userPhoto) {
        this.title = title;
        this.content = content;
        this.date = date;
        this.userPhoto = userPhoto;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getUserPhoto() {
        return userPhoto;
    }

    public void setUserPhoto(int userPhoto) {
        this.userPhoto = userPhoto;
    }
}
