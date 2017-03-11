package com.cmdev.reelmarkets.controller;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.view.View;
import android.widget.Toast;

import com.cmdev.reelmarkets.R;
import com.cmdev.reelmarkets.model.LoginSession;
import com.cmdev.reelmarkets.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Megan on 2/16/2017.
 */

public class UserListActivity extends AppCompatActivity{

        private ArrayList<String> data = new ArrayList<String>();

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_user_list_new);
            ListView lv = (ListView) findViewById(R.id.listviewUsernames);
            generateListContent();
            lv.setAdapter(new MyListAdaper(this, R.layout.user_list_item, data));
        }

        private void generateListContent() {
            for(int i = 0; i < 55; i++) {
                data.add("This is row number " + i);
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
                ViewHolder mainViewholder = null;
                if(convertView == null) {
                    LayoutInflater inflater = LayoutInflater.from(getContext());
                    convertView = inflater.inflate(layout, parent, false);
                    ViewHolder viewHolder = new ViewHolder();
                    viewHolder.username = (TextView) convertView.findViewById(R.id.tv_listitem_username);
                    viewHolder.button = (Button) convertView.findViewById(R.id.bt_listitem_button);
                    convertView.setTag(viewHolder);
                }
                mainViewholder = (ViewHolder) convertView.getTag();
                mainViewholder.button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getContext(), "Button was clicked for list item " + position, Toast.LENGTH_SHORT).show();
                    }
                });
                mainViewholder.username.setText(getItem(position));

                return convertView;
            }
        }

        public class ViewHolder {

            TextView username;
            Button button;
        }

/*    private String[] usernames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list_new);

        ListView lv = (ListView) findViewById(R.id.listviewUsernames);
        populateDaList();

        lv.setAdapter(new MyListAdapter(this,R.layout.user_list_item,usernames));
        registerClickCallback();

    }

    protected void populateDaList() {
        //this list of names should come from db, but we're using a dummy for now
        //TODO: get actual user list from db
        String[] usernames = {"user1", "lance", "bob", "kenya", "kartik", "sam", "alice", "christina", "james", "caleigh", "gwen", "brycex3","toaster", "kartik","kartik","kartik","kartik","kartik","kartik","kartik","kartik","kartik","kartik","kartik","kartik","kartik","kartik","kartik","kartik","kartik","kartik","kartik"};

        Build Adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.user_list_item,usernames);

        //Configure dat sweet sweet listview
        ListView list = (ListView) findViewById(R.id.listviewUsernames);
        list.setAdapter(adapter);*//*
    }

    protected void registerClickCallback() {
        ListView list = (ListView) findViewById(R.id.listviewUsernames);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View viewClicked, int position, long id) {
                TextView daView = (TextView) viewClicked;
                String username = daView.getText().toString();
                //TODO: use username to find the user, ban/unban them accordingly, and make a Toast saying which happened
            }
        });
    }

    private class MyListAdapter extends ArrayAdapter<String> {
        private int layout;
        private String[] myObjects;

        private MyListAdapter(Context context, int resource, String[] objects) {
            super(context,resource,objects);
            myObjects = objects;
            layout = resource;
        }
    }*/

}
