package com.khieuthichien.wallpaper.ui;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.khieuthichien.wallpaper.R;
import com.khieuthichien.wallpaper.adapter.PostInCategoryAdapter;
import com.khieuthichien.wallpaper.modelPostInCategory.PostInCategory;
import com.khieuthichien.wallpaper.onClick.OnCategoryClickListener;
import com.khieuthichien.wallpaper.retrofit.PolyRetrofit;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostInCategoryActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private SwipeRefreshLayout f5;
    private ProgressBar progressBar;

    private RecyclerView rvPostInCategory;
    List<PostInCategory> postInCategoryList;
    PostInCategoryAdapter postInCategoryAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_in_category);
        //Log.e("idddd", getIntent().getStringExtra("id"));

        f5 = findViewById(R.id.f5);
        rvPostInCategory = findViewById(R.id.rvPostInCategory);
        progressBar = findViewById(R.id.progressBar);

        toolbar = findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        //toolbar.setTitle("Post In Category");
        //getSupportActionBar().setDisplayShowHomeEnabled(true);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        postInCategoryList = new ArrayList<>();
        postInCategoryAdapter = new PostInCategoryAdapter(postInCategoryList, getApplicationContext(), new OnCategoryClickListener() {
            @Override
            public void onClick(int id) {
                Intent intent = new Intent(PostInCategoryActivity.this , MediaOfPostActivity.class);
                intent.putExtra("id", postInCategoryList.get(id).getId() + "");
                startActivity(intent);
            }
        });

        rvPostInCategory.setAdapter(postInCategoryAdapter);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        rvPostInCategory.setLayoutManager(manager);

        getData();

    }

    private void getData() {
        PolyRetrofit.getInstanse().getPostInCategory(getIntent().getStringExtra("id")).enqueue(new Callback<List<PostInCategory>>() {
            @Override
            public void onResponse(Call<List<PostInCategory>> call, Response<List<PostInCategory>> response) {
                progressBar.setVisibility(View.GONE);
                if (response.code() == 200) {
                    Log.d("TAG", "onResponse: " + response.body().size());
                    postInCategoryList = response.body();
                    postInCategoryAdapter.changeDataset(postInCategoryList);
                }
            }

            @Override
            public void onFailure(Call<List<PostInCategory>> call, Throwable t) {
                Log.e("erorr", t.getMessage());
            }
        });
    }


    public void goBack(View view) {
        finish();
    }
}
