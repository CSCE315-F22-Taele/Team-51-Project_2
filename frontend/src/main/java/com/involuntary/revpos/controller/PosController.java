package com.involuntary.revpos.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import com.ShoppingCart.Cart;
import com.ShoppingCart.CartItem;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

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
    }

    @FXML
    public void addBurgerFilet() {
        HBox entry = new HBox();
        Label label = new Label("Burger Filet");
        entry.getChildren().add(label);
        cart.getChildren().add(entry);
    }

    @FXML
    public void addChickenFilet() {
        HBox entry = new HBox();
        Label label = new Label("Chicken Filet");
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
}