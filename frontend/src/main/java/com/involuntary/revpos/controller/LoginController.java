package com.involuntary.revpos.controller;

import com.involuntary.revpos.database.DatabaseConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class LoginController {

    @FXML
    private TextField employeeUsernameField;
    @FXML
    private PasswordField employeePasswordField;

    public void verifyLogin(ActionEvent event) {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection dbConnection = connectNow.getConnection();
        String loginData = "SELECT count(1) FROM accounts WHERE username = '" + employeeUsernameField.getText() + "' AND password = '" + employeePasswordField.getText() + "'";
        try {
            Statement statement = dbConnection.createStatement();
            ResultSet queryResult = statement.executeQuery(loginData);
            while(queryResult.next()) {
                if(queryResult.getInt(1) == 1) {
                    System.out.println("FOUND");
                    Parent root = FXMLLoader.load(getClass().getResource("/views/pos.fxml"));
                    Scene scene = new Scene(root);
                    Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    appStage.setScene(scene);
                    appStage.show();
                }
                else {
                    System.out.println("NOT VALID");
                }
            }
        } catch (Exception error) {
            error.printStackTrace();
        }
    }
}
