package com.example.android.newsapp.Class;

import java.util.List;

public class ResponseOfNew {

    private String status;
    List<Results> results;

    ResponseOfNew(String responseStatus){
        status = responseStatus;
    }

    public String getStatus() {
        return status;
    }

    public List<Results> getResults() {
        return results;
    }
}
