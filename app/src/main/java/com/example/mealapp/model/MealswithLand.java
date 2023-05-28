package com.example.mealapp.model;

import java.util.ArrayList;

public class MealswithLand {
    public ArrayList<MealForLand> meals;

    public ArrayList<MealForLand> getMeals() {
        return meals;
    }

    public void setMeals(ArrayList<MealForLand> meals) {
        this.meals = meals;
    }

    public MealswithLand(ArrayList<MealForLand> meals) {
        this.meals = meals;
    }

    public class MealForLand{
        public String strArea;

        public MealForLand(String strArea) {
            this.strArea = strArea;
        }

        public String getStrArea() {
            return strArea;
        }

        public void setStrArea(String strArea) {
            this.strArea = strArea;
        }
    }
}
