package com.example.quotesapp;

public class FavouriteQuoteListItem {
    private String quote;
    private int id;

    private boolean expanded;

    public FavouriteQuoteListItem(String quote, int id) {
        this.quote = quote;
        this.id = id;
    }

    public String getQuote() {
        return quote;
    }

    public int getId() {
        return id;
    }

    public boolean isExpanded() {
        return expanded;
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }
}

