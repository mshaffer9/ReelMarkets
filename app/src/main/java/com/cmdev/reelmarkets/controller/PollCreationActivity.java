package com.cmdev.reelmarkets.controller;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
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

    Calendar sCalendar;
    private TextView sDateView, sTimeView;
    private Button sDate, sTime;

    Calendar eCalendar;
    private TextView eDateView, eTimeView;
    private Button eDate, eTime;

    static final int DATE_DIALOG_ID = 0;
    static final int TIME_DIALOG_ID = 1;

    private TextView activeDateDisplay;
    private Calendar activeDate;
    private TextView activeTimeDisplay;
    private Calendar activeTime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poll_creation);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_dropdown_item_1line, pollType);
        Spinner spinner = (Spinner) findViewById(R.id.type);
        spinner.setAdapter(arrayAdapter);
        options = new ArrayList<>();

        sDateView = (TextView) findViewById(R.id.tvStart);
        sTimeView = (TextView) findViewById(R.id.tvStartTime);
        sDate = (Button) findViewById(R.id.btSetStart);
        sDate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showDateDialog(sDateView, sCalendar);
            }
        });

        sTime = (Button) findViewById(R.id.btSetSTime);
        sTime.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showTimeDialog(sTimeView, sCalendar);
            }
        });

        sCalendar = Calendar.getInstance();

        eDateView = (TextView) findViewById(R.id.tvEnd);
        eTimeView = (TextView) findViewById(R.id.tvEndTime);
        eDate = (Button) findViewById(R.id.btSetEnd);
        eDate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showDateDialog(eDateView, eCalendar);
            }
        });

        eTime = (Button) findViewById(R.id.btSetETime);
        eTime.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showTimeDialog(eTimeView, eCalendar);
            }
        });
        eCalendar = Calendar.getInstance();
    }

    public void showDateDialog(TextView dateDisplay, Calendar date) {
        activeDateDisplay = dateDisplay;
        activeDate = date;
        showDialog(DATE_DIALOG_ID);
    }

    public void showTimeDialog(TextView timeDisplay, Calendar time) {
        activeTimeDisplay = timeDisplay;
        activeTime = time;
        showDialog(TIME_DIALOG_ID);
    }

    private void updateDisplay(TextView dateDisplay, Calendar date) {
        dateDisplay.setText(
                new StringBuilder()
                        // Month is 0 based so add 1
                        .append(date.get(Calendar.MONTH) + 1).append("-")
                        .append(date.get(Calendar.DAY_OF_MONTH)).append("-")
                        .append(date.get(Calendar.YEAR)).append(" "));

    }

    private void updateTimeDisplay(TextView timeDisplay, Calendar time) {
        timeDisplay.setText(
                new StringBuilder()
                        // Month is 0 based so add 1
                        .append(time.get(Calendar.HOUR)).append(":")
                        .append(time.get(Calendar.MINUTE)));

    }

    private DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            activeDate.set(Calendar.YEAR, year);
            activeDate.set(Calendar.MONTH, monthOfYear);
            activeDate.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateDisplay(activeDateDisplay, activeDate);
            unregisterDateDisplay();
        }
    };

    private TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hour, int min)
        {
            activeTime.set(Calendar.HOUR, hour);
            activeTime.set(Calendar.MINUTE, min);
            updateTimeDisplay(activeTimeDisplay, activeTime);
            unregisterTimeDisplay();
        }
    };

    private void unregisterDateDisplay() {
        activeDateDisplay = null;
        activeDate = null;
    }

    private void unregisterTimeDisplay() {
        activeTimeDisplay = null;
        activeTime = null;
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_DIALOG_ID:
                return new DatePickerDialog(this, dateSetListener,
                        activeDate.get(Calendar.YEAR),
                        activeDate.get(Calendar.MONTH),
                        activeDate.get(Calendar.DAY_OF_MONTH));
            case TIME_DIALOG_ID:
                return new TimePickerDialog(this, timeSetListener,
                        activeTime.get(Calendar.HOUR),
                        activeTime.get(Calendar.MINUTE),
                        true);
        }
        return null;
    }

    @Override
    protected void onPrepareDialog(int id, Dialog dialog) {
        super.onPrepareDialog(id, dialog);
        switch (id) {
            case DATE_DIALOG_ID:
                ((DatePickerDialog) dialog).updateDate(
                        activeDate.get(Calendar.YEAR),
                        activeDate.get(Calendar.MONTH),
                        activeDate.get(Calendar.DAY_OF_MONTH));
                break;
            case TIME_DIALOG_ID:
                ((TimePickerDialog) dialog).updateTime(
                        activeTime.get(Calendar.HOUR),
                        activeTime.get(Calendar.MINUTE));
                break;
        }
    }

    public void onClickCreatePoll(View v) {

        String pollAuthor = LoginSession.getCurrentUser().getUsername();

        String pollName = ((EditText) findViewById(R.id.etPollName)).getText().toString();
        Calendar c1 = sCalendar;
        Calendar c2 = eCalendar;

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

            Poll currentPoll = new Poll(pollName, pollName.hashCode(), pollAuthor,sCalendar,eCalendar, p, options);
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
