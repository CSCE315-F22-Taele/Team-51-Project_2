package com.involuntary.revpos.controller;

import com.involuntary.revpos.database.DatabaseConnection;
import com.involuntary.revpos.models.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

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
    @FXML
    private Label manageInvStatusLabel;
    @FXML
    private ComboBox manageCategoryField;
    @FXML
    private TextField manageIDField;
    @FXML
    private TextField manageNameField;
    @FXML
    private TextField managePriceField;
    @FXML
    private TextField manageCaloriesField;
    @FXML
    private TextField manageQuantityField;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
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

    /**
     * Open another window used to manage the inventory
     *
     */
    public void openInventoryModal() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/views/manageInventory.fxml"));
        Scene scene = new Scene(root);
        Stage modal = new Stage();
        modal.setScene(scene);
        modal.initModality(Modality.APPLICATION_MODAL);
        modal.setAlwaysOnTop(true);
        modal.setIconified(false);
        modal.setResizable(false);
        modal.setTitle("Manage Inventory");
        modal.getIcons().add(new Image(getClass().getResourceAsStream("/images/box.png")));
        modal.showAndWait();
    }
    public void switchInventoryModal(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/views/manageInventory.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = ((Node) event.getSource()).getScene();
        scene.setRoot(root);
    }
    public void switchAddDeleteModal(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/views/manageInventoryExpanded.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = ((Node) event.getSource()).getScene();
        scene.setRoot(root);
    }

    /**
     * Queries the database and updates a product's stock
     *
     * @param category identifies the database table
     * @param id represents the product id inside database
     * @param quantity represents the new inventory value
     *
     * @return true if query is successful, and false if query failed
     */
    public boolean updateCall(String category, String id, int quantity) {
        Connection dbConnection = null;
        Statement statement = null;
        try {
            String sql = "UPDATE " + category + " SET inventory = " + quantity + " WHERE id = " + id;
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
    public boolean addCall(String category, String id, String name, double price, int calories, int quantity) {
        Connection dbConnection = null;
        Statement statement = null;
        try {
            String sql = "INSERT INTO " + category + " (id, name, price, calories, inventory) VALUES ('" +id+ "','" + name + "','" + price + "','" + calories + "','" + quantity + "')";
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
    public boolean removeCall(String category, String id) {
        Connection dbConnection = null;
        Statement statement = null;
        try {
            String sql = "DELETE FROM " + category + " WHERE id = " + id;
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

    public void updateInventory() {
        try {
            String category = (String) manageCategoryField.getValue();
            String id = manageIDField.getText();
            int quantity = Integer.parseInt(manageQuantityField.getText());
            if(updateCall(category, id, quantity)) {
                manageInvStatusLabel.setText("Database Updated!");
            } else {
                manageInvStatusLabel.setText("Database Failed to Update...");
            }
        } catch (Exception ex) {
            manageInvStatusLabel.setText("Invalid Info!");
        }
    }
    public void addInventory() {
        try {
            String category = (String) manageCategoryField.getValue();
            String id = manageIDField.getText();
            String name = manageNameField.getText();
            double price = Double.parseDouble(managePriceField.getText());
            int calories = Integer.parseInt(manageCaloriesField.getText());
            int quantity = Integer.parseInt(manageQuantityField.getText());
            if(addCall(category, id, name, price, calories, quantity)) {
                manageInvStatusLabel.setText("Database Updated!");
            } else {
                manageInvStatusLabel.setText("Database Failed to Update...");
            }
        } catch (Exception ex) {
            manageInvStatusLabel.setText("Invalid Info!");
        }
    }
    public void removeInventory() {
        try {
            String category = (String) manageCategoryField.getValue();
            String id = manageIDField.getText();

            if(removeCall(category, id)) {
                manageInvStatusLabel.setText("Database Updated!");
            } else {
                manageInvStatusLabel.setText("Database Failed to Update...");
            }
        } catch (Exception ex) {
            manageInvStatusLabel.setText("Invalid Info!");
        }

    }


}
