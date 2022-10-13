package com.involuntary.revpos.models;

import java.util.ArrayList;

public class MenuItem {
    private ArrayList<Product> ingredients;
    private int name;
    private int price;
    private int calories;

    public MenuItem(ArrayList<Product> ingredients, int name) {
        this.ingredients = ingredients;
        this.name = name;
    }

    public ArrayList<Product> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList<Product> ingredients) {
        this.ingredients = ingredients;
    }

    public int getName() {
        return name;
    }

    public void setName(int name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public int getCalories() {
        return calories;
    }

}
