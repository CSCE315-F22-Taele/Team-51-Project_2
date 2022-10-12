package com.involuntary.revpos.controller;

import com.involuntary.revpos.database.DatabaseConnection;
import com.involuntary.revpos.models.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.ResourceBundle;

public class InventoryController extends ManagerController implements Initializable {
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
        System.out.println("POPULATING TABLE");
        updateTable();
    }

    /**
     * Queries the database and adds every product and its information to a readable list for FXML
     *
     * @return a list containing all the product from the database
     */
    public ObservableList<Product> queryProducts() {
        ObservableList<Product> inventoryList = FXCollections.observableArrayList();
        List<String> categories = List.of("entrees", "drinks", "sauces", "desserts");

        try {
            DatabaseConnection connectNow = new DatabaseConnection();
            Connection dbConnection = connectNow.getConnection();

            Statement statement = dbConnection.createStatement();

            for(String category : categories) {
                String queryData = "SELECT * FROM " + category;
                ResultSet result = statement.executeQuery(queryData);

                while(result.next()) {
                    Product product = new Product(
                            result.getInt("id"),
                            result.getString("name"),
                            result.getDouble("price"),
                            result.getInt("calories"),
                            result.getInt("inventory")
                    );
                    inventoryList.add(product);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return inventoryList;
    }

    /**
     * Populates the TableView (Inventory) with the generated list.
     *
     */
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
