package com.involuntary.revpos.models;

/**
 *
 * @author Johnny Le
 *
 */
public class Product {
    private String category;
    private int id;
    private String name;
    private double price;
    private int calories;
    private int quantity;

    public Product(int id) {
        this.id = id;
    }
    /**
     * Creates a product object given the params
     *
     * @param id id of the product to be assigned
     * @param name name of the product to be assigned
     * @param price price of the product to be assigned
     *
     * @return a new Product object
     */
    public Product(String category, int id, String name, double price) {
        this.category = category;
        this.id = id;
        this.name = name;
        this.price = price;
    }
    /**
     * Creates a product object given the params
     *
     * @param id id of the product to be assigned
     * @param name name of the product to be assigned
     * @param price price of the product to be assigned
     * @param calories calories count of the product
     * @param quantity quantity of the product in stock
     *
     * @return a new Product object
     */
    public Product(int id, String name, double price, int calories, int quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.calories = calories;
        this.quantity = quantity;
    }

    /**
     * @return category of a product
     */
    public String getCategory() {
        return category;
    }
    /**
     * @param category category to set (string)
     */
    public void setCategory(String category) {
        this.category = category;
    }
    /**
     * @return id of a product
     */
    public int getId() {
        return id;
    }
    /**
     * @param id id to set (integer)
     */
    public void setId(int id) {
        this.id = id;
    }
    /**
     * @return name of a product
     */
    public String getName() {
        return name;
    }
    /**
     * @param name name to set (string)
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * @return price of a product
     */
    public double getPrice() {
        return price;
    }
    /**
     * @param price price to set (double)
     */
    public void setPrice(double price) {
        this.price = price;
    }
    /**
     * @return calories of a product
     */
    public int getCalories() {
        return calories;
    }
    /**
     * @param calories calories to set (integer)
     */
    public void setCalories(int calories) {
        this.calories = calories;
    }
    /**
     * @return quantity of the product
     */
    public int getQuantity() {
        return quantity;
    }
    /**
     * @param quantity quantity to set (integer)
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public int hashCode() {
        final int temp = 14;
        int ans = 1;
        ans = temp * ans + id;
        return ans;
    }
    @Override
    public boolean equals(Object o) {
        if(this == o) {
            return true;
        }
        if(o == null) {
            return false;
        }
        if(this.getClass() != o.getClass()) {
            return false;
        }
        Product other = (Product) o;
        if(this.id != other.id) {
            return false;
        }
        return true;
    }
}