package com.example.mealapp.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;
@Dao

public interface calanderDAO {


    @Query("SELECT * FROM MEALPERCAL WHERE day = :id")
    LiveData<List<POJOmealPerCalander>> getProductsById(int id);

    @Insert(onConflict = OnConflictStrategy.IGNORE)

    void insertProduct(POJOmealPerCalander meal);

    @Delete
    void deleteProduct(POJOmealPerCalander Meal);
}
