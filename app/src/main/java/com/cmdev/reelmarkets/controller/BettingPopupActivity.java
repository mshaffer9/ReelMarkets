package com.cmdev.reelmarkets.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.cmdev.reelmarkets.R;

public class BettingPopupActivity extends AppCompatActivity {

    int progress = 50;
    int value;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_betting_popup);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width * .8),(int) (height * .3));

        final TextView text = (TextView) findViewById(R.id.tv_popup_text);
        SeekBar seekbar = (SeekBar) findViewById(R.id.seekBar);

        text.setText("You bet: ");

        //TODO: Set this current user's coin num - the current poll's min bet
        seekbar.setMax(2500);

        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            //TODO: Set this to current poll's min bet
            int MIN = 50;

            @Override
            public void onStartTrackingTouch(SeekBar seekBar){}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}

            /*This is the hacky way of setting the minimum for a seekBar.
            With time, a custom seekbar class could be made.*/
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                value = progress + MIN;
                text.setText("You bet: " + value);
            }
        });

        Button cancelButton = (Button) findViewById(R.id.bt_cancel_button);
        Button betButton = (Button) findViewById(R.id.bt_bet_Button);

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        betButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //TODO: Add bet to Poll's pool, subtract $ from user

                final Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                intent.putExtra("curr", value);
                Toast.makeText(getApplicationContext(),
                        "You bet " + value + " on the poll.", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                finish();
            }
        });
    }
}
