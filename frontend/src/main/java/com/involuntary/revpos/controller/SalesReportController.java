package com.involuntary.revpos.controller;

import com.involuntary.revpos.database.DatabaseConnection;
import com.involuntary.revpos.models.revenue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class SalesReportController implements
    Initializable {

    @FXML
    private TableView<revenue> salesTable;
    @FXML
    private TableColumn<revenue, String> dateCol;
    @FXML
    private TableColumn<revenue, Double> revenueCol;
    @FXML
    private ImageView salesBackBtn;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        updateTable("2022-12-02", "2022-12-22", 21);
    }

    /**
     * Queries the database and adds every product and its information to a
     * readable list for FXML
     *
     * @return a list containing all the product from the database
     */

    public ObservableList<revenue> queryProducts(String start, String end,
        int numDays) throws SQLException {
        ObservableList<revenue> inventoryList = FXCollections.observableArrayList();

        try {
            DatabaseConnection connectNow = new DatabaseConnection();
            Connection dbConnection = connectNow.getConnection();

            Statement statement = dbConnection.createStatement();
            int startDay = Integer.parseInt(
                String.valueOf(start.substring(start.length() - 2)));
            ;
            int endDay = Integer.parseInt(
                String.valueOf(end.substring(end.length() - 2)));
            String dateRange = "";
            String date = "";
            for (int i = 0; i < numDays; i++) {
                if (startDay < 10) {
                    date = "'" + "2022-12-0" + startDay + "'";
                } else {
                    date = "'" + "2022-12-" + startDay + "'";
                }
                startDay = startDay + 1;
                if (startDay == endDay + 1) {
                    dateRange += date;
                    break;
                }

                dateRange += date + ",";
            }
            System.out.println(dateRange);

            String queryData =
                "select revenue, date from order_history where date in("
                    + dateRange + ");";
            //select revenue, date from order_history where date in('2022-12-05','2022-12-06','2022-12-07');
            //String queryData = "SELECT * from order_history;";
            ResultSet result = statement.executeQuery(queryData);
            while (result.next()) {
                //System.out.println(result.getString("date"));
                revenue product = new revenue(
                    result.getString("date"),
                    result.getDouble("revenue")
                );
                inventoryList.add(product);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return inventoryList;
    }

    /**
     * Populates the TableView (Inventory) with the generated list.
     */
    public void updateTable(String start, String end, int numDays) {
        try {
            ObservableList inventoryList = queryProducts(start, end, numDays);
            dateCol.setCellValueFactory(
                new PropertyValueFactory<revenue, String>("id"));
            revenueCol.setCellValueFactory(
                new PropertyValueFactory<revenue, Double>("revenue"));
            salesTable.setItems(inventoryList);

        } catch (Exception e) {
        }
    }

    @FXML
    public TextField startDate;

    @FXML
    public TextField endDate;

    @FXML
    public TextField numDays;

    @FXML
    public Button salesReportBtn;

    /**
     * Calls to update the sales report table
     */
    public void seeSalesReport() {
        String start = startDate.getText();
        String end = endDate.getText();
        int numD = Integer.parseInt(numDays.getText());
        updateTable(start, end, numD);
    }

    /**
     * Calls FXML to go back to the stats scene
     *
     */
    @FXML
    public void goBack() throws ClassNotFoundException {
        Scene scene = salesBackBtn.getScene();
        try {
            Parent root = FXMLLoader.load(
                getClass().getResource("/views/stats.fxml"));
            scene.setRoot(root);
        } catch (Exception e) {
        }
    }
}
