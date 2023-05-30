package com.example.mealapp.fav;

import androidx.lifecycle.LiveData;

import com.example.mealapp.db.MealPojo;
import com.example.mealapp.model.RepoInterface;

import java.util.List;

public class FavPresenter implements FavPrsenterInterface{
    private RepoInterface repoInterface;

    public FavPresenter(RepoInterface repoInterface) {
        this.repoInterface = repoInterface;
    }

    @Override
    public LiveData<List<MealPojo>> getFavourites() {
        return repoInterface.getSavedProductsfromfav();
    }

    @Override
    public void removeMeal(MealPojo product) {
        repoInterface.deletefromfav(product);

    }
}
