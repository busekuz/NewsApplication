package com.example.android.newsapp;

public class Results {

    private String webTitle;
    private String webPublicationDate;
    private String sectionName;
    private String webUrl;
    Fields field;

    Results(String rWebTitle,String rWebPublicationDate ,String rSectionName,String rWebUrl){
        webTitle = rWebTitle;
        webPublicationDate = rWebPublicationDate;
        sectionName = rSectionName;
        webUrl = rWebUrl;
    }


    public Fields getField() {
        return field;
    }

    public String getSectionName() {
        return sectionName;
    }

    public String getWebPublicationDate() {
        return webPublicationDate;
    }

    public String getWebTitle() {
        return webTitle;
    }

    public String getWebUrl() {
        return webUrl;
    }
}
