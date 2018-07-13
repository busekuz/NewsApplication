package com.example.android.newsapp;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
public final class QueryUtils {

    public QueryUtils(){

    }

    public static ArrayList<New> extractJson(String category){
        CustomUrl myurl = new CustomUrl("https://content.guardianapis.com/search?q=%27"+category+"%27&show-fields=all&api-key=01216ad2-f602-42c3-a90b-0a1f5e980937");
        String SAMPLE_JSON_RESPONSE = null;
        try {
            SAMPLE_JSON_RESPONSE = myurl.getHTML();
        } catch (Exception e) {
            e.printStackTrace();
        }
        ArrayList<New> news = new ArrayList<>();

        try {

            JSONObject baseJsonResponse = new JSONObject(SAMPLE_JSON_RESPONSE);
            JSONObject response = baseJsonResponse.getJSONObject("response");
            JSONArray resultArray = response.getJSONArray("results");

            int i;
            for(i = 0 ; i < resultArray.length() ; i++){

                JSONObject latestNew = resultArray.getJSONObject(i);
                JSONObject fields = latestNew.getJSONObject("fields");
                String body = fields.getString("body");
                String thumbnail = fields.getString("thumbnail");
                String standfirst = fields.getString("standfirst");
                String titleNew = latestNew.getString("webTitle");
                String date = latestNew.getString("webPublicationDate");
                String titleSec = latestNew.getString("sectionName");
                String url = latestNew.getString("webUrl");


                New techNew = new New(titleNew,titleSec, url,body,thumbnail,standfirst,date);

                news.add(techNew);
                Log.v("QueryUtils","Title -- " + titleNew  + "\tdate:" + date);

            }

        }catch (JSONException e) {
            // If an error is thrown when executing any of the above statements in the "try" block,
            // catch the exception here, so the app doesn't crash. Print a log message
            // with the message from the exception.
            Log.e("QueryUtils", "Problem parsing the earthquake JSON results", e);
        }
        return news;
    }
}
