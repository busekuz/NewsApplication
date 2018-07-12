package com.example.android.newsapp;

public class New {

    private String title;
    private String section;
    private String url;
    private String body;
    public New(String nTitle, String nSection, String nUrl,String nBody){

        title = nTitle;
        section = nSection;
        url = nUrl;
        body = nBody;
    }

    public String getBody() {
        return body;
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
