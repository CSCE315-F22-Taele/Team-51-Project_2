package com.involuntary.revpos.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class ManagerController {
    @FXML
    private AnchorPane manager_content__main;

    @FXML
    public void openInventory() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/views/inventory.fxml"));
            manager_content__main.getChildren().setAll(root);
        } catch (Exception ex) {}

    }
    @FXML
    public void openPOS(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/views/pos.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = ((Node) event.getSource()).getScene();
        scene.setRoot(root);
    }
}
