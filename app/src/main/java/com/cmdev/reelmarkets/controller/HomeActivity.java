package com.cmdev.reelmarkets.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.cmdev.reelmarkets.R;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void onClickSwitchToProfile(View v) {
        startActivity(new Intent(getApplicationContext(), UserProfileActivity.class));
    }
}
