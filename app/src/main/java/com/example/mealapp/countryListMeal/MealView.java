package com.example.mealapp.countryListMeal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.text.util.Linkify;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mealapp.R;
import com.example.mealapp.db.ConcreteLocalSource;
import com.example.mealapp.db.MealPojo;
import com.example.mealapp.home.Home.View.ViewHome;
import com.example.mealapp.home.Home.presenter.MainPresenter;
import com.example.mealapp.model.CategoriesM;
import com.example.mealapp.model.Country;
import com.example.mealapp.model.Meal;
import com.example.mealapp.model.RandomMeal;
import com.example.mealapp.model.Repository;
import com.example.mealapp.network.ApiClient;

import java.util.ArrayList;
import java.util.List;

public class MealView extends AppCompatActivity implements ViewHome {
    MainPresenter presenter;
    TextView name ;
    TextView cata;
    TextView area ;
    TextView ingreident;
    TextView instruction , mealinstreaction, watchintoutube,link;
    ImageView photoMeal;
    Repository repo ;
    MealPojo mealPojo;
    Button addtofav;
    Button removefromFav;
    LinearLayout linearLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_view);
        mealPojo =new MealPojo();
        name =findViewById(R.id.meal_namedet);
        cata =findViewById(R.id.meal_categorydet);
        area=findViewById(R.id.meal_areadet);
        ingreident =findViewById(R.id.meal_ingredientsdet);
        instruction= findViewById(R.id.meal_instructions_titledet);
        mealinstreaction =findViewById(R.id.meal_instructionsdet);
        watchintoutube =findViewById(R.id.meal_youtube_titledet);
        link = findViewById(R.id.meal_youtube_linkdet);
        photoMeal=findViewById(R.id.meal_imagemealdet);
        addtofav =findViewById(R.id.addtofavviewmeal);
        removefromFav =findViewById(R.id.removefromfavmealview);
        ProgressBar progressBar = findViewById(R.id.prograssbar);
        linearLayout =findViewById(R.id.linerlayoutmealview);

// Set the ProgressBar to be visible
        progressBar.setVisibility(View.VISIBLE);
        linearLayout.setVisibility(View.INVISIBLE);

// Use a Handler to schedule a message that will run after a delay
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Set the ProgressBar to be invisible after the delay
                progressBar.setVisibility(View.INVISIBLE);
                linearLayout.setVisibility(View.VISIBLE);

            }
        }, 3000);


        presenter = new MainPresenter(this, Repository.getInstance(ConcreteLocalSource.getInstance(this), ApiClient.getInstance(),this));
    presenter.getMeal();
    addtofav.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            presenter.addmealtofav(mealPojo);
        }
    });
    removefromFav.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            presenter.removemealtofav(mealPojo);
        }
    });

    }

    @Override
    public void setMeal(List<Meal> meals) {

    }

    @Override
    public void setMealpercat(List<Meal> meals) {

    }

    @Override
    public void setMealPerID(ArrayList<RandomMeal> meal) {
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
        mealPojo.setStrMealThumb(meal.get(0).getStrMealThumb());
        mealPojo.setStrMealThumb(meal.get(0).getStrMealThumb());

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
        mealPojo.setStrIngredient10(meal.get(0).getStrIngredient1());
        mealPojo.setStrIngredient11(meal.get(0).getStrIngredient2());
        mealPojo.setStrIngredient12(meal.get(0).getStrIngredient3());
        mealPojo.setStrIngredient13(meal.get(0).getStrIngredient4());
        mealPojo.setStrIngredient13(meal.get(0).getStrIngredient5());
        mealPojo.setStrIngredient14(meal.get(0).getStrIngredient6());
        mealPojo.setStrIngredient15(meal.get(0).getStrIngredient7());
        mealPojo.setStrIngredient16(meal.get(0).getStrIngredient8());
        mealPojo.setStrIngredient17(meal.get(0).getStrIngredient9());

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

    }

    @Override
    public void SetCountry(List<Country> countries) {

    }
}