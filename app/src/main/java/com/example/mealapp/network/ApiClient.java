package com.example.mealapp.network;

import com.example.mealapp.model.Categories;
import com.example.mealapp.model.Meal;
import com.example.mealapp.model.Meals;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient implements RemoteSource{
    private static final String BASE_URL = "https://www.themealdb.com/api/json/v1/1/";


    MealService apiService;
    private static ApiClient apiClient = null;

    private ApiClient() {
        Gson gson = new GsonBuilder().create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        apiService = retrofit.create(MealService.class);
    }
    private static Interceptor provideLoggingInterceptor() {
        return new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
    }

    private static OkHttpClient provideOkHttp() {
        return new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .addNetworkInterceptor(provideLoggingInterceptor())
                .build();
    }

    public static synchronized ApiClient getInstance(){
        if (apiClient == null){
            apiClient = new ApiClient();
        }
        return apiClient;
    }


    public void startCall(NetworkDelegate networkDelegator) {
        Callback responseCallback = new Callback<Categories>(){


            @Override
            public void onResponse(Call<Categories> call, retrofit2.Response<Categories> response) {
                if (response.isSuccessful()){
                    networkDelegator.onSuccessResultCat(response.body().getCategories());
                }
            }

            @Override
            public void onFailure(Call<Categories> call, Throwable t) {
                networkDelegator.onFailureResultCat(t.getMessage());
            }
        };

        Call<Categories> categories = apiService.getCategories();

        categories.enqueue(responseCallback);
        Callback responseCallbackMe = new Callback<Meals>(){


            @Override
            public void onResponse(Call<Meals> call, retrofit2.Response<Meals> response) {
                if (response.isSuccessful()){
                    networkDelegator.onSuccessResultRandMeal(response.body().getMeals());
                }
            }

            @Override
            public void onFailure(Call<Meals> call, Throwable t) {

            }
        };

        Call<Meals> meal = apiService.getRandomMeal();

        meal.enqueue(responseCallbackMe);
    }




    @Override
    public void getFromNetwork(NetworkDelegate networkDelegate) {
        ApiClient.getInstance().startCall(networkDelegate);

    }
}
