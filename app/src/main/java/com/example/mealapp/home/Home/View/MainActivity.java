package com.example.mealapp.home.Home.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mealapp.R;
import com.example.mealapp.db.ConcreteLocalSource;
import com.example.mealapp.home.Home.presenter.MainPresenter;
import com.example.mealapp.model.Categories;
import com.example.mealapp.model.Meal;
import com.example.mealapp.model.Repository;
import com.example.mealapp.network.ApiClient;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ViewHome , CatgorysAdapter.OnClickListener {

    RecyclerView categroy;
    CatgorysAdapter adapter;
    MainPresenter presenter;
    Repository repo ;
    ImageView photoFormeal;
    TextView textViewformeal;
    List<Categories.Category> categories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        categories =new ArrayList<>();
        categroy =findViewById(R.id.catgoryrecy);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        categroy.setLayoutManager(manager);
        adapter = new CatgorysAdapter(MainActivity.this, categories, this, 0);
        categroy.setAdapter(adapter);
        presenter = new MainPresenter(this,Repository.getInstance(ConcreteLocalSource.getInstance(this), ApiClient.getInstance(),this));
        presenter.getcategory();
        presenter.getRandomMeal();
        photoFormeal = findViewById(R.id.imgformeal);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void setCategory(List<Categories.Category> category) {
        adapter.setList(category);

    }

    @Override
    public void onErrorLoading(String message) {

    }

    @Override
    public void SetMeal(ArrayList<Meal> meal) {
        Glide.with(this)
                .load(meal.get(0).getStrMealThumb())
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_foreground)
                .into(photoFormeal);



    }

    @Override
    public void onClick(Categories.Category category) {

    }
}