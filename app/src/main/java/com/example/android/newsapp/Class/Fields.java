package com.example.android.newsapp.Class;

public class Fields {

    private String body;
    private String thumbnail;
    private String standfirst;

    Fields(String fieldBody, String fieldThumbnail, String fieldStandfirst){
        body = fieldBody;
        thumbnail = fieldThumbnail;
        standfirst = fieldStandfirst;
    }

    public String getStandfirst() {
        return standfirst;
    }

    public String getBody() {
        return body;
    }

    public String getThumbnail() {
        return thumbnail;
    }
}
