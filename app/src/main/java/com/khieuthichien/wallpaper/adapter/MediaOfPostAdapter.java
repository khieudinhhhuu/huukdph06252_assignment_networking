package com.khieuthichien.wallpaper.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.khieuthichien.wallpaper.onClick.OnCategoryClickListener;
import com.khieuthichien.wallpaper.R;
import com.khieuthichien.wallpaper.modelMediaOfPost.MediaOfPost;

import java.util.List;

public class MediaOfPostAdapter extends RecyclerView.Adapter<MediaOfPostAdapter.MediaOfPostHolder>{

    Context context;
    List<MediaOfPost> mediaOfPostList;
    private OnCategoryClickListener listener;

    public MediaOfPostAdapter(Context context, List<MediaOfPost> mediaOfPostList, OnCategoryClickListener listener) {
        this.context = context;
        this.mediaOfPostList = mediaOfPostList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MediaOfPostAdapter.MediaOfPostHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_media_of_post, parent, false);
        return new MediaOfPostHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MediaOfPostAdapter.MediaOfPostHolder holder, final int position) {
        MediaOfPost mediaOfPost = mediaOfPostList.get(position);
        Glide.with(context)
                .load(mediaOfPost.getGuid().getRendered().substring(mediaOfPost.getGuid().getRendered().lastIndexOf("http"), mediaOfPost.getGuid().getRendered().lastIndexOf(".jpg") + 4))
                .placeholder(R.drawable.icon_cate)
                .centerCrop()
                .into(holder.imgMediaOfPost);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null){
                    listener.onClick(position);
                }
            }
        });
    }

    public void changeDataset(List<MediaOfPost> items) {
        this.mediaOfPostList = items;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mediaOfPostList.size();
    }

    public class MediaOfPostHolder extends RecyclerView.ViewHolder {

        private ImageView imgMediaOfPost;

        public MediaOfPostHolder(View itemView) {
            super(itemView);

            imgMediaOfPost = itemView.findViewById(R.id.imgMediaOfPost);

        }
    }
}
