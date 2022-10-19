package com.involuntary.revpos.controller;

import com.involuntary.revpos.database.DatabaseConnection;
import com.involuntary.revpos.models.MenuItem;
import com.involuntary.revpos.models.Product;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

public class PosExtendedController implements Initializable {

    @FXML
    private Button confirmChangesBtn;
    private static Button referConfirmChangesBtn;
    @FXML
    private Button removeProductBtn;
    private static Button referRemoveProductBtn;
    @FXML
    private ComboBox selectProductField;
    private static ComboBox referSelectProductField;

    /**
     * Executes the body when the FXML Controller is loaded
     *
     * @param url            represents the location of the fxml file
     * @param resourceBundle represents any resources utilized
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        referConfirmChangesBtn = confirmChangesBtn;
        referSelectProductField = selectProductField;
        referRemoveProductBtn = removeProductBtn;
    }

    /**
     * Calls FXML to open the modal that allows modification to an order
     *
     * @param item represents the item being edited/modified
     */
    public static void openModal(MenuItem item) throws IOException {
        Parent root = FXMLLoader.load(PosExtendedController.class.getResource(
            "/views/posEditOrder.fxml"));
        Scene scene = new Scene(root);
        Stage modal = new Stage();
        modal.setScene(scene);
        modal.initModality(Modality.APPLICATION_MODAL);
        modal.setAlwaysOnTop(true);
        modal.setIconified(false);
        modal.setResizable(false);
        modal.setTitle("Editing Order");
        modal.getIcons().add(new Image(
            PosExtendedController.class.getResourceAsStream(
                "/images/tamulogo.png")));

        referSelectProductField.getItems().clear();
        Connection dbConnection = null;
        Statement statement = null;
        ResultSet result = null;

        Map<String, Integer> dictionary = null;
        try {
            DatabaseConnection connectNow = new DatabaseConnection();
            dbConnection = connectNow.getConnection();

            String sql = "SELECT name, id FROM ingredients WHERE id in (";
            for (Product product : item.getOptions()) {
                sql += product.getId();
                if (product.getId() != item.getOptions()
                    .get(item.getOptions().size() - 1).getId()) {
                    sql += ",";
                }
            }
            sql += ")";

            statement = dbConnection.createStatement();
            result = statement.executeQuery(sql);

            dictionary = new HashMap<String, Integer>();
            List<String> labels = new ArrayList<>();
            while (result.next()) {
                String name = result.getString("name");
                int id = result.getInt("id");
                String key =
                    name.substring(0, 1).toUpperCase() + name.substring(1);
                labels.add(key);
                dictionary.put(key, id);
            }
            referSelectProductField.getItems().addAll(labels);
        } catch (Exception error) {
            error.printStackTrace();
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
        ArrayList<Product> removeList = new ArrayList<Product>();
        Map<String, Integer> finalDictionary = dictionary;
        referRemoveProductBtn.setOnAction(event -> {
            String key = "" + referSelectProductField.getValue();
            referSelectProductField.getItems().remove(key);
            removeList.add(new Product(finalDictionary.get(key)));
        });
        referConfirmChangesBtn.setOnAction(event -> {

            Stage stage = (Stage) (referConfirmChangesBtn.getScene()
                .getWindow());
            stage.close();
            PosController.removeItemsToCart(removeList);
            PosController.verifyOrder(item);
        });
        modal.showAndWait();

    }
}
