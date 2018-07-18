package com.example.android.newsapp;

import android.app.ProgressDialog;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.android.newsapp.Adapter.NewsAdapter;
import com.example.android.newsapp.Class.New;
import com.example.android.newsapp.Network.GuardianAPIService;
import com.example.android.newsapp.Network.RetrofitClientInstance;

import butterknife.BindDrawable;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemSelected;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



public class MainActivity extends AppCompatActivity {

    //Spinner categories
    String[] category = {"Trend","Turkey", "Technology", "Art", "Economy", "Sports", "Health", "Travel", "Food"};
    @BindView(R.id.sort_by_spinner) Spinner spinner;

    NewsAdapter adapter;

    @BindView(R.id.list) RecyclerView recycler;

    ProgressDialog progressBar;



    //Info for API request.
    int i = 0;
    String key = "01216ad2-f602-42c3-a90b-0a1f5e980937";
    GuardianAPIService service = RetrofitClientInstance.getRetrofitInstance().create(GuardianAPIService.class);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = new ProgressDialog(MainActivity.this);
        progressBar.setMessage("Loading...");
        progressBar.show();

        createSpinner();
        callRequest();
    }



    @OnClick(R.id.reloadButton)
    public void buttonClick(View v) {
        ButterKnife.bind(this);
        Intent intent = getIntent(); //Reloads main page
        finish();
        startActivity(intent);
    }


    public void callRequest() {

        Call<New> call = service.listNews(category[i], "all", key);

        call.enqueue(new Callback<New>() {
            @Override
            public void onResponse(Call<New> call, Response<New> response) {
                progressBar.dismiss();

                if (response.isSuccessful()) {
                    updateUi(response.body());
                } else {
                    Toast.makeText(MainActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<New> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error! Can not get response.", Toast.LENGTH_SHORT).show();
                progressBar.dismiss();
            }
        });
    }

    @OnItemSelected(R.id.sort_by_spinner)
    public void spinnerItemSelected(Spinner spinner, int position) {

        String item = category[position];

        Log.v("MainActivity", "Item's position: " + position + " Category: " + item);
        Intent intent;
        if (item != null) {
            i = position;
            callRequest();
        }

    }


    public void createSpinner() {

        //Creates spinner list by adapter in order to choose categories

        ButterKnife.bind(this);
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this, R.layout.spinner_item, category);
        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_item);
        spinner.setAdapter(spinnerArrayAdapter);
    }

    private void updateUi(New news) {
        ButterKnife.bind(this);

        //Updates RecycleView on main page

        LinearLayoutManager manager = new LinearLayoutManager(this);
        recycler.setLayoutManager(manager);
        adapter = new NewsAdapter(this, news);
        recycler.setAdapter(adapter);
    }
}
