package com.involuntary.revpos.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ManagerController {
    @FXML
    public void openHome(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/views/manager.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = ((Node) event.getSource()).getScene();
        scene.setRoot(root);
    }
    @FXML
    public void openInventory(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/views/inventory.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = ((Node) event.getSource()).getScene();
        scene.setRoot(root);
    }
    @FXML
    public void openManageMenu(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/views/manageMenu.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = ((Node) event.getSource()).getScene();
        scene.setRoot(root);
    }
    @FXML
    public void openStats(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/views/stats.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = ((Node) event.getSource()).getScene();
        scene.setRoot(root);
    }
    @FXML
    public void openPOS(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/views/pos.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = ((Node) event.getSource()).getScene();
        scene.setRoot(root);
    }
}
