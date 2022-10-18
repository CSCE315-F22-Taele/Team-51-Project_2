package com.involuntary.revpos.controller;

import java.io.IOException;

import com.involuntary.revpos.database.DatabaseConnection;
import com.involuntary.revpos.listeners.MyListener;
import com.involuntary.revpos.models.MenuItem;
import com.involuntary.revpos.models.Product;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.*;

public class PosController extends MenuItemController implements Initializable {

    @FXML
    private VBox cart;
    @FXML
    private Button checkoutBtn;
    @FXML
    private ImageView openSettingsBtn;

    HashMap<Product, Integer> currentCart = new HashMap<>();
    private double cartTotal = 0;
    private double cartTaxTotal = 0;
    private static DecimalFormat df2 = new DecimalFormat("#.00");

    @FXML
    private GridPane grid;
    private List<MenuItem> menuItems = new ArrayList<>();
    private MyListener myListener;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        menuItems.addAll(getData());
        if(menuItems.size() > 0) {
            myListener = new MyListener() {
                @Override
                public void onClickListener(MenuItem item) {
                    // Function to Assign on Click
                    addItemsToCart(item.getIngredients());
                    updateCart(item.getName());
                    computePrice();
                    System.out.println(currentCart.size());
                }
            };
        }
        int column = 0;
        int row = 1;
        try {
            for(int i = 0; i < menuItems.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/views/modelMenuItem.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                MenuItemController menuItemController = fxmlLoader.getController();
                menuItemController.setData(menuItems.get(i), myListener);

                if(column == 5) {
                    column = 0;
                    row++;
                }

                grid.add(anchorPane, column++, row);
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<MenuItem> getData() {
        List<MenuItem> menuItems = new ArrayList<>();
        MenuItem menuItem;

        Connection dbConnection = null;
        Statement statement = null;
        ResultSet result = null;

        try {
            DatabaseConnection connectNow = new DatabaseConnection();
            dbConnection = connectNow.getConnection();

            String sql = "SELECT * FROM menu";
            statement = dbConnection.createStatement();
            result = statement.executeQuery(sql);

            while(result.next()) {
                menuItem = new MenuItem();
                menuItem.setName(result.getString("name"));
                menuItem.setPrice(result.getDouble("price"));
                ArrayList<String> ids = new ArrayList<String>(Arrays.asList(result.getString("ingredients").split(",")));
                ArrayList<Product> ingredients = new ArrayList<Product>();
                for(String id : ids) {
                    ingredients.add(new Product(Integer.parseInt(id)));
                }
                menuItem.setIngredients(ingredients);
                menuItems.add(menuItem);
            }
        } catch (Exception error) {
            error.printStackTrace();
        } finally {
            try { if(result != null) result.close(); } catch (Exception e) {};
            try { if(statement != null) statement.close(); } catch (Exception e) {};
            try { if(dbConnection != null) dbConnection.close(); } catch (Exception e) {};
        }

        return menuItems;
    }



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

    public void addItemsToCart(ArrayList<Product> products) {
        for(Product product : products) {
            Integer count = currentCart.containsKey(product) ? currentCart.get(product) : 0;
            currentCart.put(product, count + 1);
            cartTotal += checkPrice(product) * 1.0625;
        }
    }

    public double checkPrice(Product product) {
        Connection dbConnection = null;
        Statement statement = null;
        ResultSet result = null;
        double price = product.getPrice();
        try {
            String sql = "SELECT price FROM ingredients" + " WHERE id = " + product.getId();
            DatabaseConnection connectNow = new DatabaseConnection();
            dbConnection = connectNow.getConnection();
            statement = dbConnection.createStatement();
            result = statement.executeQuery(sql);
            while(result.next()) {
                price = Math.round(result.getDouble("price") * 100.0)/ 100.0;
            }
        } catch (Exception ex) {
            System.err.println(ex.getClass().getName()+": "+ex.getMessage());
            ex.printStackTrace();
        } finally {
            try { if(result != null) result.close(); } catch (Exception e) {};
            try { if(statement != null) statement.close(); } catch (Exception e) {};
            try { if(dbConnection != null) dbConnection.close(); } catch (Exception e) {};
        }

        return price;
    }

    public boolean updateCall(String id, int quantity) {
        Connection dbConnection = null;
        Statement statement = null;
        try {
            String sql = "UPDATE ingredients" + " SET inventory = inventory - " + quantity + " WHERE id = " + id;
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

    public void updateCart(String item) {
        HBox entry = new HBox();
        Label label = new Label(item);
        entry.getChildren().add(label);
        cart.getChildren().add(entry);
        Separator separator = new Separator();
        cart.getChildren().add(separator);
    }

    public void computePrice() {
        checkoutBtn.setText("CHARGE $" + df2.format(cartTotal));
    }
    public void checkout() {
        System.out.println("CURRENT CART SIZE: " + currentCart.size());
        for (Product item : currentCart.keySet()) {
            updateCall(String.valueOf(item.getId()), currentCart.get(item));
        }

        cart.getChildren().clear();
        currentCart.clear();
        cartTotal = 0.00;
        checkoutBtn.setText("CHARGE $0.00");
    }
    public void openSettings() throws IOException {
        Scene scene = openSettingsBtn.getScene();
        LoginController.openModal(scene);
    }

}