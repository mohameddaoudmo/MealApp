package com.example.mealapp.countryListMeal;

import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;

import com.example.mealapp.home.Home.View.ViewHome;
import com.example.mealapp.home.Home.presenter.Ipresenter;
import com.example.mealapp.model.CategoriesM;
import com.example.mealapp.model.Country;
import com.example.mealapp.model.IngredientList;
import com.example.mealapp.model.Meal;
import com.example.mealapp.model.RandomMeal;
import com.example.mealapp.model.RepoInterface;
import com.example.mealapp.network.ApiClient;
import com.example.mealapp.network.NetworkDelegate;
import com.example.mealapp.network.RemoteSource;

import java.util.ArrayList;
import java.util.List;

public class Presenter implements NetworkDelegate, ICounPresenter  {

    char c;
    String data ="Egypt" ;
    private RepoInterface repoInterface;
    final static String TAG ="main";
    String country ;
    ApiClient apiClient;
    RemoteSource remoteSource;
    ViewinfoCountry view ;
    List<Meal> meal ;
    CounteryinfoAdapter counteryinfoAdapter;




    public Presenter(ViewinfoCountry viewHome, RepoInterface repoInterface) {

        this.view = viewHome;
        this.repoInterface = repoInterface;
        System.out.println(country);
        System.out.println(data);


    }

    @Override
    public void getMeal() {
    repoInterface.getfromNetwork(this,data,c);
        System.out.println("AAAAAAAAAAAAAAAAAAAAAMMMMMMMMMMMMMMM"
        +data);

    }

    @Override
    public void sendDataToApi(String data, char c) {
        this.data = data;
        this.c =c;


    }

    @Override
    public void onSuccessMealByFilter(List<Meal> meals) {
        System.out.println(meals.get(0).getStrMeal());


    }

    @Override
    public void onSuccessMealByFilterland(List<Meal> meals) {

    }

    @Override
    public void onSuccessResultCat(List<CategoriesM.Category> categories) {

    }

    @Override
    public void onFailureResultCat(String errorMessage) {


    }

    @Override
    public void onSuccessResultRandMeal(ArrayList<RandomMeal> meal) {

    }

    @Override
    public void onFailureResultRandMeal(String errorMessage) {

    }






    @Override
    public void onFailure(String error) {

    }

    @Override
    public void onSuccessCountries(List<Country> countries) {
        System.out.println("CCCCCCCCCCCCCCCCCCCCCCCCCCCc"+countries.get(0).getStrArea());


    }

    @Override
    public void onSuccessIngredient(List<IngredientList> ingredientLists) {

    }
}

