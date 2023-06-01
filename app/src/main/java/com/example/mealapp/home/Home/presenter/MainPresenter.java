package com.example.mealapp.home.Home.presenter;

import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;

import com.example.mealapp.countryListMeal.CounteryinfoAdapter;
import com.example.mealapp.db.MealPojo;
import com.example.mealapp.db.POJOmealPerCalander;
import com.example.mealapp.home.Home.View.ViewHome;
import com.example.mealapp.model.CategoriesM;
import com.example.mealapp.model.Country;
import com.example.mealapp.model.IngredientList;
import com.example.mealapp.model.Meal;
import com.example.mealapp.model.RandomMeal;
import com.example.mealapp.model.RepoInterface;
import com.example.mealapp.network.ApiClient;
import com.example.mealapp.network.NetworkDelegate;
import com.example.mealapp.network.RemoteSource;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Callback;

public class MainPresenter implements NetworkDelegate, Ipresenter {
    char c;
    ViewHome viewHome;
    private RepoInterface repoInterface;
    final static String TAG ="main";
    String country ;
    ApiClient apiClient;
    RemoteSource remoteSource;
    View view ;
    String data;
    List <Meal>meal ;
    CounteryinfoAdapter counteryinfoAdapter;




    public MainPresenter(ViewHome viewHome, RepoInterface repoInterface) {

        this.viewHome = viewHome;
        this.repoInterface = repoInterface;



    }


    @Override
    public void onSuccessMealByFilter(List<Meal> meals) {
       viewHome.setMealpercat(meals);

    }

    @Override
    public void onSuccessMealByFilterland( List<Meal> meals) {
        if(meals!=null){
        meals.get(0).getStrMeal();
        viewHome.setMeal(meals);
        System.out.println(meals.get(1).getStrMeal());}

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
    public void onSuccessResultRandMeal(ArrayList<RandomMeal> meal) {
        viewHome.SetRandomMeal(meal);





    }

    @Override
    public void onSuccessResultwithsearch(ArrayList<RandomMeal> meal) {


    }

    @Override
    public void onSuccessResultMealPerID(ArrayList<RandomMeal> meal) {
        viewHome.setMealPerID(meal);
        System.out.println("Egyptian food"+meal.get(0).getStrMeal());

    }

    @Override
    public void onFailureResultResearch(String errorMessage) {

    }

    @Override
    public void unchecksuccses(boolean i) {

    }

    @Override
    public void onFailureResultRandMeal(String errorMessage) {

    }





    @Override
    public void onFailure(String error) {

    }

    @Override
    public void onSuccessCountries(List<Country> countries ) {
        viewHome.SetCountry(countries);
    }

    @Override
    public void onSuccessIngredient(List<IngredientList> ingredientLists) {

    }

    @Override
    public void getcategory() {
        repoInterface.getfromNetwork(this,data,c);
    }

    @Override
    public void getRandomMeal() {
       repoInterface.getfromNetwork(this,data,c);
    }

    @Override
    public void getCountry() {
        repoInterface.getfromNetwork(this,data,c);

    }

    @Override
    public void getIngredient() {

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


    @Override
    public void getMeal() {
        repoInterface.getfromNetwork(this,data,c

        );
    }


}
