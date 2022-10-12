package com.involuntary.revpos.controller;

import com.involuntary.revpos.database.DatabaseConnection;
import com.involuntary.revpos.models.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ManagerController implements Initializable {
    @FXML
    private TableView<Product> inventoryTable;
    @FXML
    private TableColumn<Product, Integer> idCol;
    @FXML
    private TableColumn<Product, String> nameCol;
    @FXML
    private TableColumn<Product, Double> priceCol;
    @FXML
    private TableColumn<Product, Integer> caloriesCol;
    @FXML
    private TableColumn<Product, Integer> quantityCol;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("HELLO");
        updateTable();
    }

    public ObservableList<Product> queryProducts() {
        ObservableList<Product> inventoryList = FXCollections.observableArrayList();

        try {
            DatabaseConnection connectNow = new DatabaseConnection();
            Connection dbConnection = connectNow.getConnection();

            Statement statement = dbConnection.createStatement();
            String queryData = "SELECT * FROM entrees";
            ResultSet result = statement.executeQuery(queryData);

            while(result.next()) {
                Product product = new Product(
                        result.getInt(1),
                        result.getString(2),
                        result.getDouble(3),
                        result.getInt(4),
                        result.getInt(5)
                );
                inventoryList.add(product);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return inventoryList;
    }
    public void updateTable() {
        try {
            ObservableList inventoryList = queryProducts();
            idCol.setCellValueFactory(new PropertyValueFactory<Product, Integer>("id"));
            nameCol.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
            priceCol.setCellValueFactory(new PropertyValueFactory<Product, Double>("price"));
            caloriesCol.setCellValueFactory(new PropertyValueFactory<Product, Integer>("calories"));
            quantityCol.setCellValueFactory(new PropertyValueFactory<Product, Integer>("quantity"));
            inventoryTable.setItems(inventoryList);

        } catch (Exception e) {}
    }
}
