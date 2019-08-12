package com.khieuthichien.wallpaper.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.khieuthichien.wallpaper.onClick.OnCategoryClickListener;
import com.khieuthichien.wallpaper.R;
import com.khieuthichien.wallpaper.modelCategory.Category;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder>{

    private Context context;
    private List<Category> categoryList;
    private OnCategoryClickListener listener;

    public CategoryAdapter(Context context, List<Category> categoryList, OnCategoryClickListener listener) {
        this.context = context;
        this.categoryList = categoryList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.item_category, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.ViewHolder holder, final int position) {
        final Category category = categoryList.get(position);
        //holder.imgCategory.setImageResource(R.drawable.icon_cate);
        holder.tvCategory.setText(category.getName());
        holder.tvNumberCategory.setText( "("+ category.getCount()+ "" + ")");

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(context, PostInCategoryActivity.class);
//                context.startActivity(intent);
                if (listener != null){
                    listener.onClick(position);
                }
            }
        });
    }

    public void changeDataset(List<Category> items) {
        this.categoryList = items;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (categoryList == null) return 0;
        return categoryList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        final ImageView imgCategory;
        final TextView tvCategory;
        final TextView tvNumberCategory;

        public ViewHolder(View itemView) {
            super(itemView);

            imgCategory = itemView.findViewById(R.id.imgCategory);
            tvCategory = itemView.findViewById(R.id.tvCategory);
            tvNumberCategory = itemView.findViewById(R.id.tvNumberCategory);

        }

    }
}
