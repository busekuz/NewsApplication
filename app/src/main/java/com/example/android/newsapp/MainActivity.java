package com.example.android.newsapp;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static final String LOG_TAG = MainActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        NewAsync a = new NewAsync();
        a.execute();

    }
    private void updateUi(ArrayList<New> news) {

        ListView newsListView = (ListView) findViewById(R.id.list);


        final NewsAdapter adapter = new NewsAdapter(this, news);

        newsListView.setAdapter(adapter);
        newsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // Find the current new that was clicked on
                New currentNew = adapter.getItem(position);

                // Convert the String URL into a URI object (to pass into the Intent constructor)
                Uri newUri = Uri.parse(currentNew.getUrl());

                // Create a new intent to view the earthquake URI
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, newUri);

                // Send the intent to launch a new activity
                startActivity(websiteIntent);
            }
        });
    }
    public class NewAsync extends AsyncTask<Void,Void,ArrayList<New>>{


        @Override
        protected ArrayList<New> doInBackground(Void... voids) {
            ArrayList<New> news = QueryUtils.extractJson();
            Log.v("MainActivity","asynctask do in background" + news);

            return news;
        }

        protected void onPostExecute(ArrayList<New> news) {
            updateUi(news);
        }
        }
    }
