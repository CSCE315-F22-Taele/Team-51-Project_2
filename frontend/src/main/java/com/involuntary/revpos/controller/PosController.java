package com.involuntary.revpos.controller;

import java.io.IOException;

import com.involuntary.revpos.database.DatabaseConnection;
import com.involuntary.revpos.models.Product;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.sql.Connection;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.HashMap;

public class PosController extends MenuController {

    @FXML
    private VBox cart;
    @FXML
    private Button checkoutBtn;
    @FXML
    private ImageView openSettingsBtn;

    HashMap<Product, Integer> currentCart = new HashMap<>();
    private double cartTotal = 0;
    private static DecimalFormat df2 = new DecimalFormat("#.00");

    @FXML
    public void removeBun() {
        if(currentCart.containsKey(bun)) {
            if (currentCart.get(bun) <= 1) {
                currentCart.remove(bun);
            System.out.println("Bun removed");
            } else {
                currentCart.put(bun, currentCart.get(bun)-1);
            }
        } else {
            System.out.println("No buns to be removed");
        }
    }
    @FXML
    public void removeLettuce() {
        if(currentCart.containsKey(lettuce)) {
            currentCart.remove(lettuce);
            if (currentCart.get(lettuce) <= 1) {
                currentCart.remove(lettuce);
            } else {
                currentCart.put(lettuce, currentCart.get(lettuce)-1);
            }
        }
    }
    @FXML
    public void removeTomato() {
        if(currentCart.containsKey(tomato)) {
            if (currentCart.get(tomato) <= 1) {
                currentCart.remove(tomato);
            } else {
                currentCart.put(tomato, currentCart.get(tomato)-1);
            }
        }
    }
    @FXML
    public void removePickles() {
        if(currentCart.containsKey(pickles)) {
            if (currentCart.get(pickles) <= 1) {
                currentCart.remove(pickles);
            } else {
                currentCart.put(pickles, currentCart.get(pickles)-1);
            }
        }
    }

    @FXML
    public void removeOnion() {
        if(currentCart.containsKey(onions)) {
            if (currentCart.get(onions) <= 1) {
                currentCart.remove(onions);
            } else {
                currentCart.put(onions, currentCart.get(onions)-1);
            }
        }
    }
    @FXML
    public void addBlackBeanBurger() {
        addItemToCart(blackBeanFillet);
        addItemToCart(bun);
        addItemToCart(lettuce);
        addItemToCart(tomato);
        addItemToCart(pickles);
        addItemToCart(mealTray);
        addItemToCart(salt);
        addItemToCart(pepper);
        addItemToCart(utensils);
        addItemToCart(napkins);
        addMayo();
        addKetchup();
        addMustard();

        HBox entry = new HBox();
        Label label = new Label("Black Bean Burger");
        entry.getChildren().add(label);
        cart.getChildren().add(entry);
        Separator separator = new Separator();
        cart.getChildren().add(separator);
        computePrice();
    }

    @FXML
    public void addClassicHamburger() {
        addItemToCart(burgerFillet);
        addItemToCart(bun);
        addItemToCart(lettuce);
        addItemToCart(tomato);
        addItemToCart(pickles);
        addItemToCart(mealTray);
        addItemToCart(salt);
        addItemToCart(pepper);
        addItemToCart(utensils);
        addItemToCart(napkins);
        addMayo();
        addKetchup();
        addMustard();

        HBox entry = new HBox();
        Label label = new Label("Burger");
        entry.getChildren().add(label);
        cart.getChildren().add(entry);
        Separator separator = new Separator();
        cart.getChildren().add(separator);
        computePrice();
    }


    @FXML
    public void addChickenSandwich() {

        addItemToCart(chickenFillet);
        addItemToCart(bun);
        addItemToCart(lettuce);
        addItemToCart(tomato);
        addItemToCart(pickles);
        addItemToCart(mealTray);
        addItemToCart(salt);
        addItemToCart(pepper);
        addItemToCart(utensils);
        addItemToCart(napkins);
        addMayo();
        addKetchup();
        addMustard();

        HBox entry = new HBox();
        Label label = new Label("Chicken Burger");
        entry.getChildren().add(label);
        cart.getChildren().add(entry);
        Separator separator = new Separator();
        cart.getChildren().add(separator);
        computePrice();
    }

