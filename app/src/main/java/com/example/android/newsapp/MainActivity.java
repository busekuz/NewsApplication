package com.example.android.newsapp;

import android.app.Activity;
import android.view.View.OnClickListener;

import com.bumptech.glide.Glide;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.module.AppGlideModule;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String LOG_TAG = MainActivity.class.getName();
    String[] category = {"Trend","Technology", "Art", "Economy", "Sports", "Fashion", "Health","Food","Travel","Music"};
    private ArrayAdapter<String> dataForContexts;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        NewAsync object = new NewAsync();
        object.execute(category[0]);

        createSpinner();
        spinner.setOnItemSelectedListener(spinnerClickListener);

    }


    OnItemSelectedListener spinnerClickListener = new OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view,int position, long id) {
            Object item = adapterView.getItemAtPosition(position);
            Log.v("MainActivity","items position:" + position +item.toString());
            Intent intent;
            if (item != null) {
                NewAsync object = new NewAsync();
                object.execute(item.toString());
            }
        }
        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {
        }
    };

    public void createSpinner() {
        //Spinner list by adapter
        spinner = (Spinner) findViewById(R.id.sort_by_spinner);
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this, R.layout.spinner_item, category);
        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_item);
        spinner.setAdapter(spinnerArrayAdapter);
    }








    //Updates ListView on main page
    private void updateUi(ArrayList<New> news) {
        ListView newsListView = (ListView) findViewById(R.id.list);
        final NewsAdapter adapter = new NewsAdapter(this, news);
        newsListView.setAdapter(adapter);
    }



    public class NewAsync extends AsyncTask<String, Void, ArrayList<New>> {

        @Override
        protected ArrayList<New> doInBackground(String... categories) {
            // Parsing JSON file
            ArrayList<New> news = QueryUtils.extractJson(categories[0]);
            return news;
        }

        protected void onPostExecute(ArrayList<New> news) {
            // SetText etc.
            updateUi(news);
        }
    }


}
