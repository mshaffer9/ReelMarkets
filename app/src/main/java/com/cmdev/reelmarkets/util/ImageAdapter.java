package com.cmdev.reelmarkets.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.cmdev.reelmarkets.R;
import com.squareup.picasso.Picasso;

/**
 * Created by Vagdevi on 4/1/2017.
 */

public class ImageAdapter extends BaseAdapter {

    private String[] IMAGE_URLS;
    private String[] titles;

    private LayoutInflater inflater;

    Context c;

    public ImageAdapter(Context context, String[] URLS, String[] show_titles) {
        IMAGE_URLS = URLS;
        inflater = LayoutInflater.from(context);
        c = context;
        titles = show_titles;
    }

    @Override
    public int getCount() {
        return IMAGE_URLS.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;
        if (view == null) {
            view = inflater.inflate(R.layout.genre_item_grid, parent, false);

            assert view != null;
        }
        ImageView imageView = (ImageView) view.findViewById(R.id.image);
        TextView title = (TextView) view.findViewById(R.id.title);
        Picasso.with(c)
                .load(IMAGE_URLS[position])
                .fit()
                .into(imageView);
        title.setText(titles[position]);
        return view;
    }
}