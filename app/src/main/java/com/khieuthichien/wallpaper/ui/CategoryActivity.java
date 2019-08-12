package com.khieuthichien.wallpaper.ui;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.khieuthichien.wallpaper.R;
import com.khieuthichien.wallpaper.adapter.CategoryAdapter;
import com.khieuthichien.wallpaper.modelCategory.Category;
import com.khieuthichien.wallpaper.onClick.OnCategoryClickListener;
import com.khieuthichien.wallpaper.retrofit.PolyRetrofit;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private List<Category> categoryList;
    private RecyclerView rvCategory;
    private CategoryAdapter categoryAdapter;

    private Toolbar toolbar;
    private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category222);

        progressBar = findViewById(R.id.progressBar);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Category");

        rvCategory = findViewById(R.id.rvCategory);
        categoryList = new ArrayList<>();

        categoryAdapter = new CategoryAdapter(CategoryActivity.this, categoryList, new OnCategoryClickListener() {
            @Override
            public void onClick(int id) {
                Intent intent = new Intent(CategoryActivity.this , PostInCategoryActivity.class);
                intent.putExtra("id", categoryList.get(id).getId() + "");
                startActivity(intent);
            }
        });
        rvCategory.setAdapter(categoryAdapter);

        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        rvCategory.setLayoutManager(manager);

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
            Intent intent1 = new Intent(getApplicationContext(), LatestActivity.class);
            startActivity(intent1);
            finish();
        } else if (id == R.id.itemCategory) {
            Intent intent2 = new Intent(getApplicationContext(), CategoryActivity.class);
            startActivity(intent2);
            finish();
        } else if (id == R.id.itemGIFs) {

        } else if (id == R.id.itemFavorites) {
            Intent intent4 = new Intent(getApplicationContext(), MyFavoriteActivity.class);
            startActivity(intent4);
            finish();
        } else if (id == R.id.itemRateApp) {

        } else if (id == R.id.itemMoreApp) {

        } else if (id == R.id.itemAboutUs) {
            Intent intent7 = new Intent(getApplicationContext(), AboutUsActivity.class);
            startActivity(intent7);
        } else if (id == R.id.itemSetting) {

        } else if (id == R.id.itemPrivacyPolice) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



    private void getData() {
        PolyRetrofit.getInstanse().getCategory("1", "10").enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                progressBar.setVisibility(View.GONE);
                if (response.code() == 200) {
                    Log.d("TAG", "onResponse: " + response.body().size());
                    categoryList = response.body();
                    categoryAdapter.changeDataset(categoryList);
                }
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                Log.e("erorr", t.getMessage());
            }
        });
    }




//    @Override
//    public void onClick(int pos) {
//        Intent intent = new Intent(CategoryActivity.this, ImageDetailActivity.class);
//        intent.putExtra("id",categoryList.get(pos).getId()+"");
//        startActivity(intent);
//    }


}
