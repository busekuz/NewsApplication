package com.example.android.newsapp;

public class New {

    private String title;
    private String section;

    public New(String nTitle, String nSection){

        title = nTitle;
        section = nSection;
    }

    public String getSection() {
        return section;
    }


    public String getTitle(){
        return title;
    }


}
