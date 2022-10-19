package com.involuntary.revpos.controller;

import com.involuntary.revpos.database.DatabaseConnection;
import com.involuntary.revpos.models.MenuItem;
import com.involuntary.revpos.models.Product;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;

import java.io.IOException;

public class StatsController extends ManagerController {

    @FXML
    private ImageView manageReportBtn;
    @FXML
    private ImageView managerPairReport;

    @FXML
    private ImageView salesReportBtn;

    public void openManageReport() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(
            "/views/restockReport.fxml"));
        Scene scene = manageReportBtn.getScene();
        scene.setRoot(root);
    }
    public void openSalesReport() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/views/sales_report.fxml"));
        Scene scene = salesReportBtn.getScene();
        scene.setRoot(root);
    }
    public void openManagerPairReport() throws IOException {
        Parent root = FXMLLoader.load(
            getClass().getResource("/views/salesTogetherReport.fxml"));
        Scene scene = managerPairReport.getScene();
        scene.setRoot(root);
    }

    @FXML
    private TableView<MenuItem> pairTable;
    @FXML
    private TableColumn<Product, String> firstPairCol;
    @FXML
    private TableColumn<Product, String> secondPairCol;

    public ObservableList<String> queryPairs() {
        ObservableList<String> singleList = FXCollections.observableArrayList();

        try {
            DatabaseConnection connectNow = new DatabaseConnection();
            Connection dbConnection = connectNow.getConnection();

            Statement statement = dbConnection.createStatement();

            String queryData = "SELECT name FROM X ORDER BY inventory DESC";
            ResultSet result = statement.executeQuery(queryData);

            while (result.next()) {
                singleList.add(result.getString("name"));
            }

        } catch (
            Exception ex) {
            ex.printStackTrace();
        }
        return singleList;
    }

    public void updatePairTable() {
        try {
            ObservableList pairList = queryPairs();

            firstPairCol.setCellValueFactory(
                new PropertyValueFactory<Product, String>("name"));
            secondPairCol.setCellValueFactory(
                new PropertyValueFactory<Product, String>("name"));

            pairTable.setItems(pairList);


        } catch (Exception e) {
        }

    }
}
