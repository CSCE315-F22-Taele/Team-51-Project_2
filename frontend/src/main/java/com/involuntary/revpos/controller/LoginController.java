package com.involuntary.revpos.controller;

import com.involuntary.revpos.database.DatabaseConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class LoginController {

    @FXML
    private TextField employeeUsernameField;
    @FXML
    private PasswordField employeePasswordField;
    @FXML
    private Label loginMsgLabel;

    /**
     * Verifies the credentials from the login screen, switching the scene if successful.
     *
     * @param event represents the high-level event generated by a component (e.g. button)
     *
     */

    public void verifyLogin(ActionEvent event) {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection dbConnection = connectNow.getConnection();
        String loginData = "SELECT count(1) FROM accounts WHERE username = '" + employeeUsernameField.getText() + "' AND password = '" + employeePasswordField.getText() + "'";
        try {
            Statement statement = dbConnection.createStatement();
            ResultSet queryResult = statement.executeQuery(loginData);
            while(queryResult.next()) {
                if(queryResult.getInt(1) == 1) {
                    loginMsgLabel.setText("Success! Logging in...");
                    Parent root = FXMLLoader.load(getClass().getResource("/views/pos.fxml"));
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    Scene scene = ((Node) event.getSource()).getScene();
                    scene.setRoot(root);
                    stage.setTitle("Revs American Grill [POS]");

                }
                else {
                    loginMsgLabel.setText("Invalid credentials, try again!");
                }
            }
        } catch (Exception error) {
            error.printStackTrace();
        }
    }
}
