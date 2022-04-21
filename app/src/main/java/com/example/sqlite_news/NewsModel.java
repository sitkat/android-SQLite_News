package com.example.sqlite_news;

public class NewsModel {
    String name,description,date;

    public NewsModel(String name, String description, String date) {
        this.name = name;
        this.description = description;
        this.date = date;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getDate() {
        return date;
    }
}
