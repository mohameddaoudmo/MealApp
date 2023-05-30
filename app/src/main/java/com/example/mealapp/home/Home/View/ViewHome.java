package com.example.mealapp.home.Home.View;

import com.example.mealapp.db.MealPojo;
import com.example.mealapp.model.CategoriesM;
import com.example.mealapp.model.Country;
import com.example.mealapp.model.Meal;
import com.example.mealapp.model.RandomMeal;

import java.util.ArrayList;
import java.util.List;

public interface ViewHome {
    void setMeal(List<Meal> meals);
    void setMealpercat(List<Meal> meals);
    void setMealPerID(ArrayList<RandomMeal> meal);
    void addmealtofav(MealPojo mealPojo);
    void addmealtocal(MealPojo mealPojo);
    void deletemealtofav(MealPojo mealPojo);
    void deletemealtocal(MealPojo mealPojo);


    void showLoading();
    void hideLoading();
    void setCategory(List<CategoriesM.Category> category);
    void onErrorLoading(String message);
    void SetRandomMeal(ArrayList<RandomMeal> meal);

    void SetCountry (List<Country> countries);

}