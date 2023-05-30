package com.example.mealapp.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;


import com.example.mealapp.model.RandomMeal;

import java.util.List;


@Dao
public interface ProductDAO {

    @Query("SELECT * FROM meal")
    LiveData<List<MealPojo>> getAllProducts();

    @Insert(onConflict = OnConflictStrategy.IGNORE)

    void insertProduct(MealPojo meal);

    @Delete
    void deleteProduct(MealPojo Meal);
}
