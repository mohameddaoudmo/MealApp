package com.example.mealapp.network;

import com.example.mealapp.model.Categories;
import com.example.mealapp.model.Meal;

import java.util.ArrayList;
import java.util.List;

public interface NetworkDelegate {
    public void onSuccessResultCat(List<Categories.Category> categories);
    public void onFailureResultCat(String errorMessage);
    public void onSuccessResultRandMeal(ArrayList<Meal> meal);
    public void onFailureResultRandMeal(String errorMessage);
}
