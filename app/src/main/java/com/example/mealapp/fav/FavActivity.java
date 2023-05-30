package com.example.mealapp.fav;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.mealapp.R;
import com.example.mealapp.db.ConcreteLocalSource;
import com.example.mealapp.db.MealPojo;
import com.example.mealapp.model.Meal;
import com.example.mealapp.model.Repository;
import com.example.mealapp.network.ApiClient;

import java.util.ArrayList;
import java.util.List;

public class FavActivity extends AppCompatActivity implements FavAdapter.OnClickListener{
    List<MealPojo> productList = new ArrayList<>();
    RecyclerView favouriteList;
    Repository repo;
    FavPresenter favouritePresenter;
    FavAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fav);
        favouriteList = findViewById(R.id.favrecyc);
        favouriteList.setHasFixedSize(true);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        favouriteList.setLayoutManager(manager);
        adapter = new FavAdapter(FavActivity.this, productList, FavActivity.this, 1);
        favouriteList.setAdapter(adapter);
        favouritePresenter = new FavPresenter( Repository.getInstance(ConcreteLocalSource.getInstance(this), ApiClient.getInstance(), this));
        favouritePresenter.getFavourites().observe(this, new Observer<List<MealPojo>>() {
            @Override
            public void onChanged(List<MealPojo> products) {
                adapter.setList(products);
            }
        });
    }

    @Override
    public void onClick(MealPojo meal) {
        favouritePresenter.removeMeal(meal);


    }

    @Override
    public void onClickFav(Meal meal) {

    }

    @Override
    public void onClickCalanender(Meal meal) {

    }
}
