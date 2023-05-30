package com.example.mealapp.countryListMeal;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mealapp.R;
import com.example.mealapp.model.CategoriesM;
import com.example.mealapp.model.Meal;

import java.util.List;

public class CounteryinfoAdapter extends RecyclerView.Adapter<CounteryinfoAdapter.ViewHolder> {

   static List<Meal> meals ;
    Context context;
    private CounteryinfoAdapter.OnClickListener listener;
    int mode;

    public CounteryinfoAdapter( Context context,List<Meal> meals, OnClickListener listener, int mode ) {
        this.meals = meals;
        this.context = context;
        this.listener = listener;
        this.mode = mode;
    }
    public void setList(List<Meal> meals){

        this.meals = meals;

        this.notifyDataSetChanged();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        ImageView photo ;
        TextView id ;
        ImageView fav;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name =itemView.findViewById(R.id.meal_name);
            id = itemView.findViewById(R.id.meal_id);
            photo =itemView.findViewById(R.id.meal_thumbnail);
            fav = itemView.findViewById(R.id.favbutton);


        }
    }


    @NonNull
    @Override
    public CounteryinfoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.meal_item, parent, false);
        ViewHolder vh = new CounteryinfoAdapter.ViewHolder(v);

        return vh;
    }
    public interface OnClickListener {
        void onClick(Meal meal);
    }

    @Override
    public void onBindViewHolder(@NonNull CounteryinfoAdapter.ViewHolder holder, int position) {
        if (meals!= null){
        holder.id.setText(meals.get(position).getIdMeal());
        holder.name.setText(meals.get(position).getStrMeal());
        Glide.with(context)
                .load(meals.get(position).getStrMealThumb())
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_foreground)
                .into(holder.photo);}

    }

    @Override
    public int getItemCount() {
        return meals.size();
    }


}
