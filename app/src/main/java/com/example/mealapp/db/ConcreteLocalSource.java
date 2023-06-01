package com.example.mealapp.db;

import android.content.Context;

//import androidx.lifecycle.LiveData;


import androidx.lifecycle.LiveData;

import com.example.mealapp.calander.CalanderActivity;
import com.example.mealapp.model.Meal;
import com.example.mealapp.model.RandomMeal;

import java.util.Calendar;
import java.util.List;

public class ConcreteLocalSource implements LocalSource{
    int id ;

    private ProductDAO productDAO;
    private calanderDAO calanderDAO;
    private static ConcreteLocalSource concreteLocalSource = null;
    private ConcreteLocalSource(Context context){

        productDAO = ProductDatabase.getInstance(context.getApplicationContext()).productDAO();
        calanderDAO =ProductDatabase.getInstance(context.getApplicationContext()).productCAl();
        Calendar calendar = Calendar.getInstance();
        id = calendar.get(Calendar.DAY_OF_MONTH);    }

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

    @Override
    public void insertintoCa(POJOmealPerCalander meal) {
        new Thread(){
            public void run(){
                calanderDAO.insertProduct(meal);
            }
        }.start();


    }

    @Override
    public void deletefromCal(POJOmealPerCalander mea) {
        new Thread(){
            public void run(){
                calanderDAO.deleteProduct(mea);
            }
        }.start();



    }
    @Override
    public void getid(int i) {
        System.out.println("id is"+id);
        id=i;

    }


    @Override
    public LiveData<List<POJOmealPerCalander>> getCachedMealfromCal() {
        LiveData<List<POJOmealPerCalander>> list = calanderDAO.getProductsById(id);

        return list;
    }



}
