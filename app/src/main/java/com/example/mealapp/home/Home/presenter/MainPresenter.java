package com.example.mealapp.home.Home.presenter;

import android.util.Log;

import com.example.mealapp.home.Home.View.ViewHome;
import com.example.mealapp.model.CategoriesM;
import com.example.mealapp.model.Country;
import com.example.mealapp.model.IngredientList;
import com.example.mealapp.model.Meal;
import com.example.mealapp.model.RepoInterface;
import com.example.mealapp.network.NetworkDelegate;

import java.util.ArrayList;
import java.util.List;

public class MainPresenter implements NetworkDelegate, Ipresenter {
    ViewHome viewHome;
    private RepoInterface repoInterface;
    final static String TAG ="main";
    String country ;


    public MainPresenter(ViewHome viewHome, RepoInterface repoInterface) {
        this.viewHome = viewHome;
        this.repoInterface = repoInterface;
        System.out.println(country);
    }

    @Override
    public void onSuccessResultCat(List<CategoriesM.Category> categories) {
        viewHome.setCategory(categories);

        Log.i(TAG,"Sucussc");

    }

    @Override
    public void onFailureResultCat(String errorMessage) {


    }

    @Override
    public void onSuccessResultRandMeal(ArrayList<Meal> meal) {
        viewHome.SetMeal(meal);




    }

    @Override
    public void onFailureResultRandMeal(String errorMessage) {

    }

    @Override
    public void onSuccessMealByFilter(List<Meal> meals) {

    }

    @Override
    public void onFailure(String error) {

    }

    @Override
    public void onSuccessCountries(List<Country> countries) {
        viewHome.SetCountry(countries);
        country =countries.get(0).getStrArea();
    }

    @Override
    public void onSuccessIngredient(List<IngredientList> ingredientLists) {

    }

    @Override
    public void getcategory() {
        repoInterface.getfromNetwork(this);
    }

    @Override
    public void getRandomMeal() {
        repoInterface.getfromNetwork(this);
    }

    @Override
    public void getCountry() {
        repoInterface.getfromNetwork(this);

    }

    @Override
    public void getIngredient() {

    }
}
