package com.example.android.newsapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.support.annotation.NonNull;
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
import java.util.ArrayList;
import java.util.Date;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.module.AppGlideModule;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;


public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.MyViewHolder>  {

    Context context;
    List<New> news;

    public NewsAdapter(Context context,ArrayList<New> news) {
        super();
        this.context = context;
        this.news = news;
    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        //New currentNew =getItem(position);


        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.new_list, viewGroup, false);
        final MyViewHolder myViewHolder = new MyViewHolder(view);


        return myViewHolder;
    }
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, final int position) {
        final New currentNew = news.get(position); //neyse ben düzeltirirm


        myViewHolder.titleView.setText(currentNew.getResponse().getResult().get(position).getWebTitle());
        myViewHolder.secView.setText(currentNew.getResponse().getResult().get(position).getSectionName());
        String date_of = currentNew.getResponse().getResult().get(position).getWebPublicationDate();

        //Changes date format.
        String dates = date_of.substring(0, 10);
        dates = dates.substring(0, 4) + '/' + dates.substring(5);
        dates = dates.substring(0, 7) + '/' + dates.substring(8);

        myViewHolder.dateView.setText(dates);

        // Sets image using Glide.
        Glide.with(context)
                .load(currentNew.getResponse().getResult().get(position).field.getThumbnail())
                .apply(new RequestOptions().override(150, 150).circleCrop())
                .into(myViewHolder.iconImg);


        myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // When a new view is clicked ->  go to the new

                Intent i = new Intent(context, NewsIntent.class);
                i.putExtra("bodyString", currentNew.getResponse().getResult().get(position).field.getBody());
                i.putExtra("titleOfBody", currentNew.getResponse().getResult().get(position).getWebTitle());
                i.putExtra("imageThumbnail", currentNew.getResponse().getResult().get(position).field.getThumbnail());
                i.putExtra("headline", currentNew.getResponse().getResult().get(position).field.getStandfirst());
                context.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return 0;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView titleView;
        TextView secView;
        TextView dateView;
        ImageView iconImg;
        public MyViewHolder(@NonNull View listItemView) {
            super(listItemView);
             titleView = (TextView) listItemView.findViewById(R.id.title);
             secView   = (TextView) listItemView.findViewById(R.id.section);
             iconImg   = (ImageView) listItemView.findViewById(R.id.image);
             dateView  = (TextView) listItemView.findViewById(R.id.date);

        }

    }
}

