package com.example.mealapp.home.Home.View;

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
import com.example.mealapp.db.ConcreteLocalSource;
import com.example.mealapp.home.Home.presenter.MainPresenter;
import com.example.mealapp.model.CategoriesM;
import com.example.mealapp.model.Country;
import com.example.mealapp.model.Meal;
import com.example.mealapp.model.Repository;
import com.example.mealapp.network.ApiClient;

import java.util.ArrayList;
import java.util.List;


public class Countries extends Fragment implements ViewHome, CountryAdapter.OnClickListener  {
    RecyclerView countryrec;
    CountryAdapter adapter;
    MainPresenter presenter;
    Repository repo;
    Country country;

    List<Country> countries;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        countries = new ArrayList<>();
        countryrec = view.findViewById(R.id.countryrecy);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        countryrec.setLayoutManager(manager);
        adapter = new CountryAdapter(getContext(), countries, this, 0);
        countryrec.setAdapter(adapter);
        presenter = new MainPresenter(this, Repository.getInstance(ConcreteLocalSource.getInstance(getContext()), ApiClient.getInstance(), getContext()));
        presenter.getCountry();
        System.out.println(country);


    }

    public Countries() {
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
        return inflater.inflate(R.layout.fragment_countries, container, false);
    }

    @Override
    public void onClick(Country country) {
        this.country =country;


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
    public void SetMeal(ArrayList<Meal> meal) {

    }

    @Override
    public void SetCountry(List<Country> countries) {
        adapter.setList(countries);


    }
}