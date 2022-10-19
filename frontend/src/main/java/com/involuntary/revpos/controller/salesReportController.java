package com.involuntary.revpos.controller;
import com.involuntary.revpos.database.DatabaseConnection;
import com.involuntary.revpos.models.revenue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;


public class salesReportController implements
    Initializable {

        @FXML
        private TableView<revenue> salesTable;
        @FXML
        private TableColumn<revenue, String> dateCol;
        @FXML
        private TableColumn<revenue, Double> revenueCol;


        @Override
        public void initialize(URL url, ResourceBundle rb) {
            updateTable();
        }

        /**
         * Queries the database and adds every product and its information to a
         * readable list for FXML
         *
         * @return a list containing all the product from the database
         */
        public ObservableList<revenue> queryProducts() throws SQLException {
            ObservableList<revenue> inventoryList = FXCollections.observableArrayList();

            try {
                DatabaseConnection connectNow = new DatabaseConnection();
                Connection dbConnection = connectNow.getConnection();

                Statement statement = dbConnection.createStatement();

                //String queryData = "('2022-12-05 to 2022-12-07' date list ) select revenue, date from order_history where date in('2022-12-05','2022-12-06','2022-12-07');";
                String queryData = "SELECT * from order_history;";
                ResultSet result = statement.executeQuery(queryData);
                result.next();
                System.out.println(result.getDouble("revenue"));
                String startDate = "2022-12-05";
                String endDate = "2022-12-07";
                Boolean getCurrent = true;
                while (result.next()) {
                    //if (result.getString("date") == startDate) { getCurrent = true; }
                    if (getCurrent) {
                        revenue product = new revenue(
                                result.getString("date"),
                                result.getDouble("revenue")
                        );
                        inventoryList.add(product);
                        //if (result.getString("date") == endDate) { break; }
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }


            return inventoryList;
        }

        /**
         * Populates the TableView (Inventory) with the generated list.
         */
        public void updateTable() {
            try {
                ObservableList inventoryList = queryProducts();
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
        public Button salesReportBtn;
        public void seeSalesReport() {

        }
}
