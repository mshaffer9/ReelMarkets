package com.cmdev.reelmarkets.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.cmdev.reelmarkets.R;
import com.cmdev.reelmarkets.util.ImageAdapter;

/**
 * Created by Megan on 3/2/2017.
 */

public class GenreActivity extends AppCompatActivity {
    public static final String[] IMAGES = new String[] {
// Heavy images
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
            "House of Cards (2013)",
            "Arrow",
            "The Walking Dead",
            "Daredevil",
            "Prison Break",
            "Supernatural",
            "24",
            "Doctor Who",
            "Band of Brothers",
            "Dexter"

    };

    GridView grid;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_genre);

        TextView genreName = (TextView) findViewById(R.id.genrename);
        genreName.setText(getIntent().getStringExtra("genre"));
        grid = (GridView)findViewById(R.id.grid);
        grid.setAdapter(new ImageAdapter(this, IMAGES, TITLES));
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(getApplicationContext(), TVShowPollsActivity.class));
            }
        });
    }

}
