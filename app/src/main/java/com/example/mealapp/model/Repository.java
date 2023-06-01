package com.example.mealapp.model;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.mealapp.db.LocalSource;
import com.example.mealapp.db.MealPojo;
import com.example.mealapp.db.POJOmealPerCalander;
import com.example.mealapp.network.ApiClient;
import com.example.mealapp.network.NetworkDelegate;
import com.example.mealapp.network.RemoteSource;


import java.util.List;

import retrofit2.Callback;

public class Repository implements RepoInterface{


    RemoteSource remoteSource;
    private static Repository repo = null;
    LocalSource localSource;


    public static synchronized Repository getInstance(LocalSource localSource , RemoteSource remoteSource , Context context){
        if (repo == null){
            repo = new Repository( localSource , remoteSource);
        }
        return repo;
    }

    public Repository(LocalSource localSource , RemoteSource remoteSource){
        this.localSource = localSource;
        this.remoteSource =remoteSource;

    }



    @Override
    public void getfromNetwork(NetworkDelegate networkDelegate, String name , char c) {
        remoteSource.getFromNetwork(networkDelegate ,name ,c);
    }

    @Override
    public LiveData<List<MealPojo>> getSavedProductsfromfav() {
        return localSource.getCachedMealfromfav();
    }

    @Override
    public void deletefromfav(MealPojo product) {
        localSource.deletefromfav(product);

    }

    @Override
    public void insertintofav(MealPojo product) {
        localSource.insertintofav(product);

    }

    @Override
    public void insertintoCa(POJOmealPerCalander meal) {
        localSource.insertintoCa(meal);
    }

    @Override
    public void deletefromCal(POJOmealPerCalander mea) {
        localSource.deletefromCal(mea);

    }

    @Override
    public LiveData<List<POJOmealPerCalander>> getCachedMealfromCal(int id) {
        return localSource.getCachedMealfromCal();
    }

    @Override
    public void getid(int i) {
        localSource.getid(i);

    }


}


