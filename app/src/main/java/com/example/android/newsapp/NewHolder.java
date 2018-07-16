package com.example.android.newsapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class NewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private final ImageView newsImage;
    private final TextView title;
    private final TextView section;
    private final TextView date;


    private New myNew;
    private Context context;

    public NewHolder(Context context, View itemView) {

        super(itemView);

        // 1. Set the context
        this.context = context;

        // 2. Set up the UI widgets of the holder
        this.newsImage = (ImageView) itemView.findViewById(R.id.image);
        this.title = (TextView) itemView.findViewById(R.id.title);
        this.section = (TextView) itemView.findViewById(R.id.section);
        this.date = (TextView) itemView.findViewById(R.id.date);

        // 3. Set the "onClick" listener of the holder
        itemView.setOnClickListener(this);
    }

    public void bindNews(New myNew) {

        // 4. Bind the data to the ViewHolder
        this.myNew = myNew;
        this.title.setText(myNew.getTitle());
        this.section.setText(myNew.getSection());

        this.date.setText(myNew.getDate().toString());
        Glide.with(context)
                .load(myNew.getThumbnail())
                .apply(new RequestOptions().override(150, 150).circleCrop())
                .into(newsImage);
    }

    @Override
    public void onClick(View v) {

        // 5. Handle the onClick event for the ViewHolder
        if (this.myNew != null) {
            Intent i = new Intent(context, NewsIntent.class);
            i.putExtra("bodyString", myNew.getBody());
            i.putExtra("titleOfBody",myNew.getTitle());
            i.putExtra("imageThumbnail",myNew.getThumbnail());
            i.putExtra("headline",myNew.getStandfirst());
            context.startActivity(i);

            Toast.makeText(this.context, "Clicked on " + this.myNew.getTitle(), Toast.LENGTH_SHORT ).show();
        }
    }
}