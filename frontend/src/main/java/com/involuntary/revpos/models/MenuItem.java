package com.involuntary.revpos.models;

import java.util.ArrayList;

public class MenuItem {
    private ArrayList<Product> ingredients;
    private String name;
    private int price;
    private int calories;

    public MenuItem(ArrayList<Product> ingredients, String name) {
        this.ingredients = ingredients;
        this.name = name;
    }

    public ArrayList<Product> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList<Product> ingredients) {
        this.ingredients = ingredients;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public int getCalories() {
        return calories;
    }

}
