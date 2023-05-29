package com.example.mealapp.model;

public class Ingredient {
    private String name;
    private String amount;
    private String thumbnail;

    public Ingredient(String name, String amount, String thumbnail) {
        this.name = name;
        this.amount = amount;
        this.thumbnail = thumbnail;
    }

    public Ingredient(String name, String amount) {
        this.name = name;
        this.amount = amount;
    }

    public Ingredient() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }
}
