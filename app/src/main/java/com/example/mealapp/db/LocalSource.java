package com.example.mealapp.db;

import androidx.lifecycle.LiveData;


import com.example.mealapp.model.RandomMeal;

import java.util.List;

public interface LocalSource {
  public void insertintofav(MealPojo meal);
    public void deletefromfav(MealPojo mea);
  public LiveData<List<MealPojo>> getCachedMealfromfav();
  public void insertintoCa(POJOmealPerCalander meal);
  public void deletefromCal(POJOmealPerCalander mea);
  public LiveData<List<POJOmealPerCalander>> getCachedMealfromCal();
  public void getid(int i);
}
