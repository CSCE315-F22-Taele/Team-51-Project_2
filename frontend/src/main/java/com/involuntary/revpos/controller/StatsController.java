package com.involuntary.revpos.controller;

import com.involuntary.revpos.database.DatabaseConnection;
import com.involuntary.revpos.models.MenuItem;
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
            getClass().getResource("/views/pairReport.fxml"));
        Scene scene = managerPairReport.getScene();
        scene.setRoot(root);
    }

    @FXML
    private TableView<MenuItem> pairTable;
    @FXML
    private TableColumn<MenuItem, String> firstPairCol;
    @FXML
    private TableColumn<MenuItem, String> secondPairCol;

    public ObservableList<MenuItem> queryPairs() {
        ObservableList<MenuItem> itemList = FXCollections.observableArrayList();

        try {
            DatabaseConnection connectNow = new DatabaseConnection();
            Connection dbConnection = connectNow.getConnection();

            Statement statement = dbConnection.createStatement();

            String queryData = "SELECT name FROM X ORDER BY inventory DESC";
            ResultSet result = statement.executeQuery(queryData);

            while (result.next()) {
                MenuItem menuItem = new MenuItem(
                    result.getString("name")
                );
                itemList.add(menuItem);
            }

        } catch (
            Exception ex) {
            ex.printStackTrace();
        }
        return itemList;
    }

    public void updatePairTable() {
        try {
            ObservableList<MenuItem> pairList = queryPairs();
            ObservableList<MenuItem> pair1 = FXCollections.observableArrayList();
            ObservableList<MenuItem> pair2 = FXCollections.observableArrayList();

            for(int i = 0; i < pairList.size() / 2; i++) {
                pair1.add(pairList.get(i));
                pair2.add(pairList.get(pairList.size() - 1 - i));
            }

            firstPairCol.setCellValueFactory(
                new PropertyValueFactory<MenuItem, String>("name"));

            pairTable.setItems(pair1);

            secondPairCol.setCellValueFactory(
                new PropertyValueFactory<MenuItem, String>("name"));

            pairTable.setItems(pair2);


        } catch (Exception e) {
        }

    }

    @FXML
    private ImageView settingBackBtn;
}
