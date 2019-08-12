package com.khieuthichien.wallpaper.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.khieuthichien.wallpaper.R;
import com.khieuthichien.wallpaper.model.Post;
import com.khieuthichien.wallpaper.modelMediaOfPost.MediaOfPost;
import com.khieuthichien.wallpaper.onClick.OnCategoryClickListener;

import java.util.List;

public class LatestAdapter extends RecyclerView.Adapter<LatestAdapter.LatestHolder> {

    Context context;
    List<Post> postList;
    private OnCategoryClickListener listener;

    public LatestAdapter(Context context, List<Post> postList, OnCategoryClickListener listener) {
        this.context = context;
        this.postList = postList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public LatestAdapter.LatestHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_latest, parent, false);
        return new LatestHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull LatestAdapter.LatestHolder holder, final int position) {
        Post post = postList.get(position);

        int begin = post.getContent().getRendered().lastIndexOf("http");
        int end = post.getContent().getRendered().lastIndexOf(".jpg") + 4;

        if (begin != -1){
            holder.imgLatest.setVisibility(View.VISIBLE);
            String link = post.getContent().getRendered().substring(begin, end);
            Glide.with(context)
                    .load(link)
                    .placeholder(R.drawable.icon_cate)
                    .centerCrop()
                    .into(holder.imgLatest);
        }else {
            holder.imgLatest.setVisibility(View.GONE);
        }



        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null){
                    listener.onClick(position);
                }
            }
        });
    }

    public void changeDataset(List<Post> items) {
        this.postList = items;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    public class LatestHolder extends RecyclerView.ViewHolder {

        public ImageView imgLatest;
        public ImageView imgView;
        public TextView tvView;
        public ImageView imgFavorite;
        public TextView tvFavorite;

        public LatestHolder(View itemView) {
            super(itemView);

            imgLatest = itemView.findViewById(R.id.imgLatest);
            imgView = itemView.findViewById(R.id.imgView);
            tvView = itemView.findViewById(R.id.tvView);
            imgFavorite = itemView.findViewById(R.id.imgFavorite);
            tvFavorite = itemView.findViewById(R.id.tvFavorite);

        }
    }
}
