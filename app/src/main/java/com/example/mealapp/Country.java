package com.example.mealapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mealapp.countryListMeal.CounteryinfoAdapter;
import com.example.mealapp.countryListMeal.ViewinfoCountry;
import com.example.mealapp.db.ConcreteLocalSource;
import com.example.mealapp.home.Home.View.ViewHome;
import com.example.mealapp.home.Home.presenter.MainPresenter;
import com.example.mealapp.model.CategoriesM;
import com.example.mealapp.model.Meal;
import com.example.mealapp.model.RandomMeal;
import com.example.mealapp.model.Repository;
import com.example.mealapp.network.ApiClient;

import java.util.ArrayList;
import java.util.List;

public class Country extends Fragment implements ViewHome, ViewinfoCountry, CounteryinfoAdapter.OnClickListener {

    MainPresenter presenter;


    RecyclerView meal;
    CounteryinfoAdapter adapter;
    List<Meal> mealArrayList ;
    String info ;
    public static final String TAG ="CountryInfo";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        presenter = new MainPresenter(this , Repository.getInstance( ConcreteLocalSource.getInstance(getContext()) , ApiClient.getInstance() , getContext()));

        return inflater.inflate(R.layout.fragment_country, container, false);
    }

    @Override
    public void onClick(Meal meal) {

    }

    @Override
    public void showMeal(List<Meal> meals) {

    }

    @Override
    public void setMeal(List<Meal> meals) {
        System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAaMMMMMMMMMMMMMMMMM");

    }

    @Override
    public void setMealpercat(List<Meal> meals) {

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
    public void SetCountry(List<com.example.mealapp.model.Country> countries) {

    }
}