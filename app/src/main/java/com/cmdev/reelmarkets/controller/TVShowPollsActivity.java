package com.cmdev.reelmarkets.controller;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.cmdev.reelmarkets.R;
import com.cmdev.reelmarkets.util.PollAdapter;

public class TVShowPollsActivity extends AppCompatActivity {
    public static final String[] POLL_NAMES = new String[] {
            "Will Clara die?",
            "Who's going to be the next villain?",
            "Where will Dr. Who visit next?",
            "When will we see Sandra next?",
            "Who will be Dr. Who's next travel partner?",
            "Will Dr. Who have a continuous storyline?",
            "Who do you think will play the next doctor?",
            "Do you think previous doctors will appear in the next season?",
            "When will will see Daleks next?"
    };

    public static final String[] NUMBERS = new String[] {
            "1500",
            "200",
            "100",
            "300",
            "250",
            "210",
            "550",
            "670",
            "330",
            "100",
            "250"

    };

    ListView list;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tvshow_polls);

        list = (ListView)findViewById(R.id.polls);
        list.setAdapter(new PollAdapter(this, POLL_NAMES, NUMBERS));
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(TVShowPollsActivity.this, "" + POLL_NAMES[position], Toast.LENGTH_SHORT).show();
            }
        });
    }

}
