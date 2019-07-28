package com.example.quotesapp;

import java.util.List;

public class Quote {

    private String text;
    private String author;
    private List<String> categories;
    private int favouriteInt;

    public Quote (String text, String author, List<String> categories, int favouriteInt){
        this.text = text;
        this.author = author;
        this.categories = categories;
        this.favouriteInt = favouriteInt;
    }

    public String getText() {
        return text;
    }

    public String getAuthor() {
        return author;
    }

    public List<String> getCategories() {
        return categories;
    }

    public int getFavouriteInt() {
        return favouriteInt;
    }
}
