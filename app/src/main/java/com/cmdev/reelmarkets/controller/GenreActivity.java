package com.cmdev.reelmarkets.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.cmdev.reelmarkets.R;
import com.cmdev.reelmarkets.util.ImageAdapter;
import com.cmdev.reelmarkets.util.ReelMarketsRestClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

/**
 * Created by Megan & Vagdevi on 3/2/2017.
 */

public class GenreActivity extends AppCompatActivity {
    public static final String[] IMAGES = new String[] {
// Heavy images
            "http://thetvdb.com/banners/posters/287378-1.jpg",
            "http://thetvdb.com/banners/posters/79349-1.jpg",
            "http://thetvdb.com/banners/posters/262980-1.jpg",
            "http://thetvdb.com/banners/posters/257655-1.jpg",
            "http://thetvdb.com/banners/posters/153021-1.jpg",
            "http://thetvdb.com/banners/posters/281662-1.jpg",
            "http://thetvdb.com/banners/posters/75340-1.jpg",
            "http://thetvdb.com/banners/posters/76290-1.jpg",
            "http://thetvdb.com/banners/posters/76107-1.jpg",
            "http://thetvdb.com/banners/posters/79349-1.jpg"

    };

    public static final String[] TITLES = new String[] {
            "Band of Brothers",
            "Dexter",
            "House of Cards (2013)",
            "Arrow",
            "The Walking Dead",
            "Daredevil",
            "Prison Break",
           "Supernatural",
            "24",
            "Doctor Who"
    };

    public ArrayList<String> showNames = new ArrayList<>();
    public ArrayList<String> banners = new ArrayList<>();
    public ArrayList<String> showIDs = new ArrayList<>();
    boolean exists = true;

    GridView grid;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Intent intent = new Intent(getApplicationContext(), TVShowPollsActivity.class);

        setContentView(R.layout.activity_genre);
        String genre = getIntent().getStringExtra("genre");

        //title the page with the genre name
        TextView genreName = (TextView) findViewById(R.id.genrename);
        genreName.setText(genre);

        //get all shows related to genre
        Log.d("GenreActivity", "Trying to get ShowbyGenre: " + genre);
        String path = "getShowByGenre/";
        RequestParams params = new RequestParams();
        params.put("genre", genre);
        ReelMarketsRestClient.get(path, params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray timeline) {
                try {
                    System.out.println(timeline);
                    for (int i = 0; i < timeline.length(); i++) {
                        JSONObject obj = (JSONObject) timeline.get(i);
                        System.out.println(obj);
                        System.out.println(obj.getString("Show_Name"));
                        System.out.println(obj.getString("ShowID"));
                        showNames.add(obj.getString("Show_Name"));
                        banners.add("http://thetvdb.com/banners/" + obj.getString("Banner"));
                        showIDs.add(obj.getString("ShowID"));
                    }
                    String[] show_names = showNames.toArray(new String[0]);
                    String[] bannersArray = banners.toArray(new String[0]);

                    grid = (GridView)findViewById(R.id.grid);
                    grid.setAdapter(new ImageAdapter(getApplicationContext(), bannersArray, show_names));
                    grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            intent.putExtra("id", showIDs.get(position));
                            intent.putExtra("show", showNames.get(position));
                            Log.d("GenreActivity", "id clicked is: " + showIDs.get(position));
                            startActivity(intent);
                        }
                    });

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                exists = false;
                System.out.println("Status Code = " + statusCode);
                System.out.println("responseString = " + responseString);
                if (statusCode == 200) {
                    //TODO:
                    Toast.makeText(getApplicationContext(),
                            "Let me take you to a show page where there are no shows teehee", Toast.LENGTH_LONG).show();
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
