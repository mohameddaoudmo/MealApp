package com.example.mealapp.countryListMeal;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
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
import com.example.mealapp.db.POJOmealPerCalander;
import com.example.mealapp.home.Home.View.ViewHome;
import com.example.mealapp.home.Home.presenter.MainPresenter;
import com.example.mealapp.model.CategoriesM;
import com.example.mealapp.model.Country;
import com.example.mealapp.model.Meal;
import com.example.mealapp.model.RandomMeal;
import com.example.mealapp.model.Repository;
import com.example.mealapp.network.ApiClient;

import java.util.ArrayList;
import java.util.Calendar;
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
    Button addtocal;
    Button removefromcal;
    int day;
    int year;

    int mounth ;
    RandomMeal randomMeal;
    POJOmealPerCalander pojOmealPerCalander;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_view);
        mealPojo =new MealPojo();
        name =findViewById(R.id.meal_namedet);
        cata =findViewById(R.id.meal_categorydet);
        area=findViewById(R.id.meal_areadet);
        pojOmealPerCalander = new POJOmealPerCalander();
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
        addtocal = findViewById(R.id.addtocalander);
        removefromcal =findViewById(R.id.removefromcalander) ;

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
    addtocal.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            showDatePickerDialog();
        }
    });
    removefromcal.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            presenter.removemealtocal(pojOmealPerCalander);
        }
    });
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
        this.randomMeal = meal.get(0);

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


        pojOmealPerCalander.setIdMeal(Integer.parseInt(meal.get(0).getIdMeal()));
        pojOmealPerCalander.setStrMealThumb(meal.get(0).getStrMealThumb());
        pojOmealPerCalander.setStrMealThumb(meal.get(0).getStrMealThumb());

        pojOmealPerCalander.setStrCategory(meal.get(0).getStrCategory());
        pojOmealPerCalander.setStrYoutube(meal.get(0).getStrArea());
        pojOmealPerCalander.setStrMeal(meal.get(0).getStrMeal());

        pojOmealPerCalander.setStrArea(meal.get(0).getStrArea());
        pojOmealPerCalander.setStrIngredient1(meal.get(0).getStrIngredient1());
        pojOmealPerCalander.setStrIngredient2(meal.get(0).getStrIngredient2());
        pojOmealPerCalander.setStrIngredient3(meal.get(0).getStrIngredient3());
        pojOmealPerCalander.setStrIngredient4(meal.get(0).getStrIngredient4());
        pojOmealPerCalander.setStrIngredient5(meal.get(0).getStrIngredient5());
        pojOmealPerCalander.setStrIngredient6(meal.get(0).getStrIngredient6());
        pojOmealPerCalander.setStrIngredient7(meal.get(0).getStrIngredient7());
        pojOmealPerCalander.setStrIngredient8(meal.get(0).getStrIngredient8());
        pojOmealPerCalander.setStrIngredient9(meal.get(0).getStrIngredient9());
        pojOmealPerCalander.setStrIngredient10(meal.get(0).getStrIngredient1());
        pojOmealPerCalander.setStrIngredient11(meal.get(0).getStrIngredient2());
        pojOmealPerCalander.setStrIngredient12(meal.get(0).getStrIngredient3());
        pojOmealPerCalander.setStrIngredient13(meal.get(0).getStrIngredient4());
        pojOmealPerCalander.setStrIngredient13(meal.get(0).getStrIngredient5());
        pojOmealPerCalander.setStrIngredient14(meal.get(0).getStrIngredient6());
        pojOmealPerCalander.setStrIngredient15(meal.get(0).getStrIngredient7());
        pojOmealPerCalander.setStrIngredient16(meal.get(0).getStrIngredient8());
        pojOmealPerCalander.setStrIngredient17(meal.get(0).getStrIngredient9());
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
    private void showDatePickerDialog() {
        // Get the current date
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_WEEK);

        // Create a new DatePickerDialog
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, null, year, month, day);

        // Set the title and showthe spinners for the day of the week
        datePickerDialog.setTitle("Select a day of the week");
        datePickerDialog.getDatePicker().setCalendarViewShown(false);
        datePickerDialog.getDatePicker().setSpinnersShown(true);

        // Set the callback to handle the selected date
        datePickerDialog.setButton(DialogInterface.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Get the selected year, month, and day
                int selectedYear = datePickerDialog.getDatePicker().getYear();
                int selectedMonth = datePickerDialog.getDatePicker().getMonth();
                int selectedDayOfMonth = datePickerDialog.getDatePicker().getDayOfMonth();
                // Create a new Calendar instance and set the selected date
                Calendar selectedDate = Calendar.getInstance();
                selectedDate.set(selectedYear, selectedMonth, selectedDayOfMonth);

                // Get the day of the week as an integer value
                int dayOfWeek = selectedDate.get(Calendar.DAY_OF_WEEK);
                 mounth = selectedDate.get(Calendar.DATE);
                System.out.println(mounth);
                pojOmealPerCalander.setDay(mounth);
                presenter.addmealtocal(pojOmealPerCalander);


            }
        });

        // Show the dialog
        datePickerDialog.show();
    }
}