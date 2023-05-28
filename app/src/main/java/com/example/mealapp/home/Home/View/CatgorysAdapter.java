package com.example.mealapp.home.Home.View;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mealapp.R;
import com.example.mealapp.model.CategoriesM;


import java.util.List;

public class CatgorysAdapter extends RecyclerView.Adapter<CatgorysAdapter.ViewHolder> {

    Context context;
    List<CategoriesM.Category> categories;
    private OnClickListener listener;
    int mode;

    public CatgorysAdapter(Context context, List<CategoriesM.Category> categories, OnClickListener _listener , int mode) {
        this.context = context;
        this.categories = categories;
        this.listener = _listener;
        this.mode = mode;
    }

    public void setList(List<CategoriesM.Category> category){
        this.categories = category;
        this.notifyDataSetChanged();
    }

    public interface OnClickListener {
        void onClick(CategoriesM.Category category);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView description;


        private ImageView thumbnail;
        private View layout;
        private ConstraintLayout constraintLayout;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            layout = itemView;
            description = layout.findViewById(R.id.strcatgorydiscrption);
            thumbnail =layout.findViewById(R.id.imgcatagories);


        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.customlayout, parent, false);
        ViewHolder vh = new ViewHolder(v);

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.description.setText(categories.get(position).getStrCategoryDescription());
        Glide.with(context)
                .load(categories.get(position).getStrCategoryThumb())
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_foreground)
                .into(holder.thumbnail);


    }

    @Override
    public int getItemCount() {
        return categories.size();
    }


}
