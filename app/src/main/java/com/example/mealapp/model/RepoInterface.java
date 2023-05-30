package com.example.mealapp.model;

import androidx.lifecycle.LiveData;

import com.example.mealapp.network.NetworkDelegate;

import java.util.List;

import retrofit2.Callback;

public interface RepoInterface {

    public void getfromNetwork(NetworkDelegate networkDelegate,String name ,char c);


}
