package com.khieuthichien.wallpaper.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.khieuthichien.wallpaper.onClick.OnCategoryClickListener;
import com.khieuthichien.wallpaper.R;
import com.khieuthichien.wallpaper.modelPostInCategory.PostInCategory;

import java.util.List;

public class PostInCategoryAdapter extends RecyclerView.Adapter<PostInCategoryAdapter.PostInCategoryHolder>{

    List<PostInCategory> postInCategoryList;
    Context context;
    private OnCategoryClickListener listener;

    public PostInCategoryAdapter(List<PostInCategory> postInCategoryList, Context context, OnCategoryClickListener listener) {
        this.postInCategoryList = postInCategoryList;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public PostInCategoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_post_in_category, parent, false);
        return new PostInCategoryHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostInCategoryHolder holder, final int position) {
        final PostInCategory postInCategory = postInCategoryList.get(position);
        holder.tvNamePostInCategory.setText(postInCategory.getTitle().getRendered());
        holder.tvDatePostInCategory.setText(postInCategory.getDateGmt());
        Glide.with(context)
                .load(postInCategory.getContent().getRendered().substring(postInCategory.getContent().getRendered().lastIndexOf("http"), postInCategory.getContent().getRendered().lastIndexOf(".jpg") + 4))
                .placeholder(R.drawable.icon_cate)
                .centerCrop()
                .into(holder.imgPostInCategory);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null){
                    listener.onClick(position);
                }
            }
        });


    }

    public void changeDataset(List<PostInCategory> items) {
        this.postInCategoryList = items;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return postInCategoryList.size();
    }

    public class PostInCategoryHolder extends RecyclerView.ViewHolder {

        public TextView tvNamePostInCategory;
        public TextView tvDatePostInCategory;
        public ImageView imgPostInCategory;

        public PostInCategoryHolder(View itemView) {
            super(itemView);

            tvNamePostInCategory = itemView.findViewById(R.id.tvNamePostInCategory);
            tvDatePostInCategory = itemView.findViewById(R.id.tvDatePostInCategory);
            imgPostInCategory = itemView.findViewById(R.id.imgPostInCategory);

        }
    }
}
