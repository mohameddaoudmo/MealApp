package com.example.mealapp.search;

import android.view.View;

import com.example.mealapp.db.MealPojo;
import com.example.mealapp.db.POJOmealPerCalander;
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

public class SearchPresenter implements SearchPresenterInterface, NetworkDelegate {
    char c;
    SearchView viewHome;
    String s;

    public SearchPresenter(SearchView viewHome, RepoInterface repoInterface) {
        this.viewHome = viewHome;
        this.repoInterface = repoInterface;
    }

    private RepoInterface repoInterface;
    final static String TAG ="main";
    String country ;
    ApiClient apiClient;
    RemoteSource remoteSource;
    View view ;
    String data;


    @Override
    public void onSuccessMealByFilter(List<Meal> meals) {

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
    public void onSuccessResultwithsearch(ArrayList<RandomMeal> meal) {
        viewHome.setIgriedent(meal);


    }

    @Override
    public void onSuccessResultMealPerID(ArrayList<RandomMeal> meal) {

    }

    @Override
    public void onFailureResultResearch(String errorMessage) {

    }

    @Override
    public void unchecksuccses(boolean i) {
        viewHome.checkunscuss(i);

    }

    @Override
    public void onFailureResultRandMeal(String errorMessage) {

    }

    @Override
    public void onFailure(String error) {

    }

    @Override
    public void onSuccessCountries(List<Country> countries) {

    }

    @Override
    public void onSuccessIngredient(List<IngredientList> ingredientLists) {

    }


    @Override
    public void getCoonection() {
        repoInterface.getfromNetwork(this,s,c);
    }
    @Override
    public void removemealtofav(MealPojo meal) {
        repoInterface.deletefromfav(meal);

    }

    @Override
    public void addmealtofav(MealPojo meal) {
        repoInterface.insertintofav(meal);

    }

    @Override
    public void removemealtocal(POJOmealPerCalander meal) {
        repoInterface.deletefromCal(meal);
    }

    @Override
    public void addmealtocal(POJOmealPerCalander meal) {
        repoInterface.insertintoCa(meal);

    }

}
