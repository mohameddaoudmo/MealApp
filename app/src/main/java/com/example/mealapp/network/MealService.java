package com.example.mealapp.network;

import com.example.mealapp.model.CategoriesM;
import com.example.mealapp.model.CountryResponse;
import com.example.mealapp.model.IngredientResponse;
import com.example.mealapp.model.Meals;
import com.example.mealapp.model.MealswithLand;
import com.example.mealapp.model.MyResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MealService {

    @GET("categories.php")
    Call<CategoriesM> getCategories();

    @GET("random.php")
    Call<Meals> getRandomMeal();
    @GET("filter.php?")
    Call<MyResponse> getAllMealsByArea(@Query("a") String area);
    @GET("search.php")
    Call<Meals> searchMeals(@Query("s") String query);

    @GET("filter.php?")
    Call<MyResponse> getAllMealsByIngredient(@Query("i") String ingredient);

    @GET("filter.php?")
    Call<MyResponse> getAllMealsByCategory(@Query("c") String category);
    @GET("lookup.php")
    Call<Meals> getMealById(@Query("i") String mealId);

    @GET("list.php?a=list")
    Call<CountryResponse> getAllCountries();

    @GET("api/json/v1/1/list.php?i=list")
    Call<IngredientResponse> getAllIngredients();


}
