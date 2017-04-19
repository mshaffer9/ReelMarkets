package com.cmdev.reelmarkets.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.cmdev.reelmarkets.R;

/**
 * Created by Vagdevi on 4/2/2017.
 */

public class PollAdapter extends BaseAdapter {
    private String[] POLL_NAMES;
    private String[] NUMBERS;
    private String[] DATES;
    private LayoutInflater inflater;

    Context c;

    public PollAdapter(Context context, String[] URLS, String[] show_titles, String[] date) {
        POLL_NAMES = URLS;
        inflater = LayoutInflater.from(context);
        c = context;
        NUMBERS = show_titles;
        DATES = date;
    }

    @Override
    public int getCount() {
        return POLL_NAMES.length;
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
            view = inflater.inflate(R.layout.poll_list_item, parent, false);

            assert view != null;
        }

        TextView pollName = (TextView) view.findViewById(R.id.poll_name);
        TextView moneyBet = (TextView) view.findViewById(R.id.money_bet);
        TextView expiration = (TextView) view.findViewById(R.id.tvTimeLeft);
        pollName.setText(POLL_NAMES[position]);
        moneyBet.setText(NUMBERS[position]);
        expiration.setText(DATES[position]);
        return view;
    }
}
