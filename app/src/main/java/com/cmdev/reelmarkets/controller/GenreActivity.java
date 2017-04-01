package com.cmdev.reelmarkets.controller;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

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
            "http://thetvdb.com/banners/posters/80379-1.jpg",
            "http://thetvdb.com/banners/posters/153021-1.jpg",
    };

    public static final String[] TITLES = new String[] {
            "House of Cards (2013)",
            "Arrow",
            "The Big Bang Theory",
            "The Walking Dead"
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
                Toast.makeText(GenreActivity.this, "" + TITLES[position], Toast.LENGTH_SHORT).show();
            }
        });
    }

}
