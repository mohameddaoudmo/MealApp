package com.example.mealapp.home.Home.presenter;

import android.util.Log;

import com.example.mealapp.home.Home.View.ViewHome;
import com.example.mealapp.model.Categories;
import com.example.mealapp.model.Meal;
import com.example.mealapp.model.RepoInterface;
import com.example.mealapp.network.NetworkDelegate;

import java.util.ArrayList;
import java.util.List;

public class MainPresenter implements NetworkDelegate, Ipresenter {
    ViewHome viewHome;
    private RepoInterface repoInterface;
    final static String TAG ="main";

    public MainPresenter(ViewHome viewHome, RepoInterface repoInterface) {
        this.viewHome = viewHome;
        this.repoInterface = repoInterface;
    }

    @Override
    public void onSuccessResultCat(List<Categories.Category> categories) {
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
    public void getcategory() {
        repoInterface.getfromNetwork(this);
    }

    @Override
    public void getRandomMeal() {
        repoInterface.getfromNetwork(this);
    }
}
