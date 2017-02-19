package com.cmdev.reelmarkets.controller;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.view.View;
import android.widget.Toast;

import com.cmdev.reelmarkets.R;
import com.cmdev.reelmarkets.model.LoginSession;
import com.cmdev.reelmarkets.model.User;

/**
 * Created by Megan on 2/16/2017.
 */

public class UserListActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list_new);

        populateDaList();
        registerClickCallback();

    }

    protected void populateDaList() {
        //this list of names should come from db, but we're using a dummy for now
        //TODO: get actual user list from db
        String[] usernames = {"user1", "lance", "bob", "kenya", "kartik", "sam", "alice", "christina", "james", "caleigh", "gwen", "brycex3","toaster", "benwyattluvr42","bradazz97","sheenthequeen","garbolarbo","j4m13","qu33ni3"};

        //Build Adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.user_list_item,usernames);

        //Configure dat sweet sweet listview
        ListView list = (ListView) findViewById(R.id.listviewUsernames);
        list.setAdapter(adapter);
    }

    protected void registerClickCallback() {
        ListView list = (ListView) findViewById(R.id.listviewUsernames);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View viewClicked, int position, long id) {
                TextView daView = (TextView) viewClicked;
                String username = daView.getText().toString();
                //TODO: use username to find the user, ban/unban them accordingly, and make a Toast saying which happened
                if (username.equals("kartik")) {
                    Toast.makeText(getApplicationContext(),"kartik is now unbanned!",Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(),
                            username + " is now banned!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
