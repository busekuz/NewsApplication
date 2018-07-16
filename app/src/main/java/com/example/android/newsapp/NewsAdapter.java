package com.example.android.newsapp;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
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

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.module.AppGlideModule;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;


public class NewsAdapter extends ArrayAdapter<New> {

    Context context;

    public NewsAdapter(Context context, List<New> news) {
        super(context, 0, news);
        this.context = context;
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if there is an existing list item view (called convertView) that we can reuse,
        // otherwise, if convertView is null, then inflate a new list item layout.
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.new_list, parent, false);
        }

        final New currentNew = getItem(position);


        TextView titleView = (TextView) listItemView.findViewById(R.id.title);
        titleView.setText(" "+currentNew.getTitle().toString());

        TextView secView = (TextView) listItemView.findViewById(R.id.section);
        secView.setText(currentNew.getSection().toString());
        // new since Glide v4
        ImageView iconImg = (ImageView) listItemView.findViewById(R.id.image);


        String date_of = currentNew.getDate();

        String dates = date_of.substring(0, 10);
        dates = dates.substring(0,4)+'/'+dates.substring(5);
        dates = dates.substring(0,7)+'/'+dates.substring(8);

        TextView dateView = (TextView) listItemView.findViewById(R.id.date);
        dateView.setText(dates);


        Glide.with(context)
                .load(currentNew.getThumbnail())
                .apply(new RequestOptions().override(150, 150).circleCrop())
                .into(iconImg);



        listItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(context, NewsIntent.class);
                i.putExtra("bodyString", currentNew.getBody());
                i.putExtra("titleOfBody",currentNew.getTitle());
                i.putExtra("imageThumbnail",currentNew.getThumbnail());
                i.putExtra("headline",currentNew.getStandfirst());
                context.startActivity(i);
            }
        });



        return listItemView;
    }



}