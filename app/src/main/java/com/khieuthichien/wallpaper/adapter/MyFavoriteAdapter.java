package com.khieuthichien.wallpaper.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.khieuthichien.wallpaper.R;
import com.khieuthichien.wallpaper.model.Post;

import java.util.List;

public class MyFavoriteAdapter extends BaseAdapter {

    private Context mContext;
    private List<Post> postList;

    public MyFavoriteAdapter(Context mContext, List<Post> postList) {
        this.mContext = mContext;
        this.postList = postList;
    }

    @Override
    public int getCount() {
        return postList.size();
    }

    public void changeDataset(List<Post> items) {
        this.postList = items;
        notifyDataSetChanged();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View gridMyFavorite;
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            gridMyFavorite = new View(mContext);
            gridMyFavorite = inflater.inflate(R.layout.item_latest, null);
            ImageView imgMyFavorite = gridMyFavorite.findViewById(R.id.imgMyFavorite);
            try {
                Glide
                        .with(mContext)
                        .load(postList.get(position).getContent().getRendered().substring(postList.get(position).getContent().getRendered().lastIndexOf("http"), postList.get(position).getContent().getRendered().lastIndexOf(".jpg") + 4))
                        .centerCrop()
                        .into(imgMyFavorite);
            } catch (Exception e) {

            }


        } else {
            gridMyFavorite = (View) convertView;
        }
        return gridMyFavorite;
    }

}
