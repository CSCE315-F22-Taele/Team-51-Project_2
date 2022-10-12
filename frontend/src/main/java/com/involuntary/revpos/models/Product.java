package com.involuntary.revpos.models;

public class Product {
    private int id;
    private String name;
    private double price;
    private int calories;
    private int quantity;

    public Product(int id, String name, double price, int calories, int quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.calories = calories;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}