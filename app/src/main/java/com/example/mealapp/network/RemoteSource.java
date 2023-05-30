package com.example.mealapp.network;

import retrofit2.Callback;

public interface RemoteSource {
    public void getFromNetwork(NetworkDelegate networkDelegate, String name , char c);

}
