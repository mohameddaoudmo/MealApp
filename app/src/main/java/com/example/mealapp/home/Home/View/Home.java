package com.example.mealapp.home.Home.View;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.util.Linkify;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mealapp.R;
import com.example.mealapp.db.ConcreteLocalSource;
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

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        name =view.findViewById(R.id.meal_name);
        cata =view.findViewById(R.id.meal_category);
        area=view.findViewById(R.id.meal_area);
        ingreident =view.findViewById(R.id.meal_ingredients);
        instruction= view.findViewById(R.id.meal_instructions_title);
        mealinstreaction =view.findViewById(R.id.meal_instructions);
        watchintoutube =view.findViewById(R.id.meal_youtube_title);
        link = view.findViewById(R.id.meal_youtube_link);
        photoMeal= view.findViewById(R.id.meal_image);
        presenter = new MainPresenter(this,Repository.getInstance(ConcreteLocalSource.getInstance(getContext()), ApiClient.getInstance(),getContext()));

        presenter.getRandomMeal();

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






    }

    @Override
    public void SetCountry(List<Country> countries) {

    }


}