package com.example.mealapp.db;

import android.content.Context;

//import androidx.lifecycle.LiveData;


import androidx.lifecycle.LiveData;

import com.example.mealapp.model.Meal;
import com.example.mealapp.model.RandomMeal;

import java.util.List;

public class ConcreteLocalSource implements LocalSource{

    private ProductDAO productDAO;
    private static ConcreteLocalSource concreteLocalSource = null;
    private ConcreteLocalSource(Context context){
        productDAO = ProductDatabase.getInstance(context.getApplicationContext()).productDAO();
    }

    public static synchronized ConcreteLocalSource getInstance(Context context){

        if (concreteLocalSource == null){
            concreteLocalSource = new ConcreteLocalSource(context);
        }
        return concreteLocalSource;
    }

    @Override
    public void insertintofav(MealPojo meal) {
        new Thread(){
            public void run(){
                productDAO.insertProduct(meal);
            }
        }.start();
    }

    @Override
    public void deletefromfav(MealPojo meal) {
        new Thread(){
            public void run(){
                productDAO.deleteProduct(meal);
            }
        }.start();
    }






    @Override
    public LiveData<List<MealPojo>> getCachedMealfromfav() {
        return productDAO.getAllProducts();
    }
}
