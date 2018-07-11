package com.example.android.newsapp;

public class New {

    private String title;
    private String section;
    private String url;
    public New(String nTitle, String nSection, String nUrl){

        title = nTitle;
        section = nSection;
        url = nUrl;
    }

    public String getSection() {
        return section;
    }


    public String getTitle(){
        return title;
    }

    public String getUrl(){
        return url;
    }

}
