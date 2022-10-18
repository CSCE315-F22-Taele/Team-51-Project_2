package com.involuntary.revpos.controller;

import com.involuntary.revpos.database.DatabaseConnection;
import com.involuntary.revpos.models.MenuItem;
import com.involuntary.revpos.models.Product;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import com.involuntary.revpos.listeners.MyListener;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class MenuItemController extends ManagerController {

    @FXML
    private Label nameLabel;
    @FXML
    private Label statusLabel;

    private MenuItem menuItem;
    private MyListener myListener;

    public MenuItemController() {
    }

    @FXML
    private void click(MouseEvent mouseEvent) throws IOException {
        this.myListener.onClickListener(this.menuItem);
    }

    public void setData(MenuItem menuItem, MyListener myListener) {
        try {
            this.menuItem = menuItem;
            this.myListener = myListener;
            this.nameLabel.setText(menuItem.getName());
        } catch (Exception e) {
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            e.printStackTrace(); }
    }


    @FXML
    private ComboBox menuItemCategoryField;
    @FXML
    private TextField menuItemNameField;
    @FXML
    private TextField menuItemPriceField;
    @FXML
    private TextField menuItemIngredientsField;

    public void addMenuItem(ActionEvent event) {
        Connection dbConnection = null;
        Statement statement = null;
        int result = 0;

        try {
            DatabaseConnection connectNow = new DatabaseConnection();
            dbConnection = connectNow.getConnection();
            if(dbConnection == null) {
                statusLabel.setText("Failed to connect to database...");
                return;
            }

            String sql = "INSERT INTO menu VALUES ('" + menuItemCategoryField.getValue() + "', '" + menuItemNameField.getText() + "', " + menuItemPriceField.getText() + ", '" + menuItemIngredientsField.getText() + "')";
            statement = dbConnection.createStatement();
            result = statement.executeUpdate(sql);
            if(result == 1) {
                statusLabel.setText("Good job!");
            }
        } catch (Exception error) {
            error.printStackTrace();
        } finally {
            try { if(statement != null) statement.close(); } catch (Exception e) {};
            try { if(dbConnection != null) dbConnection.close(); } catch (Exception e) {};
        }
    }

    public void removeMenuItem(ActionEvent event) {
        Connection dbConnection = null;
        Statement statement = null;
        int result = 0;

        try {
            DatabaseConnection connectNow = new DatabaseConnection();
            dbConnection = connectNow.getConnection();
            if(dbConnection == null) {
                statusLabel.setText("Failed to connect to database...");
                return;
            }

            String sql = "DELETE FROM menu WHERE name='" + menuItemNameField.getText() + "'";
            statement = dbConnection.createStatement();
            result = statement.executeUpdate(sql);
            if(result == 1) {
                statusLabel.setText("Good job!");
            } else {
                statusLabel.setText("Failed");
            }
        } catch (Exception error) {
            error.printStackTrace();
        } finally {
            try { if(statement != null) statement.close(); } catch (Exception e) {};
            try { if(dbConnection != null) dbConnection.close(); } catch (Exception e) {};
        }
    }

    public Product bun = new Product("entrees", 2011, "bun", 2.00);
    public Product lettuce = new Product("entrees", 2012, "lettuce", 0);
    public Product tomato = new Product("entrees", 2013, "tomato", 0);
    public Product pickles = new Product("entrees", 2014, "pickles", 0);
    public Product onions = new Product("entrees", 2015, "onions", 0);


}
