package com.example.android.newsapp;
import android.util.Log;

import java.io.*;
import java.net.*;

public class CustomUrl {
    String url;
    public CustomUrl(String nUrl){
        url = nUrl;
    }
    public String getHTML() throws Exception {
        StringBuilder result = new StringBuilder();


        // Created URL
        URL myUrl = null;
        try {
            myUrl = new URL(url);
        } catch (MalformedURLException e) {
            Log.e("CustomUrl", "Error with creating URL ", e);
        }

        HttpURLConnection con = (HttpURLConnection) myUrl.openConnection();
        con.setRequestMethod("GET");
        con.connect();


        BufferedReader rd = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String line;
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
        rd.close();

        if (con != null) {
            con.disconnect();
        }

        return result.toString();


    }


}
