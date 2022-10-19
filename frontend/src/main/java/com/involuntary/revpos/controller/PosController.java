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
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.*;

public class PosController extends MenuItemController implements Initializable {

    @FXML
    private VBox cart;
    private static VBox referCart;
    @FXML
    private Label taxLabel;
    private static Label referTaxLabel;
    @FXML
    private Label discountLabel;
    private static Label referDiscountLabel;
    @FXML
    private Button checkoutBtn;
    private static Button referCheckoutBtn;
    @FXML
    private ImageView openSettingsBtn;


    private HashMap<Product, Integer> currentCart = new HashMap<>();
    private static HashMap<Product, Integer> referCurrentCart;
    private static double cartTotal = 0;
    private static double cartTaxTotal = 0;
    private static double cartDiscountTotal = 0;
    private static DecimalFormat df2 = new DecimalFormat("#.00");

    @FXML
    private GridPane grid;
    private List<MenuItem> menuItems = new ArrayList<>();
    private MyListener myListener;

    /**
     * Executes the body when the FXML Controller is loaded Dynamically creates
     * the menu interface for all menu items in the database
     *
     * @param url            represents the location of the fxml file
     * @param resourceBundle represents any resources utilized
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        referCart = cart;
        referCheckoutBtn = checkoutBtn;
        referDiscountLabel = discountLabel;
        referTaxLabel = taxLabel;
        referCurrentCart = currentCart;
        menuItems.addAll(getData());
        if (menuItems.size() > 0) {
            myListener = new MyListener() {
                /**
                 * Executes the body when the FXML Controller is loaded
                 *
                 * @param item assigns a listener for when the FXML object is clicked
                 */
                @Override
                public void onClickListener(MenuItem item) throws IOException {
                    // Function to Assign on Click
                    addItemsToCart(item.getIngredients());
                    PosExtendedController.openModal(item);

                }
            };
        }
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < menuItems.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(
                    getClass().getResource("/views/modelMenuItem.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                MenuItemController menuItemController = fxmlLoader.getController();
                menuItemController.setData(menuItems.get(i), myListener);

                if (column == 5) {
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

    /**
     * Queries the database and parses the data to an array to be used in other
     * processes throughout the POS interface
     *
     * @return a list of object MenuItem
     */
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
            ArrayList<String> optionsIds;

            while (result.next()) {
                menuItem = new MenuItem();
                menuItem.setName(result.getString("name"));
                menuItem.setPrice(result.getDouble("price"));
                ArrayList<String> ids = new ArrayList<String>(
                    Arrays.asList(result.getString("ingredients").split(",")));
                if (result.getString("options") == null) {
                    optionsIds = new ArrayList<String>();
                } else {
                    optionsIds = new ArrayList<String>(
                        Arrays.asList(result.getString("options").split(",")));
                }
                ArrayList<Product> ingredients = new ArrayList<Product>();
                for (String id : ids) {
                    ingredients.add(new Product(Integer.parseInt(id)));
                }
                ArrayList<Product> options = new ArrayList<>();
                for (String optionsId : optionsIds) {
                    options.add(new Product(Integer.parseInt(optionsId)));
                }
                menuItem.setIngredients(ingredients);
                menuItem.setOptions(options);
                menuItems.add(menuItem);
            }
        } catch (Exception error) {
            error.printStackTrace();
        } finally {
            try {
                if (result != null) {
                    result.close();
                }
            } catch (Exception e) {
            }
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (Exception e) {
            }
            try {
                if (dbConnection != null) {
                    dbConnection.close();
                }
            } catch (Exception e) {
            }
        }

        return menuItems;
    }

    /**
     * Calls on two helper functions that set the cart values on the interface
     *
     * @param item represents the item being checked/verified
     */
    public static void verifyOrder(MenuItem item) {
        updateCart(item.getName());
        computePrice();
    }

    /**
     * Queries the database and parses the data to an array to be used in other
     * processes throughout the POS interface
     *
     * @param products represent the array that needs to be recorded to the cart
     *                 as ordered from the customer
     */
    public void addItemsToCart(ArrayList<Product> products) {
        for (Product product : products) {
            Integer count =
                referCurrentCart.containsKey(product) ? referCurrentCart.get(
                    product) : 0;
            referCurrentCart.put(product, count + 1);
            double price = checkPrice(product);
            cartTaxTotal += price * 0.0625;
            cartTotal += price;
        }
    }

    public static void removeItemsToCart(ArrayList<Product> products) {
        for (Product product : products) {
            if (referCurrentCart.containsKey(product)) {
                if (referCurrentCart.get(product) <= 1) {
                    referCurrentCart.remove(product);
                } else {
                    referCurrentCart.put(product,
                        referCurrentCart.get(product) - 1);
                }
            }
        }
    }


    public double checkPrice(Product product) {
        Connection dbConnection = null;
        Statement statement = null;
        ResultSet result = null;
        double price = product.getPrice();
        if (price == 0) {
            try {
                String sql = "SELECT price FROM ingredients" + " WHERE id = "
                    + product.getId();
                DatabaseConnection connectNow = new DatabaseConnection();
                dbConnection = connectNow.getConnection();
                statement = dbConnection.createStatement();
                result = statement.executeQuery(sql);
                while (result.next()) {
                    price =
                        Math.round(result.getDouble("price") * 100.0) / 100.0;
                }
            } catch (Exception ex) {
                System.err.println(
                    ex.getClass().getName() + ": " + ex.getMessage());
                ex.printStackTrace();
            } finally {
                try {
                    if (result != null) {
                        result.close();
                    }
                } catch (Exception e) {
                }
                ;
                try {
                    if (statement != null) {
                        statement.close();
                    }
                } catch (Exception e) {
                }
                ;
                try {
                    if (dbConnection != null) {
                        dbConnection.close();
                    }
                } catch (Exception e) {
                }
                ;
            }
        }

        return price;
    }

    public boolean updateCall(String id, int quantity) {
        Connection dbConnection = null;
        Statement statement = null;
        try {
            String sql =
                "UPDATE ingredients" + " SET inventory = inventory - "
                    + quantity + " WHERE id = " + id;
            DatabaseConnection connectNow = new DatabaseConnection();
            dbConnection = connectNow.getConnection();

            statement = dbConnection.createStatement();
            int callStatus = statement.executeUpdate(sql);
            if (callStatus != 1) {
                return false;
            }
        } catch (Exception ex) {
            System.err.println(
                ex.getClass().getName() + ": " + ex.getMessage());
            ex.printStackTrace();
            return false;
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (Exception e) {
            }
            ;
            try {
                if (dbConnection != null) {
                    dbConnection.close();
                }
            } catch (Exception e) {
            }
            ;
        }
        return true;
    }

    public static void updateCart(String item) {
        HBox entry = new HBox();
        Label label = new Label(item);
        entry.getChildren().add(label);
        referCart.getChildren().add(entry);
        Separator separator = new Separator();
        referCart.getChildren().add(separator);
    }

    public static void computePrice() {
        referTaxLabel.setText(
            "$" + (df2.format(cartTaxTotal).equals(".00") ? "0.00"
                : df2.format(cartTaxTotal)));
        referDiscountLabel.setText(
            "$" + (df2.format(cartDiscountTotal).equals(".00") ? "0.00"
                : df2.format(cartDiscountTotal)));
        referCheckoutBtn.setText("CHARGE $" + df2.format(
            cartTotal + cartTaxTotal - cartDiscountTotal));
    }

    public void checkout() {
        currentCart = referCurrentCart;
        for (Product item : currentCart.keySet()) {
            updateCall(String.valueOf(item.getId()), currentCart.get(item));
        }

        cart.getChildren().clear();
        currentCart.clear();
        cartTaxTotal = 0.00;
        cartDiscountTotal = 0.00;
        cartTotal = 0.00;
        taxLabel.setText("$0.00");
        discountLabel.setText("$0.00");
        checkoutBtn.setText("CHARGE $0.00");
    }

    public void openSettings() throws IOException {
        Scene scene = openSettingsBtn.getScene();
        LoginController.openModal(scene);
    }

}