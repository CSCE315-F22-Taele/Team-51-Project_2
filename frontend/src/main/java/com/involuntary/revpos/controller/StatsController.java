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
import java.util.*;

import com.involuntary.revpos.models.Product;
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

public class StatsController extends ManagerController  {

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
    private DatePicker excessDatePicker2;


    @FXML
    private TableColumn<Excess, Integer> percentSoldCol;

    @FXML
    private ImageView excessBackBtn;

    static HashMap<Integer, Product> inventoryList = new HashMap<Integer, Product>();

    public HashMap<Integer, Product> queryProducts() throws SQLException {
        try {
            DatabaseConnection connectNow = new DatabaseConnection();
            Connection dbConnection = connectNow.getConnection();

            Statement statement = dbConnection.createStatement();

            String queryData = "SELECT * FROM ingredients";
            ResultSet result = statement.executeQuery(queryData);

            while (result.next()) {
                Product product = new Product(
                        result.getInt("id"),
                        result.getString("name"),
                        result.getDouble("price"),
                        result.getInt("calories"),
                        result.getInt("inventory")
                );
                inventoryList.put(product.getId(), product);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return inventoryList;
    }

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

            String secondDate = excessDatePicker2.getValue().format(formatter);
            String date_string = "2022-12-10";
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
                // - Populate list with inventory of every item
            //  2. get number sold within the date rage
            //  3. Determine if amount sold for each product is < product.inventory*0.1
            //  4. output both the name and percent told to table
            //  5. done
            //
            String sqlQuery;
            HashMap<Integer, Product> inventoryList = queryProducts();
            int day = start.getDayOfMonth();
            while (day > end.getDayOfMonth()) { //(x = inventory*0.1, y is date )
                //get inventory here
                String d = (day < 10) ? "0" + String.valueOf(day) : "1" + String.valueOf(day);
                sqlQuery = "select 2022/12/" + d + ", "
                    + "Pepsi, Diet_pepsi, Gatorade, Mug_Rootbeer, Sierra_mist, Brisk, "
                    + "Straws, Lids, Drink_Cups, "
                    + "Burger_Fillet, Chicken_Fillet, Chicken_Tender, Black_Bean_Fillet, "
                    + "Bun, Lettuce, Pickles, Tomato, Onions, American_Cheese, Swiss_American_Cheese, Fries, Bacon, "
                    + "Meal_Tray, Salt, Pepper, Utensils, Napkins, "
                    + "GigEm_Sauce, Ketchup, Mustard, Mayo, Ranch, Honey_BBQ, Caesar_Dressing, "
                    + "Chocolate, Vanilla, Strawberry, Milk, Whipped_Cream, cookie_sandwich, "
                    + "Dessert_Bowls, Dessert_Cups from order_history where "
                        + "date = " + d + " and (Pepsi < " + inventoryList.get(1000).getQuantity()*.1 + " or Diet_pepsi <" + inventoryList.get(1001).getQuantity()*0.1 + " or Gatorade < " + inventoryList.get(1002).getQuantity()*0.1 + " or Mug_Rootbeer < " + inventoryList.get(1003).getQuantity()*0.1 + " or Sierra_mist < "+ inventoryList.get(1004).getQuantity()*0.1 +  " or Brisk < " + inventoryList.get(1005).getQuantity()*0.1
                        + " or Straws < " + inventoryList.get(1008).getQuantity()*0.1 +  " or Lids < " + inventoryList.get(1007).getQuantity()*0.1 + " or Drink_Cups < " + inventoryList.get(1006).getQuantity()*0.1
                        + " or Burger_Fillet < "+ inventoryList.get(2008).getQuantity()*0.1 + " or Chicken_Fillet < " + inventoryList.get(2007).getQuantity()*0.1 + " or Chicken_Tender < " + inventoryList.get(2009).getQuantity()*0.1 + " or Black_Bean_Fillet < " + inventoryList.get(2010).getQuantity()*0.1
                        + " or Bun < " + inventoryList.get(2011).getQuantity()*0.1 + " or Lettuce < " + inventoryList.get(2012).getQuantity()*0.1 + " or Pickles < " + inventoryList.get(2014).getQuantity()*0.1 + " or tomato < " + inventoryList.get(2013).getQuantity()*0.1 + " or onions < " + inventoryList.get(2015).getQuantity()*0.1
                        + " or American_Cheese < " + inventoryList.get(2016).getQuantity() + " or Swiss_American_Cheese < " + inventoryList.get(2017).getQuantity() + " or fries < " + inventoryList.get(2018).getQuantity() + " or bacon < " + inventoryList.get(2019).getQuantity()
                        + " or meal_tray < " + inventoryList.get(2020).getQuantity() + " or salt < " + inventoryList.get(2021).getQuantity() + " or pepper < " + inventoryList.get(2022).getQuantity() + " or utensils < " + inventoryList.get(2023).getQuantity() + " or napkins < " + inventoryList.get(2024).getQuantity()
                        + " or GigEm_Sauce < " + inventoryList.get(3025).getQuantity() + " or Ketchup < " + inventoryList.get(3026).getQuantity() + " or Mustard < " + inventoryList.get(3027).getQuantity() + " or Mayo < " + inventoryList.get(3028).getQuantity() + " or Ranch < " + inventoryList.get(3029).getQuantity() + " or Honey_BBQ < " + inventoryList.get(3030).getQuantity() + " or Caesar_Dressing < " + inventoryList.get(3031).getQuantity()
                        + " or Chocolate < " + inventoryList.get(4032).getQuantity() + " or Vanilla < " + inventoryList.get(4033).getQuantity() + " or Strawberry < " + inventoryList.get(4034).getQuantity() + " or Milk < " + inventoryList.get(4039).getQuantity() + " or Whipped_Cream < " + inventoryList.get(4040).getQuantity() + " or cookie_sandwich < " + inventoryList.get(4035).getQuantity()
                        + " or Dessert_Bowls < " + inventoryList.get(4037).getQuantity() + " or Dessert_Cups < " + inventoryList.get(4036).getQuantity() + ";";
                result = statement.executeQuery(sqlQuery);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void updateExcessTable() {
        queryExcess();
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

