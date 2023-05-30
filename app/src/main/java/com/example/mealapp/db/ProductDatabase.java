package com.example.mealapp.db;

import android.content.Context;
//
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.mealapp.model.RandomMeal;


@Database(entities = {MealPojo.class}, version = 1)
public abstract class ProductDatabase extends RoomDatabase {
    private static ProductDatabase instance = null;

    public abstract ProductDAO productDAO();

    public static synchronized ProductDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), ProductDatabase.class, "Meal")
                    .build();
        }
        return instance;
    }
}
