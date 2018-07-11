package com.example.android.newsapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static final String LOG_TAG = MainActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


       ArrayList<New> news = QueryUtils.extractJson();

        ListView newsListView = (ListView) findViewById(R.id.list);


        final NewsAdapter adapter = new NewsAdapter(this, news);

        newsListView.setAdapter(adapter);

    }
}
