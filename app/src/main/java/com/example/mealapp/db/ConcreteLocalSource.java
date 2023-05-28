package com.example.mealapp.db;

import android.content.Context;

//import androidx.lifecycle.LiveData;


import java.util.List;

public class ConcreteLocalSource implements LocalSource{

    private ProductDAO productDAO;
    private static ConcreteLocalSource concreteLocalSource = null;
    private ConcreteLocalSource(Context context){
//        productDAO = ProductDatabase.getInstance(context.getApplicationContext()).productDAO();
    }

    public static synchronized ConcreteLocalSource getInstance(Context context){

        if (concreteLocalSource == null){
            concreteLocalSource = new ConcreteLocalSource(context);
        }
        return concreteLocalSource;
    }

//    @Override
//    public void insert(Product product) {
//        new Thread(){
//            public void run(){
//                productDAO.insertProduct(product);
//            }
//        }.start();
//    }
//
//    @Override
//    public void delete(Product product) {
//        new Thread(){
//            public void run(){
//                productDAO.deleteProduct(product);
//            }
//        }.start();
//    }
//
//    @Override
//    public LiveData<List<Product>> getCachedProducts() {
//        return productDAO.getAllProducts();
//    }
}
