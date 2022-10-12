package com.involuntary.revpos.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class SettingsController {
    @FXML
    private ImageView settingBackBtn;
    @FXML
    private ImageView openManagerBtn;
    @FXML
    private ImageView logoutBtn;

    /**
     * Switches scene on call within Settings scene to POS scene
     *
     */
    @FXML
    public void goBack() throws ClassNotFoundException {
        Scene scene = settingBackBtn.getScene();
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/views/pos.fxml"));
            scene.setRoot(root);
        } catch(Exception e) {}
    }
    /**
     * Switches scene on call within Settings scene to Manager scene
     *
     */
    @FXML
    public void openManager() {
        Scene scene = openManagerBtn.getScene();
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/views/manager.fxml"));
            scene.setRoot(root);
        } catch(Exception e) {}
    }

    /**
     * Switches scene on call within Settings scene to Login scene
     *
     */
    @FXML
    public void logout() {
        Scene scene = logoutBtn.getScene();
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/views/login.fxml"));
            scene.setRoot(root);
        } catch(Exception e) {}
    }
}
