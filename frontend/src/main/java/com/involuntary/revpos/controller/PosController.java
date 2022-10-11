package com.involuntary.revpos.controller;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.involuntary.revpos.database.DatabaseConnection;
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
}