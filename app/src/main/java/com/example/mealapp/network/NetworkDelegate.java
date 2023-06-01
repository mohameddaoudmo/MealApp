package com.example.mealapp.network;

import com.example.mealapp.model.CategoriesM;
import com.example.mealapp.model.Country;
import com.example.mealapp.model.IngredientList;
import com.example.mealapp.model.Meal;
import com.example.mealapp.model.RandomMeal;

import java.util.ArrayList;
import java.util.List;

public interface NetworkDelegate {
    public void onSuccessMealByFilter(List<Meal> meals);
    public void onSuccessMealByFilterland(List<Meal> meals);
    public void onSuccessResultCat(List<CategoriesM.Category> categories);
    public void onFailureResultCat(String errorMessage);
    public void onSuccessResultRandMeal(ArrayList<RandomMeal> meal);
    public void onSuccessResultwithsearch(ArrayList<RandomMeal> meal);
    public void onSuccessResultMealPerID(ArrayList<RandomMeal> meal);
    public void onFailureResultResearch(String errorMessage);
    public void unchecksuccses(boolean i);


    public void onFailureResultRandMeal(String errorMessage);




    public void onFailure(String error);
    public void onSuccessCountries(List<Country> countries);
    public void onSuccessIngredient(List<IngredientList> ingredientLists);

}
