package com.example.mealapp.network;

import com.example.mealapp.model.CategoriesM;
import com.example.mealapp.model.Country;
import com.example.mealapp.model.CountryResponse;
import com.example.mealapp.model.Ingredient;
import com.example.mealapp.model.IngredientList;
import com.example.mealapp.model.IngredientResponse;
import com.example.mealapp.model.Meal;
import com.example.mealapp.model.Meals;
import com.example.mealapp.model.MealswithLand;
import com.example.mealapp.model.MyResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient implements RemoteSource {
    private static final String BASE_URL = "https://www.themealdb.com/api/json/v1/1/";
    String data;
    String name;
    char c = 'a';
    String mealID;
    String cat;
    String mealNameForSearch;


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

    public static synchronized ApiClient getInstance() {
        if (apiClient == null) {
            apiClient = new ApiClient();
        }
        return apiClient;
    }


    public void startCall(NetworkDelegate networkDelegator, String name, char c) {


        Callback responseCallback = new Callback<CategoriesM>() {


            @Override
            public void onResponse(Call<CategoriesM> call, retrofit2.Response<CategoriesM> response) {
                if (response.isSuccessful()) {
                    networkDelegator.onSuccessResultCat(response.body().getCategories());
                }
            }

            @Override
            public void onFailure(Call<CategoriesM> call, Throwable t) {
                networkDelegator.onFailureResultCat(t.getMessage());
            }
        };

        Call<CategoriesM> categories = apiService.getCategories();

        categories.enqueue(responseCallback);
        Callback responseCallbackMe = new Callback<Meals>() {


            @Override
            public void onResponse(Call<Meals> call, retrofit2.Response<Meals> response) {
                if (response.isSuccessful()) {
                    networkDelegator.onSuccessResultRandMeal(response.body().getMeals());
                }
            }

            @Override
            public void onFailure(Call<Meals> call, Throwable t) {

            }
        };

        Call<Meals> meal = apiService.getRandomMeal();

        meal.enqueue(responseCallbackMe);

        Callback responseCallbackLand = new Callback<CountryResponse>() {
            @Override
            public void onResponse(Call<CountryResponse> call, Response<CountryResponse> response) {
                networkDelegator.onSuccessCountries(response.body().getCountries());
            }

            @Override
            public void onFailure(Call<CountryResponse> call, Throwable t) {

            }
        };
        Call<CountryResponse> country = apiService.getAllCountries();

        country.enqueue(responseCallbackLand);
        Callback responsemeal = new Callback<MyResponse>() {
            @Override
            public void onResponse(Call<MyResponse> call, Response<MyResponse> response) {
                networkDelegator.onSuccessMealByFilterland(response.body().getMeals());
            }

            @Override
            public void onFailure(Call<MyResponse> call, Throwable t) {

            }
        };
        if (data != null) {
            System.out.println(cat + "data in it");
            Call<MyResponse> mmealforlandeal = apiService.getAllMealsByArea(data);

            mmealforlandeal.enqueue(responsemeal);
        }
        Callback responsemealforcat = new Callback<MyResponse>() {
            @Override
            public void onResponse(Call<MyResponse> call, Response<MyResponse> response) {
                networkDelegator.onSuccessMealByFilter(response.body().getMeals());
            }

            @Override
            public void onFailure(Call<MyResponse> call, Throwable t) {

            }
        };
        if (cat != null) {
            System.out.println(cat + "data in it");
            Call<MyResponse> mealforcat = apiService.getAllMealsByCategory(cat);

            mealforcat.enqueue(responsemealforcat);
        }


        Callback mealPerId = new Callback<Meals>() {


            @Override
            public void onResponse(Call<Meals> call, retrofit2.Response<Meals> response) {
                if (response.isSuccessful()) {
                    networkDelegator.onSuccessResultMealPerID(response.body().getMeals());
                    System.out.println("mealmeal" + response.body().getMeals().get(0).getStrMeal());

                }
            }

            @Override
            public void onFailure(Call<Meals> call, Throwable t) {

            }
        };
        if (mealPerId != null) {
            Call<Meals> mealperId = apiService.getMealById(mealID);

            mealperId.enqueue(mealPerId);
        }


        Callback responseCallbacksearchmeal = new Callback<Meals>() {


            @Override
            public void onResponse(Call<Meals> call, Response<Meals> response) {
                if (response.isSuccessful()) {
                    networkDelegator.onSuccessResultwithsearch(response.body().getMeals());
                }
            }

            @Override
            public void onFailure(Call<Meals> call, Throwable t) {
                System.out.println("failded");
                networkDelegator.unchecksuccses(true);
                networkDelegator.onFailureResultResearch(t.getMessage());

            }
        };



            Call<Meals> searcmeal = apiService.searchMeals(mealNameForSearch);

            searcmeal.enqueue(responseCallbacksearchmeal);



    }


    @Override
    public void getFromNetwork(NetworkDelegate networkDelegate, String name, char c) {


        ApiClient.getInstance().startCall(networkDelegate, name, c);
        this.name = name;


    }

    public void senddata(String s) {
        this.data = s;

    }

    public void senddataforCat(String s) {
        this.cat = s;
    }

    public void senddataformealId(String s) {
        this.mealID = s;
    }

    public void retrievequry(String s) {
        this.mealNameForSearch = s;

    }

}
