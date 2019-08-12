package com.khieuthichien.wallpaper.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;
import android.widget.ProgressBar;

import com.khieuthichien.wallpaper.R;
import com.khieuthichien.wallpaper.adapter.LatestAdapter;
import com.khieuthichien.wallpaper.model.Post;
import com.khieuthichien.wallpaper.onClick.OnCategoryClickListener;
import com.khieuthichien.wallpaper.retrofit.PolyRetrofit;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Main2Activity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar toolbar;
    private SwipeRefreshLayout f5;
    private ProgressBar progressBar;

    private RecyclerView rvLatest;
    List<Post> postList;
    LatestAdapter latestAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Latest");

        f5 = findViewById(R.id.f5);
        rvLatest = findViewById(R.id.rvLatest);
        progressBar = findViewById(R.id.progressBar);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(),2);
        rvLatest.setLayoutManager(gridLayoutManager);

        //int numberOfColumns = 2;
        //rvMediaOfPost.setLayoutManager(new GridLayoutManager(this, numberOfColumns));

        postList = new ArrayList<>();
        latestAdapter = new LatestAdapter(this, postList, new OnCategoryClickListener() {
            @Override
            public void onClick(int id) {
                Intent intent = new Intent(Main2Activity.this , MediaOfPostActivity.class);
                intent.putExtra("id", postList.get(id).getId() + "");
                startActivity(intent);
            }
        });

        rvLatest.setAdapter(latestAdapter);

        getData();


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.itemLatest) {
            Intent intent1 = new Intent(Main2Activity.this, LatestActivity.class);
            startActivity(intent1);
            finish();
        } else if (id == R.id.itemCategory) {
            Intent intent2 = new Intent(Main2Activity.this, CategoryActivity.class);
            startActivity(intent2);
            finish();
        } else if (id == R.id.itemGIFs) {

        } else if (id == R.id.itemFavorites) {
            Intent intent4 = new Intent(Main2Activity.this, MyFavoriteActivity.class);
            startActivity(intent4);
            finish();
        } else if (id == R.id.itemRateApp) {

        } else if (id == R.id.itemMoreApp) {

        } else if (id == R.id.itemAboutUs) {
            Intent intent7 = new Intent(Main2Activity.this, AboutUsActivity.class);
            startActivity(intent7);
        } else if (id == R.id.itemSetting) {

        } else if (id == R.id.itemPrivacyPolice) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void getData() {
        PolyRetrofit.getInstanse().getPostOfCategory(getIntent().getStringExtra("id"), "100", "1").enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                progressBar.setVisibility(View.GONE);
                if (response.code() == 200) {
                    Log.d("TAG", "onResponse: " + response.body().size());
                    postList = response.body();
                    latestAdapter.changeDataset(postList);
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                Log.e("error", t.getMessage() + "");
            }
        });
    }

}
