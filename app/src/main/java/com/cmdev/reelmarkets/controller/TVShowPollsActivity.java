package com.cmdev.reelmarkets.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.cmdev.reelmarkets.R;
import com.cmdev.reelmarkets.util.PollAdapter;
import com.cmdev.reelmarkets.util.ReelMarketsRestClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

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
    public ArrayList<String> pollIDs = new ArrayList<>();
    public ArrayList<String> pollNames = new ArrayList<>();
    public ArrayList<String> bets = new ArrayList<>();
    public ArrayList<String> dates = new ArrayList<>();

    ListView list;

    protected void onCreate(Bundle savedInstanceState) {
        final Intent intent = new Intent(getApplicationContext(), PollActivity.class);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tvshow_polls);
        String show = getIntent().getStringExtra("show");
        String id = getIntent().getStringExtra("id");

        //title the page with the show name
        TextView showName = (TextView) findViewById(R.id.showTitle);
        showName.setText(show);

        Log.d("TVShowPollsActivity", "Trying to get polls for show: " + show);
        String path = "getPollByShowID/";
        RequestParams params = new RequestParams();
        params.put("ShowID", id);
        ReelMarketsRestClient.get(path, params, new JsonHttpResponseHandler() {
            public void onSuccess(int statusCode, Header[] headers, JSONArray timeline) {
                try {
                    System.out.println(timeline);
                    for (int i = 0; i < timeline.length(); i++) {
                        JSONObject obj = (JSONObject) timeline.get(i);
                        System.out.println(obj);

                        pollNames.add(obj.getString("Question_Name"));
                        bets.add(obj.getString("Minimum_Bet"));
                        dates.add(obj.getString("Expiry_Date").split("T")[0]);
                        pollIDs.add(obj.getString("PollID"));
                    }

                    String[] poll_names = pollNames.toArray(new String[0]);
                    String[] betsArray = bets.toArray(new String[0]);
                    String[] datesArray = dates.toArray(new String[0]);

                    list = (ListView)findViewById(R.id.polls);
                    list.setAdapter(new PollAdapter(getApplicationContext(), poll_names, betsArray, datesArray));
                    list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            intent.putExtra("poll_id", pollIDs.get(position));
                            startActivity(intent);
                        }
                    });

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                System.out.println("Status Code = " + statusCode);
                System.out.println("responseString = " + responseString);
                if (statusCode == 200) {
                    TextView descr = (TextView) findViewById(R.id.description);
                    descr.setVisibility(View.VISIBLE);
                    Button btn=(Button)findViewById(R.id.addPollBtn);
                    btn.setVisibility(View.VISIBLE);
                    list = (ListView)findViewById(R.id.polls);
                    list.setVisibility(View.GONE);
                    btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            startActivity(new Intent(getApplicationContext(), PollCreationActivity.class));
                        }
                    });
                } else {
                    Toast.makeText(getApplicationContext(),
                            "Could not connect to database!", Toast.LENGTH_LONG).show();
                }
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

}
