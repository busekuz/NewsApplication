package com.example.android.newsapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import java.util.List;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.android.newsapp.Class.New;
import com.example.android.newsapp.Activity.NewsIntent;
import com.example.android.newsapp.Class.Results;
import com.example.android.newsapp.R;


public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.MyViewHolder>  {

    Context context;
    New news;

    public NewsAdapter(Context context,New news) {
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
        final Results currentNew = news.getResponse().getResults().get(position);


        myViewHolder.titleView.setText(currentNew.getWebTitle());
        myViewHolder.secView.setText(currentNew.getSectionName());
        String date_of = currentNew.getWebPublicationDate();

        //Changes date format.
        String dates = date_of.substring(0, 10);
        dates = dates.substring(0, 4) + '/' + dates.substring(5);
        dates = dates.substring(0, 7) + '/' + dates.substring(8);

        myViewHolder.dateView.setText(dates);

        // Sets image using Glide.
        Glide.with(context)
                .load(currentNew.getField().getThumbnail())
                .apply(new RequestOptions().override(150, 150).circleCrop())
                .into(myViewHolder.iconImg);


        myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // When a new view is clicked ->  go to the new
                Intent i = new Intent(context, NewsIntent.class);
                i.putExtra("bodyString", currentNew.getField().getBody());
                i.putExtra("titleOfBody", currentNew.getWebTitle());
                i.putExtra("imageThumbnail", currentNew.getField().getThumbnail());
                i.putExtra("headline", currentNew.getField().getStandfirst());
                context.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return news.getResponse().getResults().size();
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

