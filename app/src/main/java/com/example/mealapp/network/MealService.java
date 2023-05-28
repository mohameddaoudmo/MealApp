package com.example.mealapp.network;

import com.example.mealapp.model.Categories;
import com.example.mealapp.model.Meals;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MealService {

    @GET("categories.php")
    Call<Categories> getCategories();

    @GET("random.php")
    Call<Meals> getRandomMeal();
}
