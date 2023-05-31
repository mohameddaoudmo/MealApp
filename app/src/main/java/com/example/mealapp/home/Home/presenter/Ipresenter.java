package com.example.mealapp.home.Home.presenter;

import com.example.mealapp.db.MealPojo;
import com.example.mealapp.db.POJOmealPerCalander;

import retrofit2.Callback;

public interface Ipresenter {

    void getcategory ();
    void getRandomMeal ();
    void getMeal ();
    void getCountry();
    void getIngredient();
    public void removemealtofav(MealPojo meal);
    public void addmealtofav(MealPojo meal);
    public void removemealtocal(POJOmealPerCalander meal);
    public void addmealtocal(POJOmealPerCalander meal);


}
