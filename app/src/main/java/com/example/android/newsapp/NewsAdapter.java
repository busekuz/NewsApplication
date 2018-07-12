package com.example.android.newsapp;
import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class NewsAdapter extends ArrayAdapter<New> {


    public NewsAdapter(Context context, List<New> news) {
        super(context, 0, news);
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

        New currentNew = getItem(position);


        TextView titleView = (TextView) listItemView.findViewById(R.id.title);
        String titleOfNew = currentNew.getTitle().toString();
        titleView.setText(titleOfNew);

        TextView secView = (TextView) listItemView.findViewById(R.id.section);
        String sectionOfNew = currentNew.getSection().toString();
        secView.setText(sectionOfNew);


        return listItemView;
    }

}