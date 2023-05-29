package com.example.mealapp.network;

import com.example.mealapp.model.CategoriesM;
import com.example.mealapp.model.Meals;
import com.example.mealapp.model.MealswithLand;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MealService {

    @GET("categories.php")
    Call<CategoriesM> getCategories();

    @GET("random.php")
    Call<Meals> getRandomMeal();
    @GET("list.php?a=list")
    Call<List<MealswithLand>> getMeals();

}
