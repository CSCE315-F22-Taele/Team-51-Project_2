package com.involuntary.revpos.controller;

import com.involuntary.revpos.database.DatabaseConnection;
import com.involuntary.revpos.models.Excess;
import com.involuntary.revpos.models.MenuItem;
import com.involuntary.revpos.models.Pair;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import java.time.*;

public class StatsController extends ManagerController {

    @FXML
    private ImageView managerRestockReportBtn;
    @FXML
    private ImageView managerPairReportBtn;
    @FXML
    private ImageView managerSalesReportBtn;
    @FXML
    private ImageView managerExcessReportBtn;

    public void openRestockReport() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(
                "/views/restockReport.fxml"));
        Scene scene = managerRestockReportBtn.getScene();
        scene.setRoot(root);
    }

    public void openSalesReport() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(
                "/views/salesReport.fxml"));
        Scene scene = managerSalesReportBtn.getScene();
        scene.setRoot(root);
    }

    public void openPairReport() throws IOException {
        Parent root = FXMLLoader.load(
                getClass().getResource("/views/pairReport.fxml"));
        Scene scene = managerPairReportBtn.getScene();
        scene.setRoot(root);
    }

    public void openExcessReport() throws IOException {
        Parent root = FXMLLoader.load(
                getClass().getResource("/views/excessReport.fxml"));
        Scene scene = managerExcessReportBtn.getScene();
        scene.setRoot(root);
    }

    @FXML
    private TableView<Pair> pairTable;
    @FXML
    private TableColumn<Pair, String> firstPairCol;
    @FXML
    private TableColumn<Pair, String> secondPairCol;
    @FXML
    private DatePicker firstDateField;
    @FXML
    private DatePicker secondDateField;

    public ArrayList<String> queryPairs() {
        ArrayList<String> strings = new ArrayList<>();

        Connection dbConnection = null;
        ResultSet result = null;
        Statement statement = null;

        try {

            DatabaseConnection connectNow = new DatabaseConnection();
            dbConnection = connectNow.getConnection();

            statement = dbConnection.createStatement();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(
                    "yyyy-MM-dd",
                    Locale.US);
            String firstDate = (firstDateField.getValue()).format(formatter);
            String secondDate = (secondDateField.getValue()).format(formatter);
            LocalDate start = LocalDate.parse(firstDate);
            LocalDate end = LocalDate.parse(secondDate);
            List<LocalDate> totalDates = new ArrayList<>();
            while (!start.isAfter(end)) {
                totalDates.add(start);
                start = start.plusDays(1);
            }
            List<String> stringDates = new ArrayList<>();
            for (LocalDate ld : totalDates) {
                stringDates.add(ld.toString());
            }
            String dateList = "";
            for (int i = 0; i < stringDates.size(); i++) {
                dateList += "'" + stringDates.get(i) + "'";
                if (i != stringDates.size() - 1) {
                    dateList += ",";
                }
            }
            String sql = "CREATE TABLE Pairs (name varchar(255), inventory int)";
            statement.executeUpdate(sql);
            sql =
                    "INSERT INTO Pairs (name, inventory) VALUES ('Cheese_Burger', (SELECT sum(Cheese_Burger) FROM order_history "
                            + "WHERE Date IN(" + dateList
                            + "))) UNION VALUES ('Black_Bean_Burger', (SELECT sum(Black_Bean_Burger) FROM order_history "
                            + "WHERE Date IN(" + dateList
                            + "))) UNION VALUES ('Classic_Hamburger', (SELECT sum(Classic_Hamburger) FROM order_history "
                            + "WHERE Date IN(" + dateList
                            + "))) UNION VALUES ('Chicken_Sandwich', (SELECT sum(Chicken_Sandwich) FROM order_history "
                            + "WHERE Date IN(" + dateList
                            + "))) UNION VALUES ('Bacon_Burger', (SELECT sum(Bacon_Burger) FROM order_history "
                            + "WHERE Date IN(" + dateList
                            + "))) UNION VALUES ('GigEm_Patty_Melt', (SELECT sum(GigEm_Patty_Melt) FROM order_history "
                            + "WHERE Date IN(" + dateList
                            + "))) UNION VALUES ('Chicken_Tenders', (SELECT sum(Chicken_Tenders) FROM order_history "
                            + "WHERE Date IN(" + dateList
                            + "))) UNION VALUES ('Caesar_Salad', (SELECT sum(Caesar_Salad) FROM order_history "
                            + "WHERE Date IN(" + dateList
                            + "))) UNION VALUES ('Chocolate_Aggie_Shake', (SELECT sum(Chocolate_Aggie_Shake) FROM order_history "
                            + "WHERE Date IN(" + dateList
                            + "))) UNION VALUES ('Vanilla_Aggie_Shake', (SELECT sum(Vanilla_Aggie_Shake) FROM order_history "
                            + "WHERE Date IN(" + dateList
                            + "))) UNION VALUES ('Strawberry_Aggie_Shake', (SELECT sum(Strawberry_Aggie_Shake) FROM order_history "
                            + "WHERE Date IN(" + dateList
                            + "))) UNION VALUES ('French_Fries', (SELECT sum(French_Fries) FROM order_history "
                            + "WHERE Date IN(" + dateList
                            + "))) UNION VALUES ('Fountain_Drink', (SELECT sum(Fountain_Drink) FROM order_history "
                            + "WHERE Date IN(" + dateList
                            + "))) UNION VALUES ('Vanilla_Ice_Cream_Cup', (SELECT sum(Vanilla_Ice_Cream_Cup) FROM order_history "
                            + "WHERE Date IN(" + dateList
                            + "))) UNION VALUES ('Strawberry_Ice_Cream_Cup', (SELECT sum(Strawberry_Ice_Cream_Cup) FROM order_history "
                            + "WHERE Date IN(" + dateList
                            + "))) UNION VALUES ('Chocolate_Ice_Cream_Cup', (SELECT sum(Chocolate_Ice_Cream_Cup) FROM order_history "
                            + "WHERE Date IN(" + dateList + ")))";
            statement.executeUpdate(sql);
            sql = "SELECT name FROM Pairs ORDER BY inventory DESC";
            result = statement.executeQuery(sql);

            while (result.next()) {
                String s = new String(
                        result.getString("name")
                );
                strings.add(s);
            }
            sql = "DROP TABLE pairs";
            statement.executeUpdate(sql);

        } catch (
                Exception ex) {
            ex.printStackTrace();
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
        return strings;
    }

    public void updatePairTable() {
        try {
            ObservableList<Pair> itemList = FXCollections.observableArrayList();
            ArrayList<String> strings = queryPairs();
            ObservableList<ArrayList<Pair>> splitList = FXCollections.observableArrayList();

            for (int i = 0; i < strings.size() / 2; i++) {
                Pair p = new Pair(strings.get(i),
                        strings.get(strings.size() - 1 - i));
                itemList.add(p);
            }

            firstPairCol.setCellValueFactory(
                    new PropertyValueFactory<Pair, String>("pair1"));
            secondPairCol.setCellValueFactory(
                    new PropertyValueFactory<Pair, String>("pair2"));
            pairTable.setItems(itemList);


        } catch (Exception e) {
        }

    }

    @FXML
    private ImageView pairBackBtn;

    @FXML
    public void pairGoBack() throws ClassNotFoundException {
        Scene scene = pairBackBtn.getScene();
        try {
            Parent root = FXMLLoader.load(
                    getClass().getResource("/views/stats.fxml"));
            scene.setRoot(root);
        } catch (Exception e) {
        }
    }


    //EXCESS REPORT

    @FXML
    private TableView<Excess> excessTableView;

    @FXML
    private DatePicker excessDatePicker;

    @FXML
    private TableColumn<Excess, String> excessNameCol;

    @FXML
    private TableColumn<Excess, Integer> percentSoldCol;

    @FXML
    private ImageView excessBackBtn;


    @FXML
    private void queryExcess() {
        Connection dbConnection = null;
        ResultSet result = null;
        Statement statement = null;

        try {

            DatabaseConnection connectNow = new DatabaseConnection();
            dbConnection = connectNow.getConnection();

            statement = dbConnection.createStatement();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(
                    "yyyy-MM-dd",
                    Locale.US);
            String firstDate = (excessDatePicker.getValue()).format(formatter);
            String secondDate = LocalDateTime.now().format(formatter);
            LocalDate start = LocalDate.parse(firstDate);
            LocalDate end = LocalDate.parse(secondDate);

            List<LocalDate> totalDates = new ArrayList<>();
            while (!start.isAfter(end)) {
                totalDates.add(start);
                start = start.plusDays(1);
            }
            List<String> stringDates = new ArrayList<>();
            for (LocalDate ld : totalDates) {
                stringDates.add(ld.toString());
            }
            String dateList = "";
            for (int i = 0; i < stringDates.size(); i++) {
                dateList += "'" + stringDates.get(i) + "'";
                if (i != stringDates.size() - 1) {
                    dateList += ",";
                }
            }

            //need way to loop through all items
            //  1. get every item name and current inventory
            //  2. get number sold within the date rage
            //  3. divide that number sold by the current inventory to get percent (calculatePercentSold)
            //  4. output both the name and percent told to table
            //  5. done
            String sqlQuery = "SELECT * FROM order_history";
            //result = statement.executeQuery(sqlQuery);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void updateExcessTable() {

    }

    @FXML
    public void excessGoBack() throws ClassNotFoundException {
        Scene scene = excessBackBtn.getScene();
        try {
            Parent root = FXMLLoader.load(
                    getClass().getResource("/views/stats.fxml"));
            scene.setRoot(root);
        } catch (Exception e) {
        }
    }
}

