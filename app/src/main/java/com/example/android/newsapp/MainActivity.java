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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    public static final String LOG_TAG = MainActivity.class.getName();
    String[] category = {"Trend","Technology", "Art", "Economy", "Sports", "Fashion", "Health","Food","Travel","Music"};
    private ArrayAdapter<String> dataForContexts;
    Spinner spinner;
    EditText inputSearch;
    NewsAdapter adapter;
    private RecyclerView recycler;
    ProgressDialog progressDialog;
    ArrayList<New> news;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        createSpinner();
        spinner.setOnItemSelectedListener(spinnerClickListener);

        GuardianAPIService service = RetrofitClientInstance.getRetrofitInstance().create(GuardianAPIService.class);

        final Call<ArrayList<New>> call = service.listNews(category[0],"all","01216ad2-f602-42c3-a90b-0a1f5e980937");

        call.enqueue(new Callback<ArrayList<New>>() {
            @Override
            public void onResponse(Call<ArrayList<New>> call, Response<ArrayList<New>> response) {
                if(response.isSuccessful()){
                    updateUi((ArrayList<New>) call);
                }
                else{
                    Toast.makeText(MainActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<ArrayList<New>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "error!" , Toast.LENGTH_SHORT).show();
            }
        });

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


                //NewAsync object = new NewAsync();
                //object.execute(item.toString());
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
    private void updateUi(ArrayList<New> news){
        RecyclerView newsListView = (RecyclerView) findViewById(R.id.list);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        newsListView.setLayoutManager(manager);

        //List<Results> realNews = news.getResponse().getResult();
        adapter = new NewsAdapter(this, news); //    boyleydi : NewsAdapter(this,R.layout.new_list,news);
        newsListView.setAdapter(adapter);
        }
}
