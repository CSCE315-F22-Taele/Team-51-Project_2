package com.involuntary.revpos.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;

import java.io.IOException;

public class StatsController extends ManagerController {
    @FXML
    private ImageView manageReportBtn;

    public void openManageReport() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(
            "/views/restockReport.fxml"));
        Scene scene = manageReportBtn.getScene();
        scene.setRoot(root);
    }
}
