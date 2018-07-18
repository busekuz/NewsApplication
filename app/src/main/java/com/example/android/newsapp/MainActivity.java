package com.example.android.newsapp;

import android.app.ProgressDialog;
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
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

import com.example.android.newsapp.Adapter.NewsAdapter;
import com.example.android.newsapp.Class.New;
import com.example.android.newsapp.Network.GuardianAPIService;
import com.example.android.newsapp.Network.RetrofitClientInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    //Spinner categories
    String[] category = {"Trend","Technology", "Art", "Economy", "Sports", "Fashion", "Health","Food","Travel","Music"};
    Spinner spinner;

    NewsAdapter adapter;
    private RecyclerView recycler;
    ProgressDialog progressBar;


    //Info for API request.
    int i = 0;
    String  key = "01216ad2-f602-42c3-a90b-0a1f5e980937";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recycler =  findViewById(R.id.list);

        progressBar = new ProgressDialog(MainActivity.this);
        progressBar.setMessage("Loading...");
        progressBar.show();

        createSpinner();
        spinner.setOnItemSelectedListener(spinnerClickListener);

        GuardianAPIService service = RetrofitClientInstance.getRetrofitInstance().create(GuardianAPIService.class);

        Call<New> call = service.listNews(category[i],"all",key);

        call.enqueue(new Callback<New>() {
            @Override
            public void onResponse(Call<New> call, Response<New> response) {
                progressBar.dismiss();

                if(response.isSuccessful()){
                    updateUi(response.body());
                }
                else{
                    Toast.makeText(MainActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<New> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error!" , Toast.LENGTH_SHORT).show();
                progressBar.dismiss();
            }
        });

    }



    public void buttonClick (View v){
        // Reloads main page
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }


    OnItemSelectedListener spinnerClickListener = new OnItemSelectedListener() {
        // Switches between categories
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view,int position, long id) {
            Object item = adapterView.getItemAtPosition(position);
            Log.v("MainActivity","items position: "  + position + " " + item.toString());
            Intent intent;
            if (item != null) {
                i = position;
                GuardianAPIService service = RetrofitClientInstance.getRetrofitInstance().create(GuardianAPIService.class);
                Call<New> call = service.listNews(category[i],"all","01216ad2-f602-42c3-a90b-0a1f5e980937");
                call.enqueue(new Callback<New>() {
                    @Override
                    public void onResponse(Call<New> call, Response<New> response) {
                        if(response.isSuccessful()){
                            updateUi(response.body());
                        }
                        else{
                            Toast.makeText(MainActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onFailure(Call<New> call, Throwable t) {
                        Toast.makeText(MainActivity.this, "error!" , Toast.LENGTH_SHORT).show();
                    }
                });

            }
        }
        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {
            Log.v("MainActivity","On nothing selected.");
        }
    };

    public void createSpinner() {
        //Spinner list by adapter in order to choose categories
        spinner = (Spinner) findViewById(R.id.sort_by_spinner);
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this, R.layout.spinner_item, category);
        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_item);
        spinner.setAdapter(spinnerArrayAdapter);
    }

    private void updateUi(New news){
        //Updates ListView on main page

        LinearLayoutManager manager = new LinearLayoutManager(this);
        recycler.setLayoutManager(manager);
        adapter = new NewsAdapter(this, news);
        recycler.setAdapter(adapter);
        }
}
