package com.example.android.newsapp;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Date;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;



public class NewsAdapter extends RecyclerView.Adapter<NewHolder>{

    Context context;
    private int itemResource;
    private final List<New> news;

    public NewsAdapter(Context context,int itemResource, List<New> news) {

        this.itemResource = itemResource;
        this.context = context;
        this.news = news;
    }

    @Override
    public NewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        // 3. Inflate the view and return the new ViewHolder
        View view = LayoutInflater.from(parent.getContext())
                .inflate(this.itemResource, parent, false);
        return new NewHolder(this.context, view);
    }

    // 4. Override the onBindViewHolder method
    @Override
    public void onBindViewHolder(NewHolder holder, int position) {

        // 5. Use position to access the correct Bakery object
        New myNew = this.news.get(position);

        // 6. Bind the bakery object to the holder
        holder.bindNews(myNew);
    }

    @Override
    public int getItemCount() {

        return this.news.size();
    }





}