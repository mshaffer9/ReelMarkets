package com.cmdev.reelmarkets.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.cmdev.reelmarkets.R;
import com.cmdev.reelmarkets.util.ReelMarketsRestClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class SearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

    }

    public void onClickSearchShow(View v) {
        final Intent intent = new Intent(getApplicationContext(), TVShowPollsActivity.class);

        EditText searchShow = (EditText) findViewById(R.id.searchShow);
        final String showSearched = searchShow.getText().toString();
        Log.d("SearchActivity", "Trying to find show: " + showSearched);
        String path = "searchShow/";
        RequestParams params = new RequestParams();
        params.put("showName", showSearched);
        ReelMarketsRestClient.get(path, params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray timeline) {
                try {
                    System.out.println(timeline);
                    String showId = ((JSONObject) timeline.get(0)).getString("Show_ID");
                    intent.putExtra("id", showId);
                    intent.putExtra("show", showSearched);
                    startActivity(intent);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                System.out.println("Status Code = " + statusCode);
                System.out.println("responseString = " + responseString);
                if (statusCode == 200) {
                    if (responseString.equals("No Polls")) {
                        intent.putExtra("id", "0");
                        intent.putExtra("show", showSearched);
                        startActivity(intent);
                    } else if (responseString.equals("No Show")) {
                        Toast.makeText(getApplicationContext(),
                                "The show you inputted does not exist in our catalog!", Toast.LENGTH_LONG).show();
                    }
                    //TODO:

                } else {
                    Toast.makeText(getApplicationContext(),
                            "Could not connect to database!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

}