    @FXML
    public void addChickenTenders() {
        addItemToCart(chickenTender);
        addItemToCart(chickenTender);
        addItemToCart(chickenTender);
        addFries();
        addItemToCart(mealTray);
        addItemToCart(salt);
        addItemToCart(pepper);
        addItemToCart(utensils);
        addItemToCart(napkins);
        addKetchup();

        HBox entry = new HBox();
        Label label = new Label("Chicken Tenders");
        entry.getChildren().add(label);
        cart.getChildren().add(entry);
        Separator separator = new Separator();
        cart.getChildren().add(separator);
        computePrice();
    }

    @FXML
    public void addFries() {
        addItemToCart(fries);
        addItemToCart(mealTray);
        addItemToCart(salt);
        addItemToCart(pepper);
        addItemToCart(utensils);
        addItemToCart(napkins);

        HBox entry = new HBox();
        Label label = new Label("Fries");
        entry.getChildren().add(label);
        cart.getChildren().add(entry);
        Separator separator = new Separator();
        cart.getChildren().add(separator);
        computePrice();
    }

    @FXML
    public void addBrisk() {
        addItemToCart(drinkCup);
        addItemToCart(brisk);

        HBox entry = new HBox();
        Label label = new Label("Brisk");
        entry.getChildren().add(label);
        cart.getChildren().add(entry);
        Separator separator = new Separator();
        cart.getChildren().add(separator);
        computePrice();
    }

    @FXML
    public void addGatorade() {
        addItemToCart(drinkCup);
        addItemToCart(gatorade);

        HBox entry = new HBox();
        Label label = new Label("Gatorade");
        entry.getChildren().add(label);
        cart.getChildren().add(entry);
        Separator separator = new Separator();
        cart.getChildren().add(separator);
        computePrice();
    }

    @FXML
    public void addRootBeer() {
        addItemToCart(drinkCup);
        addItemToCart(mugRootBeer);

        HBox entry = new HBox();
        Label label = new Label("Root Beer");
        entry.getChildren().add(label);
        cart.getChildren().add(entry);
        Separator separator = new Separator();
        cart.getChildren().add(separator);
        computePrice();
    }

    @FXML
    public void addPepsi() {
        addItemToCart(drinkCup);
        addItemToCart(pepsi);

        HBox entry = new HBox();
        Label label = new Label("Pepsi");
        entry.getChildren().add(label);
        cart.getChildren().add(entry);
        Separator separator = new Separator();
        cart.getChildren().add(separator);
        computePrice();
    }

    @FXML
    public void addDietPepsi() {
        addItemToCart(drinkCup);
        addItemToCart(dietPepsi);

        HBox entry = new HBox();
        Label label = new Label("Diet Pepsi");
        entry.getChildren().add(label);
        cart.getChildren().add(entry);
        Separator separator = new Separator();
        cart.getChildren().add(separator);
        computePrice();
    }

    @FXML
    public void addSierraMist() {
        addItemToCart(drinkCup);
        addItemToCart(sierraMist);

        HBox entry = new HBox();
        Label label = new Label("Sierra Mist");
        entry.getChildren().add(label);
        cart.getChildren().add(entry);
        Separator separator = new Separator();
        cart.getChildren().add(separator);
        computePrice();
    }

    @FXML
    public void addCaesarDressing() {
        addItemToCart(caesarDressing);

        HBox entry = new HBox();
        Label label = new Label("Caesar Dressing");
        entry.getChildren().add(label);
        cart.getChildren().add(entry);
    }

    @FXML
    public void addGigemSauce() {
        addItemToCart(gigemSauce);

        HBox entry = new HBox();
        Label label = new Label("Gigem Sauce");
        entry.getChildren().add(label);
        cart.getChildren().add(entry);
    }

    @FXML
    public void addHoneyBBQ() {
        addItemToCart(honeyBBQ);

        HBox entry = new HBox();
        Label label = new Label("Honey BBQ");
        entry.getChildren().add(label);
        cart.getChildren().add(entry);
    }

