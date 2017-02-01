package com.cmdev.reelmarkets.controller;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.cmdev.reelmarkets.R;
import com.cmdev.reelmarkets.model.LoginSession;

public class UserProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        TextView viewHelloProfile = (TextView) findViewById(R.id.tvHelloProfile);
        viewHelloProfile.setText("Hi " + LoginSession.currentUser.getUsername() + "!");

        TextView viewEmail = (TextView) findViewById(R.id.tvEmailProfile);
        viewEmail.setText(LoginSession.currentUser.getEmail());

        TextView viewDob = (TextView) findViewById(R.id.tvDobProfile);
        viewDob.setText(LoginSession.currentUser.getDoB());

        TextView viewGender = (TextView) findViewById(R.id.tvGenderProfile);
        viewGender.setText(LoginSession.currentUser.getGender().toString());



    }
}
