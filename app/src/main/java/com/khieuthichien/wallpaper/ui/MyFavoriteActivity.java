package com.khieuthichien.wallpaper.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.GridView;
import android.widget.ProgressBar;

import com.khieuthichien.wallpaper.R;
import com.khieuthichien.wallpaper.adapter.LatestAdapter;
import com.khieuthichien.wallpaper.adapter.MyFavoriteAdapter;
import com.khieuthichien.wallpaper.model.Post;
import com.khieuthichien.wallpaper.retrofit.PolyRetrofit;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyFavoriteActivity extends AppCompatActivity {

    private GridView gridMyFavorite;
    private ProgressBar progress;

    List<Post> postList;
    MyFavoriteAdapter myFavoriteAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_favorite);

        gridMyFavorite = findViewById(R.id.gridMyFavorite);
        progress = findViewById(R.id.progress);

        postList = new ArrayList<>();
        myFavoriteAdapter = new MyFavoriteAdapter(this, postList);
        gridMyFavorite.setAdapter(myFavoriteAdapter);

        getData();

    }

    private void getData() {
        PolyRetrofit.getInstanse().getPostOfCategory(getIntent().getStringExtra("id"), "100", "1").enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                progress.setVisibility(View.GONE);
                postList = response.body();
                myFavoriteAdapter.changeDataset(postList);
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                Log.e("error", t.getMessage() + "");
            }
        });
    }

}
