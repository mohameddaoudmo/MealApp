package com.example.mealapp.fav;

import androidx.lifecycle.LiveData;

import com.example.mealapp.db.MealPojo;

import java.util.List;

public interface FavPrsenterInterface {
    public LiveData<List<MealPojo>> getFavourites();
    public void removeMeal(MealPojo product);
}
