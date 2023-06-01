package com.example.mealapp.search;


import com.example.mealapp.model.Meals;
import com.example.mealapp.model.RandomMeal;

import java.util.List;

public interface SearchView {
    void setIgriedent(List<RandomMeal> meals);
    void onfaliure (String message);
    void checkunscuss(boolean i);
}
