package com.example.android.newsapp;

import java.util.List;

public class ResponseOfNew {

    private String status;
    List<Results> result;
   // Results[] result;
    ResponseOfNew(String responseStatus){
        status = responseStatus;
    }

    public String getStatus() {
        return status;
    }

    public List<Results> getResult() {
        return result;
    }
}
