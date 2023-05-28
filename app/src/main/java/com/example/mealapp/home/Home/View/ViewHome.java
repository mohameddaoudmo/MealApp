package com.example.mealapp.home.Home.View;

import com.example.mealapp.model.Categories;
import com.example.mealapp.model.Meal;

import java.util.ArrayList;
import java.util.List;

public interface ViewHome {
    void showLoading();
    void hideLoading();
    void setCategory(List<Categories.Category> category);
    void onErrorLoading(String message);
    void SetMeal(ArrayList<Meal> meal);

}