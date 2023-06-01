package com.example.mealapp.search;

import com.example.mealapp.db.MealPojo;
import com.example.mealapp.db.POJOmealPerCalander;

public interface SearchPresenterInterface {
public void getCoonection();
    public void removemealtofav(MealPojo meal);
    public void addmealtofav(MealPojo meal);
    public void removemealtocal(POJOmealPerCalander meal);
    public void addmealtocal(POJOmealPerCalander meal);
}
