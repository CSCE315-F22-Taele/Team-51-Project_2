package com.involuntary.revpos.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class SettingsController {
    @FXML
    private ImageView settingBackBtn;
    @FXML
    public void goBack() throws ClassNotFoundException {
        Scene scene = settingBackBtn.getScene();
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/views/pos.fxml"));
            scene.setRoot(root);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
