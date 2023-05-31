package com.example.mealapp.calander;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import com.example.mealapp.R;
import com.example.mealapp.db.ConcreteLocalSource;
import com.example.mealapp.db.MealPojo;
import com.example.mealapp.db.POJOmealPerCalander;
import com.example.mealapp.fav.FavActivity;
import com.example.mealapp.fav.FavAdapter;
import com.example.mealapp.fav.FavPresenter;
import com.example.mealapp.model.Meal;
import com.example.mealapp.model.Repository;
import com.example.mealapp.network.ApiClient;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CalanderActivity extends AppCompatActivity implements OnclickListnerday ,MealPerCalanderAdapter.OnClickListener {

    TextView monthYearTV;
    GridView calendarGrid;
    PresenterCalander presenterCalander;
    RecyclerView recyclerViewcal ;
    MealPerCalanderAdapter adapter;
    List<POJOmealPerCalander> perCalanders;
    int day ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calander);
        perCalanders =new ArrayList<>();
        recyclerViewcal = findViewById(R.id.reccalander);
        recyclerViewcal.setHasFixedSize(true);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerViewcal.setLayoutManager(manager);
        adapter = new MealPerCalanderAdapter(CalanderActivity.this,perCalanders,CalanderActivity.this,1);
        recyclerViewcal.setAdapter(adapter);
        presenterCalander = new PresenterCalander( Repository.getInstance(ConcreteLocalSource.getInstance(this), ApiClient.getInstance(), this));
        presenterCalander.getFromCalander(day).observe(this, new Observer<List<POJOmealPerCalander>>() {
            @Override
            public void onChanged(List<POJOmealPerCalander> products) {
                adapter.setList(products);
            }
        });

        // Get TextView and GridView
        monthYearTV = findViewById(R.id.month_year_tv);
        calendarGrid = findViewById(R.id.calendar_grid);

        // Set month and year on TextView
        monthYearTV.setText(getMonthYear());

        // Create an adapter for the calendar grid
        CalanderAdapter adapter = new CalanderAdapter(this,this);
        // Set the adapter on the grid
        calendarGrid.setAdapter(adapter);
    }

    private String getMonthYear() {
        // Get calendar, month, and year
        Calendar calendar = Calendar.getInstance();
        int month = calendar.get(Calendar.MONTH) + 1;
        int year = calendar.get(Calendar.YEAR);
        // Return formatted string
        return month + " " + year;
    }


    @Override
    public void onDayClick(int dayOfMonth) {
        presenterCalander.sendDay(dayOfMonth);
        this.day = dayOfMonth;


    }

    @Override
    public void onClick(POJOmealPerCalander meal) {

    }

    @Override
    public void onClickFav(Meal meal) {

    }

    @Override
    public void onClickCalanender(Meal meal) {

    }
}