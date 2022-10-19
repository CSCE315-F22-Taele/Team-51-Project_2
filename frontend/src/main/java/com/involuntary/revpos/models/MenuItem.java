package com.involuntary.revpos.models;

import java.util.ArrayList;

public class MenuItem {

    public MenuItem() {
    }
    public MenuItem(String name) {
        this.name = name;
    }

    private String category;
    private String name;
    private double price;
    private ArrayList<Product> ingredients;
    private ArrayList<Product> options;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public ArrayList<Product> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList<Product> ingredients) {
        this.ingredients = ingredients;
    }

    public ArrayList<Product> getOptions() {
        return options;
    }

    public void setOptions(ArrayList<Product> options) {
        this.options = options;
    }
}
