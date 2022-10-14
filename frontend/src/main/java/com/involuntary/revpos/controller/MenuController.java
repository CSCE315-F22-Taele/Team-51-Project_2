package com.involuntary.revpos.controller;

import com.involuntary.revpos.database.DatabaseConnection;
import com.involuntary.revpos.models.Product;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class MenuController {

    public double checkPrice(Product product) {
        Connection dbConnection = null;
        Statement statement = null;
        ResultSet result = null;
        double price = product.getPrice();
        try {
            String sql = "SELECT price FROM " + product.getCategory() + " WHERE id = " + product.getId();
            DatabaseConnection connectNow = new DatabaseConnection();
            dbConnection = connectNow.getConnection();
            statement = dbConnection.createStatement();
            result = statement.executeQuery(sql);
            while(result.next()) {
                price = result.getDouble("price");
            }
        } catch (Exception ex) {
            System.err.println(ex.getClass().getName()+": "+ex.getMessage());
            ex.printStackTrace();
        } finally {
            try { if(result != null) result.close(); } catch (Exception e) {};
            try { if(statement != null) statement.close(); } catch (Exception e) {};
            try { if(dbConnection != null) dbConnection.close(); } catch (Exception e) {};
        }
        if(price != product.getPrice()) {
            product.setPrice(price);
        }
        return price;
    }

    public Product pepsi = new Product("drinks", 1000, "pepsi", 1.45);
    public Product dietPepsi = new Product("drinks", 1001, "diet pepsi", 1.45);
    public Product gatorade = new Product("drinks", 1002, "gatorade", 1.45);
    public Product mugRootBeer= new Product("drinks", 1003, "mug rootbeer", 1.45);
    public Product sierraMist = new Product("drinks", 1004, "sierra mist", 1.45);
    public Product brisk = new Product("drinks", 1005, "brisk", 1.45);
    public Product drinkCup = new Product("drinks", 1006, "drink cup", 1);
    public Product chickenFillet = new Product("entrees", 2007, "chicken fillet", 5.49);
    public Product burgerFillet = new Product("entrees", 2008, "burger fillet", 4.49);
    public Product chickenTender = new Product("entrees", 2009, "chicken tender", 1.6);
    public Product blackBeanFillet = new Product("entrees", 2010, "black bean fillet", 5.29);
    public Product bun = new Product("entrees", 2011, "bun", 2.00);
    public Product lettuce = new Product("entrees", 2012, "lettuce", 0);
    public Product tomato = new Product("entrees", 2013, "tomato", 0);
    public Product pickles = new Product("entrees", 2014, "pickles", 0);
    public Product onions = new Product("entrees", 2015, "onions", 0);
    public Product americanCheese = new Product("entrees", 2016, "american cheese", 0.5);
    public Product swissAmericanCheese = new Product("entrees", 2017, "swiss american cheese", 0.6);

    public Product fries = new Product("entrees", 2018, "fries", 2.69);
    public Product bacon = new Product("entrees", 2019, "bacon", 0.9);
    public Product mealTray = new Product("entrees", 2020, "mealtray", 0);
    public Product salt = new Product("entrees", 2021, "salt", 0);
    public Product pepper = new Product("entrees", 2022, "pepper", 0);
    public Product utensils = new Product("entrees", 2023, "utensils", 0);
    public Product napkins = new Product("entrees", 2024, "napkins", 0);
    public Product gigemSauce = new Product("sauces", 3025, "gigem sauce", 0);
    public Product ketchup = new Product("sauces", 3026, "ketchup", 0);
    public Product mustard = new Product("sauces", 3027, "mustard", 0);
    public Product mayo = new Product("sauces", 3028, "mayo", 0);
    public Product ranch = new Product("sauces", 3029, "ranch", 0);
    public Product honeyBBQ = new Product("sauces", 3030, "honey bbq", 0);
    public Product caesarDressing = new Product("sauces", 3031, "caesar dressing", 0);
    public Product chocolateIceCream = new Product("desserts", 4032, "chocolate ice cream", 3.29);
    public Product vanillaIceCream = new Product("desserts", 4033, "vanilla ice cream", 3.29);
    public Product strawberryIceCream = new Product("desserts", 4034, "strawberry ice cream", 3.29);
    public Product cookieSandwich = new Product("desserts", 4035, "cookie sandwich", 4.69);
    public Product dessertCup = new Product("desserts", 4036, "dessert cup", 0);
    public Product dessertBowl = new Product("desserts", 4037, "dessert bowl", 0);
    public Product cookie = new Product("desserts", 4038, "cookie", 0);


}
