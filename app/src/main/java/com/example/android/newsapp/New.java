package com.example.android.newsapp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Date;


import android.util.Log;

public class New {

    private String title;
    private String section;
    private String url;
    private String body;
    private String thumbnail;
    private String standfirst;
    private String date;

    public New(String nTitle, String nSection, String nUrl,String nBody,String nThumbnail,String nStand,String nDate){

        title = nTitle;
        section = nSection;
        url = nUrl;
        body = nBody;
        thumbnail = nThumbnail;
        standfirst = nStand;
        date = nDate;
    }

    public String getDate() {

        return date;
    }

    public String getStandfirst() {
        return standfirst;
    }

    public String getThumbnail() {
        return thumbnail;
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
