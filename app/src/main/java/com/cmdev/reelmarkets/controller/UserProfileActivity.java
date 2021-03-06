package com.cmdev.reelmarkets.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

import com.cmdev.reelmarkets.R;
import com.cmdev.reelmarkets.model.LoginSession;
import com.cmdev.reelmarkets.model.ProfileExpListAdapter;
import com.cmdev.reelmarkets.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserProfileActivity extends AppCompatActivity {

    ExpandableListView profileListView;

    List<String> profileHeaders;
    Map<String, List<String>> listContents;
    ExpandableListAdapter profileListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        Button userListButton = (Button) findViewById(R.id.bt_view_user_list);

        if (LoginSession.currentUser.accountType.equals(User.AccountType.ADMIN)) {
            userListButton.setVisibility(View.VISIBLE);
        }

//        TextView viewHelloProfile = (TextView) findViewById(R.id.tvHelloProfile);
//        viewHelloProfile.setText("Hi " + LoginSession.currentUser.getUsername() + "!");
//
//        TextView viewEmail = (TextView) findViewById(R.id.tvEmailProfile);
//        viewEmail.setText(LoginSession.currentUser.getEmail());
//
//        TextView viewDob = (TextView) findViewById(R.id.tvDobProfile);
//        viewDob.setText(LoginSession.currentUser.getDoB());
//
//        TextView viewGender = (TextView) findViewById(R.id.tvGenderProfile);
//        viewGender.setText(LoginSession.currentUser.getGender().toString());

        profileListView = (ExpandableListView) findViewById(R.id.expList);
        fillData();

        profileListAdapter = new ProfileExpListAdapter(this, profileHeaders, listContents);
        profileListView.setAdapter(profileListAdapter);

    }

    public void fillData() {
        profileHeaders = new ArrayList<>();
        listContents = new HashMap<>();

        profileHeaders.add("Notifications");
        profileHeaders.add("Your Bets");
        profileHeaders.add("Your Poll");
        profileHeaders.add("Your Titles");
        profileHeaders.add("Account Information");

        List<String> notifications = new ArrayList<>();
        List<String> yourBets = new ArrayList<>();
        List<String> yourPolls = new ArrayList<>();
        List<String> yourTitles = new ArrayList<>();
        List<String> accountInfo = new ArrayList<>();

        notifications.add("User John bet on your poll.");

        yourBets.add("500 on Dexter");
        yourPolls.add("Silicon Valley: Will the box be successful?");
        yourTitles.add("1st in The Americans");
        accountInfo.add("Email: " + LoginSession.currentUser.getEmail());
        accountInfo.add("Date of Birth: " + LoginSession.currentUser.getDoB());
        accountInfo.add("Gender: " + LoginSession.currentUser.getGender().toString());

        listContents.put(profileHeaders.get(0), notifications);
        listContents.put(profileHeaders.get(1), yourBets);
        listContents.put(profileHeaders.get(2), yourPolls);
        listContents.put(profileHeaders.get(3), yourTitles);
        listContents.put(profileHeaders.get(4), accountInfo);

    }

    public void onClickSwitchToPollCreation(View v) {
        startActivity(new Intent(getApplicationContext(), PollCreationActivity.class));
    }

    public void onClickSwitchToHome(View v) {
        startActivity(new Intent(getApplicationContext(), HomeActivity.class));
    }

    public void onClickSwitchToSearch(View v) {
        startActivity(new Intent(getApplicationContext(), SearchActivity.class));
    }

    public void onClickSwitchToUserList(View v) {
        startActivity(new Intent(getApplicationContext(), UserListActivity.class));
    }

}
