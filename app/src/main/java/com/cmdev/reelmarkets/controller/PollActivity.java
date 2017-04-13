package com.cmdev.reelmarkets.controller;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import com.cmdev.reelmarkets.R;

public class PollActivity extends AppCompatActivity {

    private ArrayList<String> answers = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poll);

        //TODO: the database shenanigans. Gonna fake things for now.

        TextView question = (TextView) findViewById(R.id.tv_question);
        question.setText("Who did this?");
        generateListContent();
        ListView answerList = (ListView) findViewById(R.id.lv_answer_choices);
        answerList.setAdapter(new MyListAdaper(this, R.layout.answer_list_item, answers));

    }

    private void generateListContent() {
        for(int i = 0; i < 8; i++) {
            answers.add("This is answer number " + i);
        }
    }

    private class MyListAdaper extends ArrayAdapter<String> {
        private int layout;
        private List<String> mObjects;
        private MyListAdaper(Context context, int resource, List<String> objects) {
            super(context, resource, objects);
            mObjects = objects;
            layout = resource;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            PollActivity.ViewHolder mainViewholder = null;
            if(convertView == null) {
                LayoutInflater inflater = LayoutInflater.from(getContext());
                convertView = inflater.inflate(layout, parent, false);
                PollActivity.ViewHolder viewHolder = new PollActivity.ViewHolder();
                viewHolder.button = (Button) convertView.findViewById(R.id.bt_answer_choice);
                convertView.setTag(viewHolder);
            }
            mainViewholder = (PollActivity.ViewHolder) convertView.getTag();

            //TODO: set it w the actual answer choice from mObjects
            mainViewholder.button.setText("please " + position);

            mainViewholder.button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(PollActivity.this,BettingPopupActivity.class));
                }
            });

            return convertView;
        }
    }

    public class ViewHolder {

        Button button;
    }
}
