package com.involuntary.revpos.controller;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.involuntary.revpos.database.DatabaseConnection;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import com.ShoppingCart.Cart;
import com.ShoppingCart.CartItem;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

public class PosController {

    @FXML
    private VBox cart;

    @FXML
    public void addBlackBeanFilet() {
        HBox entry = new HBox();
        Label label = new Label("Black Bean Filet");
        entry.getChildren().add(label);
        cart.getChildren().add(entry);

        DatabaseConnection connectNow = new DatabaseConnection();
        Connection dbConnection = connectNow.getConnection();
        String sqlString = "SELECT * FROM entrees";
        try {
            Statement statement = dbConnection.createStatement();
            ResultSet result = statement.executeQuery(sqlString);
            System.out.println("--------------------Query Results--------------------");
            while (result.next()) {
                System.out.println(result.getString("name"));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void addBurgerFilet() {
        HBox entry = new HBox();
        Label label = new Label("Burger Fillet");
        entry.getChildren().add(label);
        cart.getChildren().add(entry);
    }

    @FXML
    public void addChickenFilet() {
        HBox entry = new HBox();
        Label label = new Label("Chicken Fillet");
        entry.getChildren().add(label);
        cart.getChildren().add(entry);
    }

    @FXML
    public void addChickenTenders() {
        HBox entry = new HBox();
        Label label = new Label("Chicken Tenders");
        entry.getChildren().add(label);
        cart.getChildren().add(entry);
    }

    @FXML
    public void addFries() {
        HBox entry = new HBox();
        Label label = new Label("Fries");
        entry.getChildren().add(label);
        cart.getChildren().add(entry);
    }

    @FXML
    public void addBrisk() {
        HBox entry = new HBox();
        Label label = new Label("Brisk");
        entry.getChildren().add(label);
        cart.getChildren().add(entry);
    }

    @FXML
    public void addGatorade() {
        HBox entry = new HBox();
        Label label = new Label("Gatorade");
        entry.getChildren().add(label);
        cart.getChildren().add(entry);
    }

    @FXML
    public void addRootBeer() {
        HBox entry = new HBox();
        Label label = new Label("Root Beer");
        entry.getChildren().add(label);
        cart.getChildren().add(entry);
    }

    @FXML
    public void addPepsi() {
        HBox entry = new HBox();
        Label label = new Label("Pepsi");
        entry.getChildren().add(label);
        cart.getChildren().add(entry);
    }

    @FXML
    public void addSierraMist() {
        HBox entry = new HBox();
        Label label = new Label("Sierra Mist");
        entry.getChildren().add(label);
        cart.getChildren().add(entry);
    }

    @FXML
    public void addCaesarDressing() {
        HBox entry = new HBox();
        Label label = new Label("Caesar Dressing");
        entry.getChildren().add(label);
        cart.getChildren().add(entry);
    }

    @FXML
    public void addGigemSauce() {
        HBox entry = new HBox();
        Label label = new Label("Gigem Sauce");
        entry.getChildren().add(label);
        cart.getChildren().add(entry);
    }

    @FXML
    public void addHoneyBBQ() {
        HBox entry = new HBox();
        Label label = new Label("Honey BBQ");
        entry.getChildren().add(label);
        cart.getChildren().add(entry);
    }

    @FXML
    public void addKetchup() {
        HBox entry = new HBox();
        Label label = new Label("Ketchup");
        entry.getChildren().add(label);
        cart.getChildren().add(entry);
    }

    @FXML
    public void addMayo() {
        HBox entry = new HBox();
        Label label = new Label("Mayo");
        entry.getChildren().add(label);
        cart.getChildren().add(entry);
    }

    @FXML
    public void addMustard() {
        HBox entry = new HBox();
        Label label = new Label("Mustard");
        entry.getChildren().add(label);
        cart.getChildren().add(entry);
    }

    @FXML
    public void addRanch() {
        HBox entry = new HBox();
        Label label = new Label("Ranch");
        entry.getChildren().add(label);
        cart.getChildren().add(entry);
    }

    @FXML
    public void addChocolateIceCream() {
        HBox entry = new HBox();
        Label label = new Label("Chocolate Ice Cream");
        entry.getChildren().add(label);
        cart.getChildren().add(entry);
    }

    @FXML
    public void addCookie() {
        HBox entry = new HBox();
        Label label = new Label("Cookie");
        entry.getChildren().add(label);
        cart.getChildren().add(entry);
    }

    @FXML
    public void addCookieSandwich() {
        HBox entry = new HBox();
        Label label = new Label("Cookie Sandwich");
        entry.getChildren().add(label);
        cart.getChildren().add(entry);
    }

    @FXML
    public void addDessertBowl() {
        HBox entry = new HBox();
        Label label = new Label("Dessert Bowl");
        entry.getChildren().add(label);
        cart.getChildren().add(entry);
    }

    @FXML
    public void addDessertCup() {
        HBox entry = new HBox();
        Label label = new Label("Dessert Cup");
        entry.getChildren().add(label);
        cart.getChildren().add(entry);
    }

    @FXML
    public void addStrawberryIceCream() {
        HBox entry = new HBox();
        Label label = new Label("Strawberry Ice Cream");
        entry.getChildren().add(label);
        cart.getChildren().add(entry);
    }

    @FXML
    public void addVanillaIceCream() {
        HBox entry = new HBox();
        Label label = new Label("Vanilla Ice Cream");
        entry.getChildren().add(label);
        cart.getChildren().add(entry);
    }

    public void openSettings(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/views/settings.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = ((Node) event.getSource()).getScene();
        scene.setRoot(root);
    }
}