package com.example.mealapp.home.Home.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mealapp.R;
import com.example.mealapp.db.ConcreteLocalSource;
import com.example.mealapp.fav.FavActivity;
import com.example.mealapp.home.Home.presenter.MainPresenter;
import com.example.mealapp.model.CategoriesM;
import com.example.mealapp.model.Country;
import com.example.mealapp.model.Meal;
import com.example.mealapp.model.RandomMeal;
import com.example.mealapp.model.Repository;
import com.example.mealapp.network.ApiClient;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity{

    RecyclerView categroy;
    CatgorysAdapter adapter;
    MainPresenter presenter;
    Repository repo ;
    ImageView photoFormeal;
    TextView textViewformeal;
    List<CategoriesM.Category> categories;
    NavController navController;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    String data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);



        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.ic_baseline_menu_24);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        NavController navController = Navigation.
                findNavController(this,R.id.nav_host_fragment);
        NavigationUI.setupWithNavController(navigationView , navController);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        Intent intent = new Intent(MainActivity.this, FavActivity.class);
                        startActivity(intent);


                        return true;
                    case R.id.navigation_dashboard:
                        // Handle Dashboard navigation
                        return true;

                }
                return false;
            }
        });

    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == android.R.id.home)
        {
            if (drawerLayout.isDrawerOpen(GravityCompat.START)){
                drawerLayout.closeDrawer(GravityCompat.START);
            }else {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        }
        return super.onOptionsItemSelected(item);

    }



}