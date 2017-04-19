package com.cmdev.reelmarkets.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.cmdev.reelmarkets.R;

public class HomeActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //TextView homeUser = (TextView) findViewById(R.id.home_user);
        //homeUser.setText(LoginSession.getCurrentUser().getUsername());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        if (getIntent().getStringExtra("curr") != null && getIntent().getStringExtra("curr") != "") {
            String curr = getIntent().getStringExtra("curr");
            TextView genreName = (TextView) findViewById(R.id.tvNumCoins2);
            int val = Integer.parseInt(genreName.toString()) - Integer.parseInt(curr);
            genreName.setText("" + val);
        }
        final Intent intent = new Intent(getApplicationContext(), GenreActivity.class);
        //TODO: Change buttons to gridview
        Button actionB = (Button) findViewById(R.id.btAction); //setOnClickListeners
        actionB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("genre", "action");
                startActivity(intent);
            }
        });

        Button comedyB = (Button) findViewById(R.id.btComedy);
        comedyB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("genre", "comedy");
                startActivity(intent);
            }
        });

        Button crimeB = (Button) findViewById(R.id.btCrime);
        crimeB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("genre", "crime");
                startActivity(intent);
            }
        });

        Button dramaB = (Button) findViewById(R.id.btDrama);
        dramaB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("genre", "drama");
                startActivity(intent);
            }
        });

        Button familyB = (Button) findViewById(R.id.btFamily);
        familyB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("genre", "family");
                startActivity(intent);
            }
        });

        Button fantasyB = (Button) findViewById(R.id.btFantasy);
        fantasyB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("genre", "fantasy");
                startActivity(intent);
            }
        });

        Button horrorB = (Button) findViewById(R.id.btHorror);
        horrorB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("genre", "horror");
                startActivity(intent);
            }
        });

        Button realityB = (Button) findViewById(R.id.btReality);
        realityB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("genre", "reality");
                startActivity(intent);
            }
        });


    }

    public void onClickSwitchToProfile(View v) {
        startActivity(new Intent(getApplicationContext(), UserProfileActivity.class));
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

    public void onClickSwitchToPoll(View v) {
        Intent i = new Intent(getApplicationContext(), PollActivity.class);
        startActivity(i);
    }

}
