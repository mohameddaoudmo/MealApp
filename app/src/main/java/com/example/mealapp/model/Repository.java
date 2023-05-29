package com.example.mealapp.model;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.mealapp.db.LocalSource;
import com.example.mealapp.network.NetworkDelegate;
import com.example.mealapp.network.RemoteSource;


import java.util.List;

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
    public void getfromNetwork(NetworkDelegate networkDelegate) {
        remoteSource.getFromNetwork(networkDelegate);
    }}


