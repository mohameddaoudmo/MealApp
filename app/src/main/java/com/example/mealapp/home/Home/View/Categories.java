package com.example.mealapp.home.Home.View;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mealapp.R;
//import com.example.mealapp.countryListMeal.MealalperCata;
import com.example.mealapp.countryListMeal.MealperCata;
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


public class Categories extends Fragment implements ViewHome, CatgorysAdapter.OnClickListener {
    RecyclerView categroy;
    CatgorysAdapter adapter;
    MainPresenter presenter;
    Repository repo;
    String data ;

    List<CategoriesM.Category> categories;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        System.out.println("data from cat"+data);
        super.onViewCreated(view, savedInstanceState);
        categories = new ArrayList<>();
        categroy = view.findViewById(R.id.catrecyele);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        categroy.setLayoutManager(manager);
        adapter = new CatgorysAdapter(getContext(), categories, this, 0);
        categroy.setAdapter(adapter);
        presenter = new MainPresenter(this, Repository.getInstance(ConcreteLocalSource.getInstance(getContext()), ApiClient.getInstance(), getContext()));
        presenter.getcategory();
    }

    public Categories() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_categories, container, false);
    }

    @Override
    public void onClick(CategoriesM.Category category) {
        Intent intent = new Intent(getActivity(), MealperCata.class);
        intent.putExtra("country_name"," ");
        startActivity(intent);
        ApiClient apiClient = ApiClient.getInstance();
        apiClient.senddataforCat(category.getStrCategory());
    }


    @Override
    public void setMeal(List<Meal> meals) {

    }

    @Override
    public void setMealpercat(List<Meal> meals) {

    }


    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void setCategory(List<CategoriesM.Category> category) {
        adapter.setList(category);
        System.out.println("CCCaaaaaaaattttttttgory"+category.get(0).getStrCategory());


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