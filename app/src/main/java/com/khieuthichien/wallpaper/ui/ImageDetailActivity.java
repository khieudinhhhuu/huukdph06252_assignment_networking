package com.khieuthichien.wallpaper.ui;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.github.clans.fab.FloatingActionMenu;
import com.khieuthichien.wallpaper.R;
import com.khieuthichien.wallpaper.ui.AboutUsActivity;

public class ImageDetailActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ImageView imgImageDetail;
    private FloatingActionMenu fab;
    private FloatingActionButton fbtnFavorites;
    private FloatingActionButton fbtnShare;
    private FloatingActionButton fbtnSaveImage;
    private FloatingActionButton fbtnSetAs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_detail);

        toolbar = findViewById(R.id.toolbar);
        imgImageDetail = findViewById(R.id.imgImageDetail);
        fab = findViewById(R.id.fab);
//        fbtnFavorites = findViewById(R.id.fbtnFavorites);
//        fbtnShare = findViewById(R.id.fbtnShare);
//        fbtnSaveImage = findViewById(R.id.fbtnSaveImage);
//        fbtnSetAs = findViewById(R.id.fbtnSetAs);

        String link = getIntent().getStringExtra("urlImage");

        Glide.with(this)
                .load(link)
                .placeholder(R.drawable.icon_cate)
                .into(imgImageDetail);

    }

    public void goBack(View view) {
        finish();
    }

}
