package com.example.mealapp.countryListMeal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.mealapp.R;
import com.example.mealapp.db.ConcreteLocalSource;
import com.example.mealapp.home.Home.View.CountryInfo;
import com.example.mealapp.home.Home.View.FragmentCallbacl;
import com.example.mealapp.home.Home.View.ViewHome;
import com.example.mealapp.home.Home.presenter.MainPresenter;
import com.example.mealapp.model.CategoriesM;
import com.example.mealapp.model.Country;
import com.example.mealapp.model.Meal;
import com.example.mealapp.model.RandomMeal;
import com.example.mealapp.model.Repository;
import com.example.mealapp.network.ApiClient;

import java.util.ArrayList;
import java.util.List;

public class MealperCata extends AppCompatActivity implements FragmentCallbacl, ViewHome, CounteryinfoAdapter.OnClickListener {
    MainPresenter presenter;


    RecyclerView meal;
    Bundle extras ;
    CounteryinfoAdapter adapter;
    List<Meal> mealArrayList ;
    String info ;
    public static final String TAG ="CountryInfo";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        extras = getIntent().getExtras();

        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_country_info);
        presenter = new MainPresenter(this , Repository.getInstance( ConcreteLocalSource.getInstance(this) , ApiClient.getInstance() , this));
        mealArrayList = new ArrayList<>();
        meal = findViewById(R.id.mealrecyc);
        GridLayoutManager manager = new GridLayoutManager(this, 2);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        meal.setLayoutManager(manager);
        adapter = new CounteryinfoAdapter(MealperCata.this,mealArrayList , this, 0);
        meal.setAdapter(adapter);
        presenter.getMeal();




    }







    @Override
    public void onClick(Meal meal) {
        Intent intent = new Intent(MealperCata.this, MealView.class);
        intent.putExtra("country_name", "");
        startActivity(intent);
        ApiClient apiClient = ApiClient.getInstance();
        apiClient.senddataformealId(meal.getIdMeal());

    }

    @Override
    public void onClickFav(Meal meal) {

    }

    @Override
    public void onClickCalanender(Meal meal) {

    }


    @Override
    public void setMeal(List<Meal> meals) {




    }

    @Override
    public void setMealpercat(List<Meal> meals) {
        adapter.setList(meals);
        System.out.println("mealasdas");
    }

    @Override
    public void setMealPerID(ArrayList<RandomMeal> meal) {

    }


    @Override
    public void showLoading() {


    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void setCategory(List<CategoriesM.Category> category) {

    }

    @Override
    public void onErrorLoading(String message) {

    }

    @Override
    public void SetRandomMeal(ArrayList<RandomMeal> meal) {

    }

    @Override
    public void SetCountry(List<Country> countries) {

    }

    @Override
    public void onDataFromFragment(String data) {
        System.out.println(data);

    }
}