package com.example.mealapp.calander;

import androidx.lifecycle.LiveData;

import com.example.mealapp.db.POJOmealPerCalander;
import com.example.mealapp.model.RepoInterface;

import java.util.List;

public class PresenterCalander implements IpresenterCalander{
    private RepoInterface repoInterface;

    public PresenterCalander(RepoInterface repoInterface) {
        this.repoInterface = repoInterface;
    }

    @Override
    public LiveData<List<POJOmealPerCalander>> getFromCalander(int id) {
        return repoInterface.getCachedMealfromCal(id);

    }

    @Override
    public void removeMeal(POJOmealPerCalander product) {
        repoInterface.deletefromCal(product);

    }

    @Override
    public void sendDay(int i) {
        repoInterface.getid(i);

    }
}
