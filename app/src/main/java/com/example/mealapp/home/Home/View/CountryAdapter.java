package com.example.mealapp.home.Home.View;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mealapp.R;
import com.example.mealapp.model.CategoriesM;
import com.example.mealapp.model.Country;

import java.util.List;

public  class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.ViewHolder>{
    Context context;
    List<Country> countries;
    private CountryAdapter.OnClickListener listener;
    int mode;

    public CountryAdapter(Context context, List<Country> countries, CountryAdapter.OnClickListener _listener , int mode) {
        this.context = context;
        this.countries = countries;
        this.listener = _listener;
        this.mode = mode;
    }

    public void setList(List<Country> countries){
        this.countries = countries;
        this.notifyDataSetChanged();
    }

    public interface OnClickListener {
        void onClick(Country country);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView land;
        private TextView name;
        private LinearLayout  linearLayout;



        private View layout;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            layout = itemView;
            land = layout.findViewById(R.id.land);
            linearLayout = layout.findViewById(R.id.linerlayout);



        }
    }

    @NonNull
    @Override
    public CountryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.cutsomrowland, parent, false);
        CountryAdapter.ViewHolder vh = new CountryAdapter.ViewHolder(v);

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Country country = countries.get(position);
        holder.land.setText(countries.get(position).getStrArea());
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(country);

            }
        });

    }



    @Override
    public int getItemCount() {
        return countries.size();
    }


}
