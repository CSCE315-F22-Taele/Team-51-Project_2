package com.involuntary.revpos.controller;

import java.io.IOException;

import com.involuntary.revpos.database.DatabaseConnection;
import com.involuntary.revpos.models.Product;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.Statement;
import java.util.HashMap;

public class PosController extends MenuController {

    @FXML
    private VBox cart;
    @FXML
    private Button checkoutBtn;

    HashMap<String, Product> cartProducts = new HashMap<>();
    HashMap<Product, Integer> currentCart = new HashMap<>();
    public double cartTotal = 0;

    public void addItemToCart(Product product) {
        Integer count = currentCart.containsKey(product) ? currentCart.get(product) : 0;
        currentCart.put(product, count + 1);
        cartTotal += checkPrice(product) * 1.0625;
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
        checkoutBtn.setText("CHARGE $" + cartTotal);
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
        checkoutBtn.setText("CHARGE $" + cartTotal);
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
        checkoutBtn.setText("CHARGE $" + cartTotal);
    }

    @FXML
    public void addChickenTenders() {
        addItemToCart(chickenTender);
        addItemToCart(chickenTender);
        addItemToCart(chickenTender);
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
        checkoutBtn.setText("CHARGE $" + cartTotal);
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
        checkoutBtn.setText("CHARGE $" + cartTotal);
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
        checkoutBtn.setText("CHARGE $" + cartTotal);
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
        checkoutBtn.setText("CHARGE $" + cartTotal);
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
        checkoutBtn.setText("CHARGE $" + cartTotal);
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
        checkoutBtn.setText("CHARGE $" + cartTotal);
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
        checkoutBtn.setText("CHARGE $" + cartTotal);
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
        checkoutBtn.setText("CHARGE $" + cartTotal);
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
        checkoutBtn.setText("CHARGE $" + cartTotal);
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
        checkoutBtn.setText("CHARGE $" + cartTotal);
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
        if (!cartProducts.containsKey("strawberryicecream")) {
            cartProducts.put("strawberryicecream", new Product(1034, "strawberry", 3.29, 342, 1));
        } else {
            cartProducts.put("strawberryicecream", new Product(1034, "strawberry", 3.29, 342, cartProducts.get("strawberryicecream").getQuantity()));
        }

        addDessertCup();
        HBox entry = new HBox();
        Label label = new Label("Strawberry Ice Cream x" + cartProducts.get("strawberryicecream").getQuantity());
        entry.getChildren().add(label);
        cart.getChildren().add(entry);
        Separator separator = new Separator();
        cart.getChildren().add(separator);
        checkoutBtn.setText("CHARGE $" + cartTotal);
    }

    @FXML
    public void addVanillaIceCream() {
        if (!cartProducts.containsKey("vanillaicecream")) {
            cartProducts.put("vanillaicecream", new Product(1034, "vanilla", 3.29, 342, 1));
        } else {
            cartProducts.put("vanillaicecream", new Product(1034, "vanilla", 3.29, 342, cartProducts.get("vanillaicecream").getQuantity()));
        }

        addDessertCup();
        HBox entry = new HBox();
        Label label = new Label("Vanilla Ice Cream");
        entry.getChildren().add(label);
        cart.getChildren().add(entry);
        Separator separator = new Separator();
        cart.getChildren().add(separator);
        checkoutBtn.setText("CHARGE $" + cartTotal);
    }

    public void openSettings(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/views/settings.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = ((Node) event.getSource()).getScene();
        scene.setRoot(root);
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


    public void checkout() {
        for (Product item : currentCart.keySet()) {
            if(updateCall(item.getCategory(), String.valueOf(item.getId()), currentCart.get(item))) {
                System.out.println("UPDATED");
            } else {
                System.out.println("FAILED UPDATE");
            }
        }

        cart.getChildren().clear();
        currentCart.clear();
        cartTotal = 0.00;
        checkoutBtn.setText("CHARGE $" + cartTotal);
    }
}