    @FXML
    public void addKetchup() {
        addItemToCart(ketchup);

        HBox entry = new HBox();
        Label label = new Label("Ketchup");
        entry.getChildren().add(label);
        cart.getChildren().add(entry);
    }

    @FXML
    public void addMayo() {
        addItemToCart(mayo);

        HBox entry = new HBox();
        Label label = new Label("Mayo");
        entry.getChildren().add(label);
        cart.getChildren().add(entry);
    }

    @FXML
    public void addMustard() {
        addItemToCart(mustard);

        HBox entry = new HBox();
        Label label = new Label("Mustard");
        entry.getChildren().add(label);
        cart.getChildren().add(entry);
    }

    @FXML
    public void addRanch() {
        addItemToCart(ranch);

        HBox entry = new HBox();
        Label label = new Label("Ranch");
        entry.getChildren().add(label);
        cart.getChildren().add(entry);
    }

    @FXML
    public void addChocolateIceCream() {
        addItemToCart(chocolateIceCream);
        addDessertBowl();
        addItemToCart(napkins);
        addItemToCart(utensils);

        HBox entry = new HBox();
        Label label = new Label("Chocolate Ice Cream");
        entry.getChildren().add(label);
        cart.getChildren().add(entry);
        Separator separator = new Separator();
        cart.getChildren().add(separator);
        computePrice();
    }

    @FXML
    public void addCookie() {
        addItemToCart(cookie);
    }

    @FXML
    public void addCookieSandwich() {
        addItemToCart(cookieSandwich);

        HBox entry = new HBox();
        Label label = new Label("Cookie Sandwich");
        entry.getChildren().add(label);
        cart.getChildren().add(entry);
        Separator separator = new Separator();
        cart.getChildren().add(separator);
        computePrice();
    }

    @FXML
    public void addDessertBowl() {
        addItemToCart(dessertBowl);
    }

    @FXML
    public void addDessertCup() {
        addItemToCart(dessertCup);
    }

    @FXML
    public void addStrawberryIceCream() {

        addDessertCup();
        HBox entry = new HBox();
        Label label = new Label("Strawberry Ice Cream");
        entry.getChildren().add(label);
        cart.getChildren().add(entry);
        Separator separator = new Separator();
        cart.getChildren().add(separator);
        computePrice();
    }

    @FXML
    public void addVanillaIceCream() {

        addDessertCup();
        HBox entry = new HBox();
        Label label = new Label("Vanilla Ice Cream");
        entry.getChildren().add(label);
        cart.getChildren().add(entry);
        Separator separator = new Separator();
        cart.getChildren().add(separator);
        computePrice();
    }

    public void addItemToCart(Product product) {
        Integer count = currentCart.containsKey(product) ? currentCart.get(product) : 0;
        currentCart.put(product, count + 1);
        cartTotal += checkPrice(product) * 1.0625;
    }

    public boolean updateCall(String category, String id, int quantity) {
        Connection dbConnection = null;
        Statement statement = null;
        try {
            String sql = "UPDATE " + category + " SET inventory = inventory - " + quantity + " WHERE id = " + id;
            DatabaseConnection connectNow = new DatabaseConnection();
            dbConnection = connectNow.getConnection();

            statement = dbConnection.createStatement();
            int callStatus = statement.executeUpdate(sql);
            if(callStatus != 1) {
                return false;
            }
        } catch (Exception ex) {
            System.err.println(ex.getClass().getName()+": "+ex.getMessage());
            ex.printStackTrace();
            return false;
        } finally {
            try { if(statement != null) statement.close(); } catch (Exception e) {};
            try { if(dbConnection != null) dbConnection.close(); } catch (Exception e) {};
        }
        return true;
    }

    public void computePrice() {
        checkoutBtn.setText("CHARGE $" + df2.format(cartTotal));
    }
    public void checkout() {
        for (Product item : currentCart.keySet()) {
            updateCall(item.getCategory(), String.valueOf(item.getId()), currentCart.get(item));
        }

        cart.getChildren().clear();
        currentCart.clear();
        cartTotal = 0.00;
        checkoutBtn.setText("CHARGE $" + df2.format(cartTotal));
    }
    public void openSettings() throws IOException {
        Scene scene = openSettingsBtn.getScene();
        LoginController.openModal(scene);
    }
}