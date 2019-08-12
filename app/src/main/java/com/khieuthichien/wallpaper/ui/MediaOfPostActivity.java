package com.khieuthichien.wallpaper.ui;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.khieuthichien.wallpaper.R;
import com.khieuthichien.wallpaper.adapter.MediaOfPostAdapter;
import com.khieuthichien.wallpaper.modelMediaOfPost.MediaOfPost;
import com.khieuthichien.wallpaper.onClick.OnCategoryClickListener;
import com.khieuthichien.wallpaper.retrofit.PolyRetrofit;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MediaOfPostActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private SwipeRefreshLayout f5;
    private ProgressBar progressBar;

    private RecyclerView rvMediaOfPost;
    List<MediaOfPost> mediaOfPostList;
    MediaOfPostAdapter mediaOfPostAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_of_post);

        toolbar = findViewById(R.id.toolbar);
        f5 = findViewById(R.id.f5);
        rvMediaOfPost = findViewById(R.id.rvMediaOfPost);
        progressBar = findViewById(R.id.progressBar);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(),2);
        rvMediaOfPost.setLayoutManager(gridLayoutManager);

        //int numberOfColumns = 2;
        //rvMediaOfPost.setLayoutManager(new GridLayoutManager(this, numberOfColumns));

        mediaOfPostList = new ArrayList<>();
        mediaOfPostAdapter = new MediaOfPostAdapter(this, mediaOfPostList, new OnCategoryClickListener() {
            @Override
            public void onClick(int id) {
                Intent intent = new Intent(MediaOfPostActivity.this , ImageDetailActivity.class);
                intent.putExtra("urlImage", mediaOfPostList.get(id).getGuid().getRendered());
                startActivity(intent);
            }
        });

        rvMediaOfPost.setAdapter(mediaOfPostAdapter);
//        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
//        rvMediaOfPost.setLayoutManager(manager);

        getData();


    }


    private void getData() {
        PolyRetrofit.getInstanse().getMediaOfPost(getIntent().getStringExtra("id")).enqueue(new Callback<List<MediaOfPost>>() {
            @Override
            public void onResponse(Call<List<MediaOfPost>> call, Response<List<MediaOfPost>> response) {
                progressBar.setVisibility(View.GONE);
                if (response.code() == 200) {
                    Log.d("TAG", "onResponse: " + response.body().size());
                    mediaOfPostList = response.body();
                    mediaOfPostAdapter.changeDataset(mediaOfPostList);
                }
            }

            @Override
            public void onFailure(Call<List<MediaOfPost>> call, Throwable t) {
                Log.e("erorr", t.getMessage());
            }
        });
    }


    public void goBack(View view) {
        finish();
    }
}
