package com.example.mealapp.countryListMeal;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mealapp.R;
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
       
        LinearLayout linearLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name =itemView.findViewById(R.id.meal_namese);
            id = itemView.findViewById(R.id.meal_id);
            photo =itemView.findViewById(R.id.meal_thumbnail);
            linearLayout =itemView.findViewById(R.id.meallayout);


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
        void onClickFav(Meal meal);
        void  onClickCalanender(Meal meal);

    }

    @Override
    public void onBindViewHolder(@NonNull CounteryinfoAdapter.ViewHolder holder, int position) {
        if (meals!= null){
            Meal meal = meals.get(position);

            holder.linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClick(meal);
                }
            });


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
