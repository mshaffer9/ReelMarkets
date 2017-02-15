package com.cmdev.reelmarkets.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.cmdev.reelmarkets.R;
import com.cmdev.reelmarkets.model.LoginSession;
import com.cmdev.reelmarkets.model.Poll;
import com.cmdev.reelmarkets.model.PollManager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class PollCreationActivity extends AppCompatActivity {

    String[] pollType = {"SERIES", "SEASON", "EPISODE"};
    List<String> options;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poll_creation);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_dropdown_item_1line, pollType);
        Spinner spinner = (Spinner) findViewById(R.id.type);
        spinner.setAdapter(arrayAdapter);
        options = new ArrayList<>();
    }

    public void onClickCreatePoll(View v) {

        String pollAuthor = LoginSession.getCurrentUser().getUsername();

        String pollName = ((EditText) findViewById(R.id.etPollName)).getText().toString();
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        Date startDate = null;
        Date endDate = null;
        //TODO: how to handle time (hours/mins)?
        try {
            startDate = (new SimpleDateFormat("MM/dd/yyyy")).parse(((EditText)findViewById(R.id.etStartDate)).getText().toString());
            c1.setTime(startDate);
            endDate = (new SimpleDateFormat("MM/dd/yyyy")).parse(((EditText)findViewById(R.id.etEndDate)).getText().toString());
            c2.setTime(endDate);
        } catch (ParseException e) {
            Toast.makeText(getApplicationContext(),
                    "Invalid start or end date!", Toast.LENGTH_LONG).show();
        } catch (NullPointerException e) {
            Toast.makeText(getApplicationContext(),
                    "Invalid start or end date!", Toast.LENGTH_LONG).show();
        }

        Poll.PollType p = Poll.PollType.SEASON;
        int pos = ((Spinner) findViewById(R.id.type)).getSelectedItemPosition();
        if (pos == 0) {
            p = Poll.PollType.SEASON;
        } else if (pos == 1) {
            p = Poll.PollType.SERIES;
        } else if (pos == 2) {
            p = Poll.PollType.EPISODE;
        }

        String option1 = ((EditText)findViewById(R.id.etOption1)).getText().toString();
        String option2 = ((EditText)findViewById(R.id.etOption2)).getText().toString();
        String option3 = ((EditText)findViewById(R.id.etOption3)).getText().toString();
        String option4 = ((EditText)findViewById(R.id.etOption4)).getText().toString();
        String option5 = ((EditText)findViewById(R.id.etOption5)).getText().toString();
        String option6 = ((EditText)findViewById(R.id.etOption6)).getText().toString();
        String option7 = ((EditText)findViewById(R.id.etOption7)).getText().toString();
        String option8 = ((EditText)findViewById(R.id.etOption8)).getText().toString();

        boolean isValid = !pollAuthor.equals("") && !pollName.equals("");
        boolean hasValidDates = c1.before(c2);
        boolean hasOptions = !option1.equals("") && !option2.equals("");


        if (!isValid) {
            Toast.makeText(getApplicationContext(),
                    "Please complete all fields!", Toast.LENGTH_LONG).show();
        }  else if (!hasValidDates) {
            Toast.makeText(getApplicationContext(),
                    "Start date must be before end date!", Toast.LENGTH_LONG).show();
        } else if (!hasOptions) {
            Toast.makeText(getApplicationContext(),
                    "Polls must have at least 2 options!", Toast.LENGTH_LONG).show();
        } else {

            //check if new options contain text
            if (!option3.equals(""))
                options.add(option3);
            if (!option4.equals(""))
                options.add(option4);
            if (!option5.equals(""))
                options.add(option5);
            if (!option6.equals(""))
                options.add(option6);
            if (!option7.equals(""))
                options.add(option7);
            if (!option8.equals(""))
                options.add(option8);

            Poll currentPoll = new Poll(pollName, pollName.hashCode(), pollAuthor,startDate.toString(),endDate.toString(),p, options);
            PollManager.addNewPoll(currentPoll);
            startActivity(new Intent(getApplicationContext(), PollActivity.class));
            finish();
            Toast.makeText(getApplicationContext(),
                    "Poll added successfully!", Toast.LENGTH_LONG).show();
        }
    }

    public void onClickAddOptions(View v) {

        if (findViewById(R.id.layout3).getVisibility() == View.GONE) {
            findViewById(R.id.layout3).setVisibility(View.VISIBLE);
        } else if (findViewById(R.id.layout4).getVisibility() == View.GONE) {
            findViewById(R.id.layout4).setVisibility(View.VISIBLE);
        } else if (findViewById(R.id.layout5).getVisibility() == View.GONE) {
            findViewById(R.id.layout5).setVisibility(View.VISIBLE);
        } else if (findViewById(R.id.layout6).getVisibility() == View.GONE) {
            findViewById(R.id.layout6).setVisibility(View.VISIBLE);
        } else if (findViewById(R.id.layout7).getVisibility() == View.GONE) {
            findViewById(R.id.layout7).setVisibility(View.VISIBLE);
        } else if (findViewById(R.id.layout8).getVisibility() == View.GONE) {
            findViewById(R.id.layout8).setVisibility(View.VISIBLE);
        } else {
            Toast.makeText(getApplicationContext(),
                    "You've reached the max of 8 options!", Toast.LENGTH_LONG).show();
        }
    }
}
