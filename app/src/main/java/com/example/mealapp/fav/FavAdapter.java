package com.example.mealapp.fav;

import android.content.Context;
import android.text.util.Linkify;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mealapp.R;
import com.example.mealapp.countryListMeal.CounteryinfoAdapter;
import com.example.mealapp.db.MealPojo;
import com.example.mealapp.model.Meal;

import java.util.List;

public class FavAdapter extends RecyclerView.Adapter<FavAdapter.ViewHolder> {

    static List<MealPojo> meal ;
    Context context;
    private FavAdapter.OnClickListener listener;
    int mode;

    public FavAdapter(Context context, List<MealPojo> meals, FavAdapter.OnClickListener listener, int mode ) {
        this.meal = meals;
        this.context = context;
        this.listener = listener;
        this.mode = mode;
    }
    public void setList(List<MealPojo> meals){

        this.meal = meals;

        this.notifyDataSetChanged();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name ;
        TextView cata;
        TextView area ;
        TextView ingreident;
        TextView instruction , mealinstreaction, watchintoutube,link;
        ImageView photoMeal;
        Button delete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            delete = itemView.findViewById(R.id.deletefromfav);
            name =itemView.findViewById(R.id.meal_namedetfav);
            cata =itemView.findViewById(R.id.meal_categorydetfav);
            area=itemView.findViewById(R.id.meal_areadetfav);
            ingreident =itemView.findViewById(R.id.meal_ingredientsdetfav);
            instruction= itemView.findViewById(R.id.meal_instructions_titledetfav);
            mealinstreaction =itemView.findViewById(R.id.meal_instructionsdetfav);
            watchintoutube =itemView.findViewById(R.id.meal_youtube_titledetfav);
            link = itemView.findViewById(R.id.meal_youtube_linkdetfav);
            photoMeal=itemView.findViewById(R.id.photofav);


        }
    }


    @NonNull
    @Override
    public FavAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.customlayoutfav, parent, false);
       ViewHolder vh = new ViewHolder(v);

        return vh;
    }
    public interface OnClickListener {
        void onClick(MealPojo meal);
        void onClickFav(Meal meal);
        void  onClickCalanender(Meal meal);

    }

    @Override
    public void onBindViewHolder(@NonNull FavAdapter.ViewHolder holder, int position) {
        if (meal!= null){
            holder.delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClick(meal.get(position));
                }
            });


//            holder.link.setText(meal.get(0).getStrYoutube());

            holder.name.setText(meal.get(position).getStrMeal());
            holder.cata.setText(meal.get(0).getStrCategory());
            holder.area.setText(meal.get(0).getStrArea());
            holder.ingreident.setText(meal.get(0).getStrIngredient1()+" "+meal.get(0).getStrIngredient2()+" "+meal.get(0).getStrIngredient3()+" "+meal.get(0).getStrIngredient4()+" "+meal.get(0).getStrIngredient4()+" "+meal.get(0).getStrIngredient6()
                    +" "+meal.get(0).getStrIngredient7()
//                            +" "+meal.get(0).getStrIngredient8()+" "+meal.get(0).getStrIngredient9()+" "+meal.get(0).getStrIngredient10()+" "+meal.get(0).getStrIngredient11()
//                    +" "+meal.get(0).getStrIngredient12()+" "+meal.get(0).getStrIngredient13()+" "+meal.get(0).getStrIngredient14()+" "+meal.get(0).getStrIngredient15()
//                    +" "+meal.get(0).getStrIngredient16()+" "+meal.get(0).getStrIngredient17()+" "+meal.get(0).getStrIngredient18()+" "+meal.get(0).getStrIngredient19()
//                    +meal.get(0).getStrIngredient20()
                   );
            holder.instruction.setText(meal.get(0).getStrInstructions());



            Glide.with(context)
                    .load(meal.get(position).getStrMealThumb())
                    .placeholder(R.drawable.ic_launcher_background)
                    .error(R.drawable.ic_launcher_foreground)
                    .into(holder.photoMeal);}

    }

    @Override
    public int getItemCount() {
        return meal.size();
    }


}
