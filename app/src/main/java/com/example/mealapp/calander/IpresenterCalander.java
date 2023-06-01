package com.example.mealapp.calander;

import androidx.lifecycle.LiveData;

import com.example.mealapp.db.MealPojo;
import com.example.mealapp.db.POJOmealPerCalander;

import java.util.List;

public interface IpresenterCalander {
    public LiveData<List<POJOmealPerCalander>> getFromCalander(int id);
    public void removeMeal(POJOmealPerCalander product);
    public void sendDay(int i);
}
