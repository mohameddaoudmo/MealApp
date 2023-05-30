package com.example.mealapp.home.Home.View;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.text.util.Linkify;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mealapp.R;
import com.example.mealapp.db.ConcreteLocalSource;
import com.example.mealapp.db.MealPojo;
import com.example.mealapp.home.Home.presenter.MainPresenter;
import com.example.mealapp.model.CategoriesM;
import com.example.mealapp.model.Country;
import com.example.mealapp.model.Meal;
import com.example.mealapp.model.RandomMeal;
import com.example.mealapp.model.Repository;
import com.example.mealapp.network.ApiClient;

import java.util.ArrayList;
import java.util.List;


public class Home extends Fragment implements ViewHome {
    TextView name ;
    TextView cata;
    TextView area ;
    TextView ingreident;
    TextView instruction , mealinstreaction, watchintoutube,link;
    ImageView photoMeal;
    MainPresenter presenter;
    Repository repo ;
    Button addtofav;
    ScrollView scrollView ;

    Button deletefromfav;
    MealPojo mealPojo;
    LinearLayout linearLayout;
    ProgressBar progressBar;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        name =view.findViewById(R.id.meal_name);
        mealPojo = new MealPojo();
        cata =view.findViewById(R.id.meal_category);
        area=view.findViewById(R.id.meal_area);
        ingreident =view.findViewById(R.id.meal_ingredients);
        instruction= view.findViewById(R.id.meal_instructions_title);
        mealinstreaction =view.findViewById(R.id.meal_instructions);
        watchintoutube =view.findViewById(R.id.meal_youtube_title);
        link = view.findViewById(R.id.meal_youtube_link);
        photoMeal= view.findViewById(R.id.meal_image);
        addtofav=view.findViewById(R.id.addtofavviewmealh);
        deletefromfav=view.findViewById(R.id.removefromfavmealviewh);
        scrollView =view.findViewById(R.id.scrollView2);
        progressBar=view.findViewById(R.id.prograssbarHome);
        progressBar.setVisibility(View.VISIBLE);
        scrollView.setVisibility(View.INVISIBLE);
//
        presenter = new MainPresenter(this,Repository.getInstance(ConcreteLocalSource.getInstance(getContext()), ApiClient.getInstance(),getContext()));

        presenter.getRandomMeal();
        addtofav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.addmealtofav(mealPojo);
            }
        });
        deletefromfav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.removemealtofav(mealPojo);
            }
        });

    }

    public Home() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

// Use a Handler to schedule a message that will run after a delay
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Set the ProgressBar to be invisible after the delay
                progressBar.setVisibility(View.INVISIBLE);
                scrollView.setVisibility(View.VISIBLE);

            }
        }, 5000);
        return inflater.inflate(R.layout.fragment_home, container, false);
    }


    @Override
    public void setMeal(List<Meal> meals) {

    }

    @Override
    public void setMealpercat(List<Meal> meals) {

    }

    @Override
    public void setMealPerID(ArrayList<RandomMeal> meal) {

    }

    @Override
    public void addmealtofav(MealPojo mealPojo) {

    }

    @Override
    public void addmealtocal(MealPojo mealPojo) {

    }

    @Override
    public void deletemealtofav(MealPojo mealPojo) {


    }

    @Override
    public void deletemealtocal(MealPojo mealPojo) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void setCategory(List<CategoriesM.Category> category) {

    }

    @Override
    public void onErrorLoading(String message) {

    }

    @Override
    public void SetRandomMeal(ArrayList<RandomMeal> meal) {
        Glide.with(this)
                .load(meal.get(0).getStrMealThumb())
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_foreground)
                .into(photoMeal);

        link.setText(meal.get(0).getStrYoutube());

        Linkify.addLinks(link, Linkify.WEB_URLS);
        name.setText(meal.get(0).getStrMeal());
        cata.setText(meal.get(0).getStrCategory());
        area.setText(meal.get(0).getStrArea());
        ingreident.setText(meal.get(0).getStrIngredient1()+" "+meal.get(0).getStrIngredient2()+" "+meal.get(0).getStrIngredient3()+" "+meal.get(0).getStrIngredient4()+" "+meal.get(0).getStrIngredient4()+" "+meal.get(0).getStrIngredient6()
                +" "+meal.get(0).getStrIngredient7()+" "+meal.get(0).getStrIngredient8()+" "+meal.get(0).getStrIngredient9()+" "+meal.get(0).getStrIngredient10()+" "+meal.get(0).getStrIngredient11()
                +" "+meal.get(0).getStrIngredient12()+" "+meal.get(0).getStrIngredient13()+" "+meal.get(0).getStrIngredient14()+" "+meal.get(0).getStrIngredient15()
                +" "+meal.get(0).getStrIngredient16()+" "+meal.get(0).getStrIngredient17()+" "+meal.get(0).getStrIngredient18()+" "+meal.get(0).getStrIngredient19()
                +meal.get(0).getStrIngredient20());
        instruction.setText(meal.get(0).getStrInstructions());
        mealPojo.setIdMeal(Integer.parseInt(meal.get(0).getIdMeal()));

        mealPojo.setStrCategory(meal.get(0).getStrCategory());
        mealPojo.setStrYoutube(meal.get(0).getStrArea());
        mealPojo.setStrMeal(meal.get(0).getStrMeal());
        mealPojo.setStrArea(meal.get(0).getStrArea());
        mealPojo.setStrIngredient1(meal.get(0).getStrIngredient1());
        mealPojo.setStrIngredient2(meal.get(0).getStrIngredient2());
        mealPojo.setStrIngredient3(meal.get(0).getStrIngredient3());
        mealPojo.setStrIngredient4(meal.get(0).getStrIngredient4());
        mealPojo.setStrIngredient5(meal.get(0).getStrIngredient5());
        mealPojo.setStrIngredient6(meal.get(0).getStrIngredient6());
        mealPojo.setStrIngredient7(meal.get(0).getStrIngredient7());
        mealPojo.setStrIngredient8(meal.get(0).getStrIngredient8());
        mealPojo.setStrIngredient9(meal.get(0).getStrIngredient9());






    }

    @Override
    public void SetCountry(List<Country> countries) {

    }


}