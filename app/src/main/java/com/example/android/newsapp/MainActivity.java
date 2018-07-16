package com.example.android.newsapp;

import android.app.Activity;
import android.app.ProgressDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    public static final String LOG_TAG = MainActivity.class.getName();
    String[] category = {"Trend","Technology", "Art", "Economy", "Sports", "Fashion", "Health","Food","Travel","Music"};
    private ArrayAdapter<String> dataForContexts;
    Spinner spinner;
    EditText inputSearch;
    NewsAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        NewAsync object = new NewAsync();
        object.execute(category[0]);

        createSpinner();
        spinner.setOnItemSelectedListener(spinnerClickListener);



    }

    public void buttonClick (View v){
        Intent intent = getIntent();
        finish();
        startActivity(intent);
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
        RecyclerView newsListView = (RecyclerView) findViewById(R.id.list);

        LinearLayoutManager manager = new LinearLayoutManager(this);
        newsListView.setLayoutManager(manager);
        //newsListView.setHasFixedSize(true);
        //adapter = new NewsAdapter(this, news);
        adapter = new NewsAdapter(this,R.layout.new_list,news);
        newsListView.setAdapter( adapter );

        /*
        inputSearch = (EditText) findViewById(R.id.inputSearch);
        inputSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                // When user changed the Text
                MainActivity.this.adapter.getFilter().filter(cs);
            }
            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
            }
            @Override
            public void afterTextChanged(Editable arg0) {
            }
        });*/
    }

    public class NewAsync extends AsyncTask<String, Void, ArrayList<New>> {
        ProgressDialog progDialog = new ProgressDialog(MainActivity.this);

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progDialog.setMessage("Loading...");
            progDialog.setIndeterminate(false);
            progDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progDialog.setCancelable(true);
            progDialog.show();
        }

        @Override
        protected ArrayList<New> doInBackground(String... categories) {
            // Parsing JSON file
            ArrayList<New> news = QueryUtils.extractJson(categories[0]);
            return news;
        }

        protected void onPostExecute(ArrayList<New> news) {
            // SetText etc.
            progDialog.dismiss();
            updateUi(news);
        }



    }


}
